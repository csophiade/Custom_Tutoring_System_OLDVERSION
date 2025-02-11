package com.springboot.webapp;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
//endregion

@Controller
public class LessonController {
    @RequestMapping("/approveLesson")
    public static ModelAndView approveLesson(HttpServletRequest request)  {
        // from the form, the parameters are taken into here,
        int lessonID = Integer.parseInt(request.getParameter("approveLesson-lessonID"));
        int isApproved = 1;
        String msg;
        if (DAO.approveLesson(isApproved, lessonID) != 1){
            return PageController.errorpage(request);
        }
        // req.setAttribute("message", msg);
        PageController.bookings(request);
        return new ModelAndView("booking-info");
    }

    @RequestMapping("/createLesson")
    public static ModelAndView createLesson(HttpServletRequest request) throws ParseException {
        try{
            String username = request.getParameter("username");
            String time = request.getParameter("lessonTime");
            String lessonDate = request.getParameter("lessonDate");
            int hours = Integer.parseInt(request.getParameter("hours"));
            int minutes = Integer.parseInt(request.getParameter("minutes"));
            int duration = (hours * 60) + minutes; //nr
            int userID = DAO.getUserID(username);
            User user = DAO.findLessonStudent(userID);
            if (duration == 0){
                assert user != null;
                duration = user.getLessonDuration();
            }
            int isApproved = 1;
            if (username.equalsIgnoreCase("block")){
                isApproved = 2;
            }
            int lessonID = -1;
            if (DAO.validateTime(lessonDate, time, duration, lessonID)){
                if (DAO.createLesson(lessonDate, time, duration, userID, isApproved) != 1){
                    return PageController.errorpage(request);
                }
                CalendarController.submitDate(lessonDate, request);
                return new ModelAndView("calendar-week");
            } else {
                request.setAttribute("errorMessage", "lesson already exists during this time");
                CalendarController.submitDate(lessonDate, request);
                return new ModelAndView("calendar-week");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/requestLesson")
    public static ModelAndView requestLesson(HttpServletRequest request) throws ParseException {
        try{
            String username = request.getParameter("username");
            String time = request.getParameter("lessonTime");
            String lessonDate = request.getParameter("lessonDate");
            int hours = Integer.parseInt(request.getParameter("hours"));
            int minutes = Integer.parseInt(request.getParameter("minutes"));
            int duration = (hours * 60) + minutes;
            int userID = DAO.getUserID(username);
            User user = DAO.findLessonStudent(userID);
            if (duration == 0){
                assert user != null;
                duration = user.getLessonDuration();
            }
            int isApproved = 0;
            int lessonID = 0;
            if (DAO.validateTime(lessonDate, time, duration, lessonID)){
                if (DAO.createLesson(lessonDate, time, duration, userID, isApproved) != 1){
                    return PageController.errorpage(request);
                }
                CalendarController.submitDate(lessonDate, request);
                return new ModelAndView("calendar-week-student");
            } else {
                request.setAttribute("errorMessage", "lesson already exists during this time");
                CalendarController.submitDate(lessonDate, request);
                return new ModelAndView("calendar-week-student");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/deleteLesson")
    public static ModelAndView deleteLesson(HttpServletRequest request) {
        // from the form, the parameters are taken into here,
        int lessonID = Integer.parseInt(request.getParameter("deleteLesson-lessonID"));
        String msg;
        if (DAO.deleteLesson(lessonID) != 1){
            return PageController.errorpage(request);
        }
        msg = "Successfully rejected lesson";
        // req.setAttribute("message", msg);
        PageController.bookings(request);
        return new ModelAndView("booking-info");
    }

    @RequestMapping("/editLesson")
    public static ModelAndView editLesson(HttpServletRequest request) {
        // from the form, the parameters are taken into here,
        int lessonID = Integer.parseInt(request.getParameter("editLessonApprove-lessonID"));
        String date = request.getParameter("editLessonApprove-date");
        String startTime = request.getParameter("editLessonApprove-startTime");
        int hours = Integer.parseInt(request.getParameter("editLessonApprove-hours"));
        int minutes = Integer.parseInt(request.getParameter("editLessonApprove-minutes"));
        int duration = (hours * 60) + minutes;
        boolean isValid = DAO.validateTime(date, startTime, duration, lessonID);
        int isApproved = 1;
        String msg = null;
        if (!isValid){
            msg = "a lesson already occurs during this time";
        } else{
            DAO.editLesson(date, startTime, duration, isApproved, lessonID);
        }
        request.setAttribute("message", msg);
        return PageController.bookings(request);
    }

    @RequestMapping("/searchLesson")
    public static ModelAndView lessonSearcher(HttpServletRequest request) {
        String searchValue = request.getParameter("q");
        if ((searchValue != null) && (!searchValue.equals(""))) {
            List<Lesson> searchedLessons = DAO.doGetLessonSearch(searchValue);
            request.setAttribute("lessons2", searchedLessons);
        }
        return PageController.bookings(request);
    }
}
