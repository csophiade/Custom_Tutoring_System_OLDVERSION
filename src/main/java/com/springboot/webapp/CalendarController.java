package com.springboot.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CalendarController {

    @RequestMapping("/submitDate")
    public static ModelAndView submitDate(String lessonDate, HttpServletRequest request) throws ParseException {
        try{
            String date = request.getParameter("date-string"); // gets the date the user clicked on in calendar
            if (date == null){
                date = lessonDate; // sometimes they make a lesson in calendar week, so use the date of this lesson, so they can see it in the week
            }
            //region Date Format
            DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date inputDate = inputDateFormat.parse(date); // parse into a date
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(inputDate);
            c.setFirstDayOfWeek(Calendar.MONDAY); // find this week, starting monday
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // get the date of monday in the week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String monday = df.format(c.getTime()); // finding what monday is
            c.add(Calendar.DATE, 1); // add one day to the date in order to find tuesday
            String tuesday = df.format(c.getTime());
            c.add(Calendar.DATE, 1);
            String wednesday = df.format(c.getTime());
            c.add(Calendar.DATE, 1);
            String thursday = df.format(c.getTime());
            c.add(Calendar.DATE, 1);
            String friday = df.format(c.getTime());
            c.add(Calendar.DATE, 1);
            String saturday = df.format(c.getTime());
            c.add(Calendar.DATE, 1);
            String sunday = df.format(c.getTime());
            String[] weekdays; // initialising String array
            weekdays = new String[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday}; // array with capacity 7
            ArrayList<Lesson>[] dayLessons; // array of arraylist of lessons
            dayLessons = new ArrayList[7]; // representing each day of the week
            for (int i = 0; i<weekdays.length; i++){
                dayLessons[i] = DAO.getDayLessons(weekdays[i]); // set each day to an array list of all the lessons on that day
            }
            if (sunday == date || sunday.equals(date)){ // sometimes clicking on sunday gives the following week, this amends this issue
                date = saturday;
            }
            // endregion
            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("sessionUser");
            if (sessionUser.getIsTeacher() == 0){ // validate if user is teacher or student
                PageController.calendarWeekStudent(date, dayLessons, request);
                return new ModelAndView("calendar-week-student");
            } else if (sessionUser.getIsTeacher() == 1){
                PageController.calendarWeek(date, dayLessons, request);
                return new ModelAndView("calendar-week");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new ModelAndView("errorpage"); // returns error page if error is caught
        }
        return new ModelAndView("errorpage");
    }
}
