package com.springboot.webapp;
//region Import Statements
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//endregion

@Controller
public class LoginController {
    // login - goes to homepage (unless error)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView display(HttpServletRequest request, HttpServletResponse response, Model m) throws ServletException, IOException, InterruptedException,
            SQLException, ClassNotFoundException, NoSuchMethodException, ParseException {
        try{ // due to the number of exceptions
            String username = request.getParameter("username");
            String password = DigestUtils.sha256Hex(request.getParameter("password"));
            int iduser = DAO.loginFunction(username, password); // returns userID to show user exists and for use in order to set session later
            if (iduser != -1){
                // region Lesson Counter + Balancer
                ArrayList<User> userArrayList = DAO.doGetUserList(); // a list of all existing students
                for (int i = 0; i < (userArrayList != null ? userArrayList.size() : 0); i++){ // iterate through each user
                    int userID = userArrayList.get(i).getUserID(); // get the user ID of the current user
                    ArrayList<Lesson> userLessonsNotCounted = DAO.findUserIDLessonsNotCounted(userID); // find all the user's lessons which have not been counted
                    for (int j = 0; j < (userLessonsNotCounted != null ? userLessonsNotCounted.size() : 0); j++){ // for each of these user's lessons
                        String date = userLessonsNotCounted.get(j).getLessonDate();
                        String time = userLessonsNotCounted.get(j).getLessonTime();
                        String datetime = date + " " + time; // combine date and time with " "
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm", Locale.getDefault());
                        LocalDateTime lessonDateTime = LocalDateTime.parse(datetime, formatter); // parse into LocalDateTime
                        LocalDateTime now = LocalDateTime.now(); // get the time as of now
                        if (now.isAfter(lessonDateTime)) { // if the lesson has already started
                            int lessonID = userLessonsNotCounted.get(j).getLessonID(); // get the lesson id
                            int isCounted = 1;
                            if (DAO.editIsCounted(isCounted, lessonID) != 1){ // edit is counted to be 1 so it is not retrieved again
                                return PageController.errorpage(request); // if not updated, send error page
                            }
                            int lessonCount = DAO.getLessonCount(userID); // get lesson count of the user
                            lessonCount = lessonCount + 1; // add 1 to user lesson count
                           if (DAO.editLessonCount(lessonCount, userID) != 1){ //update new lesson count
                               return PageController.errorpage(request); // if fails
                           }
                        }
                    }
                    int lessonCount = DAO.getLessonCount(userID); // get the updated lesson count of current user
                    while (lessonCount >= 1){ // while lesson count is above 1, add 25% of tuition fee to the balance and minus 1 from lesson count
                        float tuitionFee = (float) DAO.getTuitionFee(userID);
                        float balance = DAO.getBalance(userID);
                        balance = (balance + (tuitionFee/4)); // add only 25% of monthly fee per lesson
                        if (DAO.editBalance(userID, balance) != 1){ // update balance
                            return PageController.errorpage(request); // if failure, errorpage
                        }
                        lessonCount = lessonCount - 1; // reduce until lesson count is 0
                    }
                    if (DAO.editLessonCount(lessonCount, userID) != 1){ // update final lesson count (should be 0)
                        return PageController.errorpage(request); // if fails, errorpage
                    }
                }
                //endregion
                User u = DAO.getSessionUser(iduser); // try catch block in the method
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", u); // set user details in session for retrieval and also validating user later on
                assert u != null;
                if (u.getIsTeacher() == 1) {
                    return PageController.homepageT(request);
                } else if (u.getIsTeacher() == 0) {
                    return PageController.homepageStudent(request);
                }
            } else {
                String msg = "username of password is wrong";
                m.addAttribute("error", msg);
                request.setAttribute("errorMessage", "this user does not exist");
                request.getRequestDispatcher("/index.jsp").forward(request, response); // reload page instead of directing to error page
                Thread.sleep(100);
                return new ModelAndView("index");
            }
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage"); // go to errorpage if an error is caught
        }
        return new ModelAndView("errorpage");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Model m) throws ServletException, IOException {
        try{
            request.getSession().invalidate();
            String msg = "Successfully logged out";
            m.addAttribute("message", msg);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return new ModelAndView("index");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
    }


}
