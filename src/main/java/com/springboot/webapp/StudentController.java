package com.springboot.webapp;
//region Import Statements
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
//endregion

@Controller
public class StudentController {

    @RequestMapping("/createStudent")
    public static ModelAndView signup(HttpServletRequest request) {
        // from the form, the parameters are taken into here, then sent to user u below in this class, then to user create
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        // region Fname Lname
        String[] fname = firstName.split(" ");
        firstName = null;
        for (int i = 0; i<fname.length; i++){
            String temp = fname[i].substring(0, 1).toUpperCase() + fname[i].substring(1,fname[i].length()).toLowerCase();
            if (firstName!=null){
                firstName = firstName + " " + temp;
            } else if (firstName == null){
                firstName = temp;
            }
        }
        String[] lname = lastName.split(" ");
        lastName = null;
        for (int i = 0; i<lname.length; i++){
            String temp = lname[i].substring(0, 1).toUpperCase() + lname[i].substring(1,lname[i].length()).toLowerCase();
            if (lastName!=null){
                lastName = lastName + " " + temp;
            } else if (lastName == null){
                lastName = temp;
            }
        }
        //endregion
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String hash = DigestUtils.sha256Hex(password);
        int isTeacher = 0;
        String address = request.getParameter("address");
        String studentGrade = request.getParameter("studentGrade");
        String studentDoB = request.getParameter("studentDoB"); // migrate to date
        int tuitionFee = Integer.parseInt(request.getParameter("tuitionFee"));
        int balance = Integer.parseInt(request.getParameter("balance"));
        String lessonDurationHours = request.getParameter("hours");
        String lessonDurationMinutes = request.getParameter("minutes");
        int lessonDuration = (Integer.parseInt(lessonDurationHours) * 60) + Integer.parseInt(lessonDurationMinutes);
        String emailAddress = request.getParameter("emailAddress");
        String phoneNumber = request.getParameter("phoneNumber");
        int lessonCount = 0;
        if (!DAO.userExists(username)) {
            int rs = DAO.createUser(firstName, lastName,  username, password, isTeacher, address, studentGrade, studentDoB, tuitionFee,
                    balance, lessonCount, lessonDuration, emailAddress, phoneNumber, hash);
            if (rs == 1) {
                String msg = "Student " + firstName + " " + lastName + " has been successfully created with an average lesson duration of " +
                        (Math.floor(lessonDuration / 60)) + "h " + (lessonDuration % 60) + "m, an average fee of $" + tuitionFee + " and an outstanding current balance of $" + balance;
                request.setAttribute("message", msg);
                return PageController.students(request);
            } else{
                String msg = "error creating user: please try again or contact system admin";
                request.setAttribute("message", msg);
                return PageController.errorpage(request);
            }
        } else if (DAO.userExists(username)) {
            String msg = "Username already exists — please choose a different username";
            request.setAttribute("message", msg);
            return PageController.students(request);
        } else {
            return new ModelAndView("errorpage");
        }

    }

    @RequestMapping("/editStudent")
    public static ModelAndView edit(HttpServletRequest request, Model m) {
        // from the form, the parameters are taken into here, then sent to user u below in this class, then to user edit
        int userID = Integer.parseInt(request.getParameter("form-userID"));
        String firstName = request.getParameter("form-firstName");
        String lastName = request.getParameter("form-lastName");
        //region Fname Lname
        String[] fname = firstName.split(" ");
        firstName = null;
        for (int i = 0; i<fname.length; i++){
            String temp = fname[i].substring(0, 1).toUpperCase() + fname[i].substring(1,fname[i].length()).toLowerCase();
            if (firstName!=null){
                firstName = firstName + " " + temp;
            } else if (firstName == null){
                firstName = temp;
            }
        }
        String[] lname = lastName.split(" ");
        lastName = null;
        for (int i = 0; i<lname.length; i++){
            String temp = lname[i].substring(0, 1).toUpperCase() + lname[i].substring(1,lname[i].length()).toLowerCase();
            if (lastName!=null){
                lastName = lastName + " " + temp;
            } else if (lastName == null){
                lastName = temp;
            }
        }
        //endregion
        String username = request.getParameter("form-username");
        String password = request.getParameter("form-password");
        String hash = DigestUtils.sha256Hex(password);
        String address = request.getParameter("form-address");
        String studentGrade = request.getParameter("form-studentGrade");
        String studentDoB = request.getParameter("form-studentDoB"); // migrate to date
        int tuitionFee = Integer.parseInt(request.getParameter("form-tuitionFee"));
        String lessonDurationHours = request.getParameter("form-hours");
        String lessonDurationMinutes = request.getParameter("form-minutes");
        int lessonDuration = (Integer.parseInt(lessonDurationHours) * 60) + Integer.parseInt(lessonDurationMinutes);
        String emailAddress = request.getParameter("form-emailAddress");
        String phoneNumber = request.getParameter("form-phoneNumber");
        int lessonCount = 0;
        String msg;
        if ((!DAO.userExists(username)) || (DAO.getUserID(username) == userID)){ //ensuring that the username has not already been used
            // this method may return an error, so it has a try-catch block in the method
            int rs = DAO.editUser(userID, firstName, lastName, username, password, address, studentGrade, studentDoB, tuitionFee,
                    lessonDuration, lessonCount, emailAddress, phoneNumber, hash);
            if (rs == 1) {
                msg = "Student " + firstName + " " + lastName + " has been successfully updated with username: " + username + ", and password: "
                        + password + ", with an average lesson duration of " + (Math.floor(lessonDuration / 60)) + "h " + (lessonDuration % 60)
                        + "m, and an average fee of $" + tuitionFee;
                request.setAttribute("message", msg);
                return PageController.students(request); // redirect to student page
            } else {
                msg = "error creating user: please try again or contact system admin";
                m.addAttribute("error", msg);
                request.setAttribute("message", "error creating user: please try again or contact system admin");
                return PageController.errorpage(request); // sends to errorpage if does not work
            }
        } else{
            msg = "Username already exists — please choose a different username";
            m.addAttribute("errorMessage", msg);
            request.setAttribute("message", msg);
            return PageController.students(request); // reloads the page to make it easy for teacher to amend mistake
        }
    }

    @RequestMapping("/deleteStudent")
    public static ModelAndView delete(HttpServletRequest request) {
        // from the form, the parameters are taken into here
        int userID = Integer.parseInt(request.getParameter("form2-userID"));
        String msg;
            if (DAO.deleteUser(userID) == 1){
                msg = "Successfully deleted user";
                request.setAttribute("message", msg);
                return PageController.students(request);
            }
            return PageController.errorpage(request);
    }

    @RequestMapping("/editBalance")
    public static ModelAndView balanceEditor(HttpServletRequest request) {
        // from the form, the parameters are taken into here, then sent to user u below in this class, then to user edit
        int userID = Integer.parseInt(request.getParameter("form-userID1"));
        int paymentSubtract = Integer.parseInt(request.getParameter("form-payment"));
        int paymentAdd = Integer.parseInt(request.getParameter("form-paymentadd"));
        int balance = Integer.parseInt(request.getParameter("form-balance1"));
        float newBalance = balance - paymentSubtract + paymentAdd;
        String msg;
        if (DAO.editBalance(userID, newBalance) == 1){
            msg = "Successfully edited user balance." + " New balance is now $" + newBalance;
            request.setAttribute("message", msg);
            return PageController.students(request);
        }
        return PageController.errorpage(request);
    }

    @RequestMapping("/searchStudent")
    public static ModelAndView studentSearcher(HttpServletRequest request) {
        String searchValue = request.getParameter("q");
        List<User> searchedUsers = DAO.doGetStudentSearch(searchValue);
        request.setAttribute("users2", searchedUsers);
        return PageController.students(request);
    }
}
