package com.springboot.webapp;
//region Import Statements
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.sql.*;
import java.time.format.DateTimeFormatter;
//endregion

@Controller
public class PageController {
    @RequestMapping("/homepage-teacher")
    public static ModelAndView homepageT(HttpServletRequest request) {
            List<Lesson> approvedLessons = DAO.doGetApprovedLessonToday();
            request.setAttribute("approvedLessonsToday", approvedLessons);
            assert approvedLessons != null;
            for (Lesson lesson : approvedLessons) {
                User s = DAO.findLessonStudent(lesson.getuID());
                assert s != null;
                lesson.setUserFirstName(s.getFirstName());
                lesson.setUserLastName( s.getLastName());
                lesson.setUserAddress(  s.getAddress());
                lesson.setUserContact(  s.getContactNumber());
                lesson.setUserDuration( s.getLessonDuration());
            }
            List<Lesson> approvedLessonsTomorrow = DAO.doGetApprovedLessonTomorrow();
            request.setAttribute("approvedLessonsTomorrow", approvedLessonsTomorrow);
            assert approvedLessonsTomorrow != null;
            for (Lesson lesson : approvedLessonsTomorrow) {
                User s = DAO.findLessonStudent(lesson.getuID());
                assert s != null;
                lesson.setUserFirstName(s.getFirstName());
                lesson.setUserLastName( s.getLastName());
                lesson.setUserAddress(  s.getAddress());
                lesson.setUserContact(  s.getContactNumber());
                lesson.setUserDuration( s.getLessonDuration());
            }
            List<Lesson> unapprovedLessons = DAO.doGetUnapprovedLesson();
            assert unapprovedLessons != null;
            int j = unapprovedLessons.size();
            request.setAttribute("unapprovedLessonLength", j);

            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("sessionUser");
            if (sessionUser.getIsTeacher() == 1){
                return new ModelAndView("homepage-teacher");
            } else{
                return new ModelAndView("errorpage");
            }
    }

    @RequestMapping("/homepage-student")
    public static ModelAndView homepageStudent(HttpServletRequest request) throws ParseException {
        try { // due to parseException error
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("sessionUser");
            int userID = sessionUser.getUserID();

            ArrayList<Lesson> userApprovedLessons = (ArrayList<Lesson>) DAO.doGetUserApprovedLessons(userID);
            ArrayList<Lesson> userApprovedLessonsUpcoming = new ArrayList<>();
            ArrayList<Lesson> userApprovedLessonsPast = new ArrayList<>();
            assert userApprovedLessons != null;
            for (Lesson userApprovedLesson : userApprovedLessons) {
                String date = userApprovedLesson.getLessonDate();
                String todayString = LocalDate.now().toString();
                DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date inputDate = inputDateFormat.parse(date);
                DateFormat todayDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date today = todayDateFormat.parse(todayString);
                if (inputDate.after(today) || inputDate == today || inputDate.equals(today)) {
                    userApprovedLessonsUpcoming.add(userApprovedLesson);
                } else if (inputDate.before(today)) {
                    userApprovedLessonsPast.add(userApprovedLesson);
                }
            }
            request.setAttribute("approvedLessons", userApprovedLessons);
            request.setAttribute("approvedLessonsPast", userApprovedLessonsPast);
            request.setAttribute("approvedLessonsUpcoming", userApprovedLessonsUpcoming);

            ArrayList<Lesson> userUnapprovedLessons = (ArrayList<Lesson>) DAO.doGetUserUnapprovedLessons(userID);
            request.setAttribute("unapprovedLessons", userUnapprovedLessons);

            float balance = sessionUser.getBalance();
            request.setAttribute("balance", balance);

            if (sessionUser.getIsTeacher() == 0) {
                return new ModelAndView("homepage-student");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
        return new ModelAndView("errorpage");
    }

    @RequestMapping("/calendar-student")
    public static ModelAndView calendarStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("sessionUser", sessionUser);
        String today = LocalDate.now().toString();
        request.setAttribute("today", today);

        String timeString = LocalTime.now().toString();
        String time = timeString.substring(0, 5);
        request.setAttribute("time", time);

        if (sessionUser.getIsTeacher() == 0){
            return new ModelAndView("calendar-student");
        } else{
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/calendar-week-student")
    public static ModelAndView calendarWeekStudent(String date, ArrayList<Lesson>[] dayLessons, HttpServletRequest request) {
        request.setAttribute("dateInput", date);
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("sessionUser", sessionUser);
        String errorMessage = (String) request.getAttribute("errorMessage");
        request.setAttribute("errorMessage", errorMessage);
        //region Set Weekday Lesson Arrays & Set Lengths
        ArrayList<Lesson> mondayLessons =  dayLessons[0];
        ArrayList<Lesson> tuesdayLessons =  dayLessons[1];
        ArrayList<Lesson> wednesdayLessons = dayLessons[2];
        ArrayList<Lesson> thursdayLessons = dayLessons[3];
        ArrayList<Lesson> fridayLessons =  dayLessons[4];
        ArrayList<Lesson> saturdayLessons = dayLessons[5];
        ArrayList<Lesson> sundayLessons =  dayLessons[6];
        request.setAttribute("mondayLength", mondayLessons.size());
        request.setAttribute("tuesdayLength", tuesdayLessons.size());
        request.setAttribute("wednesdayLength", wednesdayLessons.size());
        request.setAttribute("thursdayLength", thursdayLessons.size());
        request.setAttribute("fridayLength", fridayLessons.size());
        request.setAttribute("saturdayLength", saturdayLessons.size());
        request.setAttribute("sundayLength", sundayLessons.size());
        //endregion
        //region Set Weekday Lesson Details
        request.setAttribute("mondayLessons", mondayLessons);
        for (Lesson lesson : mondayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }

        request.setAttribute("tuesdayLessons", tuesdayLessons);
        for (Lesson lesson : tuesdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("wednesdayLessons", wednesdayLessons);
        for (Lesson lesson : wednesdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("thursdayLessons", thursdayLessons);
        for (Lesson lesson : thursdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("fridayLessons", fridayLessons);
        for (Lesson lesson : fridayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("saturdayLessons", saturdayLessons);
        for (Lesson lesson : saturdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("sundayLessons", sundayLessons);
        for (Lesson lesson : sundayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        //endregion
        if (sessionUser.getIsTeacher() == 0){
            return new ModelAndView("calendar-week-student");
        } else{
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/student-info")
    public static ModelAndView students(HttpServletRequest request) {
        List<User> users = DAO.doGetStudent();
        request.setAttribute("users", users);

        if (request.getAttribute("users2") != null){
            users = (List<User>) request.getAttribute("users2");
            request.setAttribute("users", users);
        }
        String msg = (String) request.getAttribute("message");
        request.setAttribute("message", msg);

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getIsTeacher() == 1){
            return new ModelAndView("student-info");
        } else{
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/booking-info")
    public static ModelAndView bookings(HttpServletRequest request) {
        ArrayList<String> studentList = DAO.getUsernameList();
        request.setAttribute("studentList", studentList);
        assert studentList != null;
        int studentListLength = studentList.size();
        request.setAttribute("studentListLength", studentListLength);
        List<Lesson> unapprovedLessons = DAO.doGetUnapprovedLesson();
        request.setAttribute("unapprovedLessons", unapprovedLessons);
        String today = LocalDate.now().toString();
        request.setAttribute("today", today);
        // region Setting lessons (unapproved and approved)
        assert unapprovedLessons != null;
        for (Lesson lesson : unapprovedLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        List<Lesson> approvedLessons = DAO.doGetAllApprovedLessons();
        request.setAttribute("approvedLessons", approvedLessons);
        assert approvedLessons != null;
        for (Lesson lesson : approvedLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }

        if (request.getAttribute("lessons2") != null){
            List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons2");
            request.setAttribute("approvedLessons", lessons);
            for (Lesson lesson : lessons) {
                User s = DAO.findLessonStudent(lesson.getuID());
                assert s != null;
                lesson.setUserFirstName(s.getFirstName());
                lesson.setUserLastName( s.getLastName());
                lesson.setUserAddress(  s.getAddress());
                lesson.setUserContact(  s.getContactNumber());
                lesson.setUserDuration( s.getLessonDuration());
            }
        }
        //endregion

        String msg = (String) request.getAttribute("message");
        request.setAttribute("message", msg);

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getIsTeacher() == 1){
            return new ModelAndView("booking-info");
        } else{
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/errorpage")
    public static ModelAndView errorpage(HttpServletRequest request) {
        String msg = (String) request.getAttribute("message");
        request.setAttribute("message", msg);
        return new ModelAndView("errorpage");
    }

    @RequestMapping("/calendar")
    public static ModelAndView calendar(HttpServletRequest request) throws ServletException, IOException {
        try{
            ArrayList<String> studentList = DAO.getUsernameList();
            request.setAttribute("studentList", studentList);
            assert studentList != null;
            int studentListLength = studentList.size();
            request.setAttribute("studentListLength", studentListLength);
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("sessionUser");
            if (sessionUser.getIsTeacher() == 1){
                return new ModelAndView("calendar");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
        return new ModelAndView("errorpage");
    }

    @RequestMapping("/calendar-week")
    public static ModelAndView calendarWeek(String date, ArrayList<Lesson>[] dayLessons, HttpServletRequest request) {
        request.setAttribute("dateInput", date); // date for finding the week around it
        ArrayList<String> studentList = DAO.getUsernameList(); // get all students
        request.setAttribute("studentList", studentList); // set in jsp so teacher can select user instead of typing
        assert studentList != null; // prevent null point exceptions
        int studentListLength = studentList.size();
        request.setAttribute("studentListLength", studentListLength); // javascript knows how many to iterate through
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("sessionUser", sessionUser); // for making block timings
        String errorMessage = (String) request.getAttribute("errorMessage"); // error message from methods which might call this one
        request.setAttribute("errorMessage", errorMessage);
        //region Set Weekday Lesson Arrays & Set Lengths
        ArrayList<Lesson> mondayLessons = dayLessons[0];
        request.setAttribute("mondayLength", mondayLessons.size()); // so javascript knows how much to iterate: other weekdays below...
        ArrayList<Lesson> tuesdayLessons = dayLessons[1];
        request.setAttribute("tuesdayLength", tuesdayLessons.size());
        ArrayList<Lesson> wednesdayLessons = dayLessons[2];
        request.setAttribute("wednesdayLength", wednesdayLessons.size());
        ArrayList<Lesson> thursdayLessons = dayLessons[3];
        request.setAttribute("thursdayLength", thursdayLessons.size());
        ArrayList<Lesson> fridayLessons = dayLessons[4];
        request.setAttribute("fridayLength", fridayLessons.size());
        ArrayList<Lesson> saturdayLessons = dayLessons[5];
        request.setAttribute("saturdayLength", saturdayLessons.size());
        ArrayList<Lesson> sundayLessons = dayLessons[6];
        request.setAttribute("sundayLength", sundayLessons.size());
        //endregion

        //region Set Weekday Lesson Details
        request.setAttribute("mondayLessons", mondayLessons);
        for (Lesson lesson : mondayLessons) { // enhanced for loop (iterate through the lessons of mondayLessons)
            User s = DAO.findLessonStudent(lesson.getuID()); // get the student who owns the lesson's details
            assert s != null; // prevent null point exceptions
            lesson.setUserFirstName(s.getFirstName()); // set in lesson class
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        } // other weekdays below
        request.setAttribute("tuesdayLessons", tuesdayLessons);
        for (Lesson lesson : tuesdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("wednesdayLessons", wednesdayLessons);
        for (Lesson lesson : wednesdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("thursdayLessons", thursdayLessons);
        for (Lesson lesson : thursdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("fridayLessons", fridayLessons);
        for (Lesson lesson : fridayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("saturdayLessons", saturdayLessons);
        for (Lesson lesson : saturdayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        request.setAttribute("sundayLessons", sundayLessons);
        for (Lesson lesson : sundayLessons) {
            User s = DAO.findLessonStudent(lesson.getuID());
            assert s != null;
            lesson.setUserFirstName(s.getFirstName());
            lesson.setUserLastName( s.getLastName());
            lesson.setUserAddress(  s.getAddress());
            lesson.setUserContact(  s.getContactNumber());
            lesson.setUserDuration( s.getLessonDuration());
        }
        //endregion
        if (sessionUser.getIsTeacher() == 1){
            return new ModelAndView("calendar-week");
        } else{
            return new ModelAndView("errorpage");
        }
    }

    @RequestMapping("/songsheet")
    public ModelAndView songsheet(HttpServletRequest request) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("sessionUser");
            if (sessionUser.getIsTeacher() == 1){
                return new ModelAndView("songsheet");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage");
        }
        return new ModelAndView("errorpage");
    }

    @RequestMapping("/songsheet-student")
    public ModelAndView songsheetStudent(HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser.getIsTeacher() == 0){
            return new ModelAndView("songsheet-student");
        } else{
            return new ModelAndView("errorpage");
        }
    }
}
