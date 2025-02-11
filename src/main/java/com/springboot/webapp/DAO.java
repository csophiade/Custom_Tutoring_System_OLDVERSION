package com.springboot.webapp;
//region Import Statements

import org.springframework.stereotype.Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
//endregion

public class DAO {
    private static final String mySqlConnect = "jdbc:mysql://localhost:3306/";
    private static final String myDB = "sys";
    private static final String dbUser = "root";
    private static final String dbPass = "password1234";


    // region Update and Delete
    public static int deleteUser(int userID) { // trying to edit result set making sure it can edit
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("DELETE FROM db_users WHERE idusers = ?");
            stm.setInt(1, userID);
            int rs = stm.executeUpdate();
            return rs;
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int editBalance(int userID, float newBalance) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE db_users SET balance = ? WHERE idusers = ?");
            stm.setFloat(1, newBalance);
            stm.setInt(2, userID);
            int rs = stm.executeUpdate();
            return rs;
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int createUser(String firstName, String lastName, String username, String password, int isTeacher, String address, String studentGrade, String studentDoB,
                                 int tuitionFee, float balance, int lessonCount, int lessonDuration, String emailAddress, String phoneNumber, String hash) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("INSERT INTO db_users (firstName, lastName, username, password, isTeacher, address," +
                    "grade, DoB, tuitionFee, balance, lessonCount, lessonDuration, emailAddress, contactNumber, hash)"+
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, username);
            stm.setString(4, password);
            stm.setInt(5, isTeacher);
            stm.setString(6, address);
            stm.setString(7, studentGrade);
            stm.setString(8, studentDoB);
            stm.setInt(9, tuitionFee);
            stm.setFloat(10, balance);
            stm.setInt(11, lessonCount);
            stm.setInt(12, lessonDuration);
            stm.setString(13, emailAddress);
            stm.setString(14, phoneNumber);
            stm.setString(15, hash);
            return  stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int editUser(int userID, String firstName, String lastName, String username, String password, String address, String studentGrade,
                               String studentDoB, int tuitionFee, int lessonDuration, int lessonCount, String emailAddress, String phoneNumber, String hash) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE db_users SET firstName = ?, lastName = ?, username = ?, password = ?, address = ?, grade = ?, DoB = ?," +
                    " tuitionFee = ?, lessonDuration = ?, emailAddress = ?, contactNumber = ?, lessonCount = ?, hash = ? WHERE idusers = ? ");
            stm.setString(1, firstName);
            stm.setString(2, lastName);
            stm.setString(3, username);
            stm.setString(4, password);
            stm.setString(5, address);
            stm.setString(6, studentGrade);
            stm.setString(7, studentDoB);
            stm.setInt(8, tuitionFee);
            stm.setInt(9, lessonDuration);
            stm.setString(10, emailAddress);
            stm.setString(11, phoneNumber);
            stm.setInt(12, lessonCount);
            stm.setInt(14, userID);
            stm.setString(13, hash);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int editIsCounted(int isCounted, int lessonID) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE lessons SET isCounted = ? WHERE idlessons = ?");
            stm.setInt(1, isCounted);
            stm.setInt(2, lessonID);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int editLessonCount(int lessonCount, int userID) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE db_users SET lessonCount = ? WHERE idusers = ?");
            stm.setInt(1, lessonCount);
            stm.setInt(2, userID);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int createLesson(String date, String time, int duration, int idusers, int isApproved) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("INSERT INTO lessons (date, time, duration, idusers2, isApproved) VALUES(?, ?, ?, ?, ?)");
            stm.setString(1, date);
            stm.setString(2, time);
            stm.setInt(3, duration);
            stm.setInt(4, idusers);
            stm.setInt(5, isApproved);
            return  stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int editLesson(String date, String time, int duration, int isApproved, int lessonID) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE lessons SET date = ?, time= ?, duration= ?, isApproved = ? WHERE idlessons = ?");
            stm.setString(1, date);
            stm.setString(2, time);
            stm.setInt(3, duration);
            stm.setInt(4, isApproved);
            stm.setInt(5, lessonID);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int approveLesson(int isApproved, int lessonID) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("UPDATE lessons SET isApproved = ? WHERE idlessons = ?");
            stm.setInt(1, isApproved);
            stm.setInt(2, lessonID);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int deleteLesson(int lessonID) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("DELETE FROM lessons WHERE idlessons = ?");
            stm.setInt(1, lessonID);
            return stm.executeUpdate();
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return -1;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //endregion

    //region User
    static int userID;
    public static boolean userExists(String username) { // checking if username from login form exists will use username from outside method
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("SELECT * FROM db_users WHERE username = ?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                userID = Integer.parseInt(rs.getString("idusers"));
                return true; // user already exists
            } else {
                return false; // does not exist — continue
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return true;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static User getSessionUser(int iduser) { // trying to retrieve result set
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            stm = con.prepareStatement("SELECT * FROM db_users WHERE idusers = ?");
            stm.setInt(1, iduser);
            rs = stm.executeQuery();
            while(rs.next()){
                User u = new User(iduser, rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                        rs.getString("password"), rs.getInt("isTeacher"), rs.getString("address"),
                        rs.getString("grade"), rs.getString("DoB"), rs.getInt("tuitionFee"),
                        rs.getFloat("balance"), rs.getInt("lessonCount"), rs.getInt("lessonDuration"),
                        rs.getString("emailAddress"), rs.getString("contactNumber"));
                return u;
            }
        } catch (Exception e) { // what is log
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static int loginFunction(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            String sql = "SELECT * FROM sys.db_users WHERE (username = ? AND hash = ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int userID = (rs.getInt("idusers"));
                return userID;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return -1;
    }

    public static List<User> doGetStudentSearch(String searchValue) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM db_users WHERE isTeacher != 1 AND (firstName LIKE ? OR lastName LIKE ?)");
            searchValue = searchValue + '%';
            statement.setString(1, searchValue);
            statement.setString(2, searchValue);
            resultSet = statement.executeQuery();
            List<User> searchedUsers = new ArrayList<>();
            while (resultSet.next()) {
                int userID = resultSet.getInt("idusers");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String studentGrade = resultSet.getString("grade");
                String studentDoB = resultSet.getString("DoB");
                int tuitionFee = resultSet.getInt("tuitionFee");
                float balance = resultSet.getFloat("balance");
                int lessonCount = resultSet.getInt("lessonCount");
                int lessonDuration = resultSet.getInt("lessonDuration");
                String emailAddress = resultSet.getString("emailAddress");
                String phoneNumber = resultSet.getString("contactNumber");
                User searchedUserL = new User(userID, firstName, lastName, username, password, address, studentGrade, studentDoB, tuitionFee, balance, lessonCount, lessonDuration, emailAddress, phoneNumber);
                searchedUsers.add(searchedUserL);
            }
            return searchedUsers;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    //----------------------------user details to html
    public static List<User> doGetStudent() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM db_users WHERE isTeacher != 1");
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int userID = resultSet.getInt("idusers");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String studentGrade = resultSet.getString("grade");
                String studentDoB = resultSet.getString("DoB");
                int tuitionFee = resultSet.getInt("tuitionFee");
                float balance = resultSet.getFloat("balance");
                int lessonCount = resultSet.getInt("lessonCount");
                int lessonDuration = resultSet.getInt("lessonDuration");
                String emailAddress = resultSet.getString("emailAddress");
                String phoneNumber = resultSet.getString("contactNumber");
                User userL = new User(userID, firstName, lastName, username, password, address, studentGrade, studentDoB, tuitionFee, balance, lessonCount, lessonDuration, emailAddress, phoneNumber);
                users.add(userL);
            }
            return users;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // endregion

    //region Lesson
    //- - - - - - - - - - - - - - - -  LESSON - - - - - - - - - - - - - - - -

    //----------------------------lesson details to html -------------------------------------------------

    public static List<Lesson> doGetApprovedLessonToday() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            LocalDate today = LocalDate.now();
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE isApproved = 1 AND date = ? ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            statement.setString(1, today.toString());
            resultSet = statement.executeQuery();
            List<Lesson> approvedLessons = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                String firstName = null;
                String lastName = null;
                String address = null;
                String contact = null;
                int duration = 0;
                Lesson approvedLesson = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved, firstName, lastName, address, contact, duration);
                approvedLessons.add(approvedLesson);
            }
            return approvedLessons;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static List<Lesson> doGetAllApprovedLessons() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE isApproved = 1 ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') DESC");
            resultSet = statement.executeQuery();
            List<Lesson> approvedLessons = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                String firstName = null;
                String lastName = null;
                String address = null;
                String contact = null;
                int duration = 0;
                Lesson approvedLesson = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved, firstName, lastName, address, contact, duration);
                approvedLessons.add(approvedLesson);
            }
            return approvedLessons;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static List<Lesson> doGetApprovedLessonTomorrow() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE isApproved = 1 AND date = ? ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            statement.setString(1, tomorrow.toString());
            resultSet = statement.executeQuery();
            List<Lesson> approvedLessonsTomorrow = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                String firstName = null;
                String lastName = null;
                String address = null;
                String contact = null;
                int duration = 0;
                Lesson approvedLessonTomorrow = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved, firstName, lastName, address, contact, duration);
                approvedLessonsTomorrow.add(approvedLessonTomorrow);
            }
            return approvedLessonsTomorrow;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public static List<Lesson> doGetUnapprovedLesson() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE isApproved = 0 ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            resultSet = statement.executeQuery();
            List<Lesson> unapprovedLessons = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                String firstName = null;
                String lastName = null;
                String address = null;
                String contact = null;
                int duration = 0;
                Lesson unapprovedLesson = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved, firstName, lastName, address, contact, duration);
                unapprovedLessons.add(unapprovedLesson);
            }
            return unapprovedLessons;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    // using lesson user id to find necessary user details
    public static User findLessonStudent(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM db_users WHERE idusers = ?");
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();
            User lessonUser = null;
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String contact = resultSet.getString("contactNumber");
                int duration = resultSet.getInt("lessonDuration");
                lessonUser = new User(firstName, lastName, address, contact, duration);
            }
            return lessonUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean validateTime(String date, String time, int duration, int lessonID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE date = ?");
            statement.setString(1, date);
            resultSet = statement.executeQuery();
            Date newStartTime = new SimpleDateFormat("HH:mm").parse(time);
            long startTimeMinutes = newStartTime.getTime() / 60000;
            Date newEndTime = new Date((startTimeMinutes + duration) * 60000);

            boolean invalid = false;
            while (resultSet.next() && invalid == false) {
                int rsID = resultSet.getInt("idlessons");
                String rsTime = resultSet.getString("time");
                int rsDuration = resultSet.getInt("duration");
                Date rsNewStartTime = new SimpleDateFormat("HH:mm").parse(rsTime);
                long rsStartTimeMinutes = rsNewStartTime.getTime() / 60000;
                Date rsNewEndTime = new Date((rsStartTimeMinutes + rsDuration) * 60000);
                if (((newStartTime.after(rsNewStartTime) && newStartTime.before(rsNewEndTime)) || (newEndTime.after(rsNewStartTime) && newEndTime.before(rsNewEndTime))
                        || (rsNewStartTime.after(newStartTime) && rsNewStartTime.before(newEndTime)) || (rsNewEndTime.after(newStartTime) && rsNewEndTime.before(newEndTime)) ||
                        (rsNewEndTime.equals(newEndTime)) || (rsNewStartTime.equals(newStartTime)) || (rsNewStartTime.equals(newEndTime)) || (rsNewEndTime.equals(newStartTime))) && (rsID != lessonID)) {
                    invalid = true;
                    return false;
                }

            }
            if (invalid == false) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public static List<Lesson> doGetLessonSearch(String searchValue) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            char[] chars = searchValue.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            int sb1 = sb.length();
            if (sb1 != 0) {
                statement = conn.prepareStatement("SELECT * FROM lessons WHERE (date LIKE ? AND isApproved = 1) ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
                searchValue = '%' + searchValue + '%';
                statement.setString(1, searchValue);
                resultSet = statement.executeQuery();
                List<Lesson> searchedLessons = new ArrayList<>();
                while (resultSet.next()) {
                    int lessonID = resultSet.getInt("idlessons");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    int duration = resultSet.getInt("duration");
                    int idusers = resultSet.getInt("idusers2");
                    int isApproved = resultSet.getInt("isApproved");
                    Lesson searchedDate = new Lesson(lessonID, date, time, duration, idusers, isApproved);
                    searchedLessons.add(searchedDate);
                }
                return searchedLessons;
            } else {
                statement = conn.prepareStatement("SELECT * FROM db_users WHERE isTeacher != 1 AND (firstName LIKE ? OR lastName LIKE ?)");
                searchValue = searchValue + '%';
                statement.setString(1, searchValue);
                statement.setString(2, searchValue);
                resultSet = statement.executeQuery();
                List<Lesson> searchedLessons = new ArrayList<>();
                while (resultSet.next()) {
                    int userID = resultSet.getInt("idusers");
                    Lesson lesson = DAO.findStudentLesson(userID);
                    if (lesson != null) {
                        searchedLessons.add(lesson);
                    }
                }
                return searchedLessons;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static Lesson findStudentLesson(int userID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE (idusers2 = ? AND isApproved!=0)");
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();
            Lesson userLesson = null;
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                int duration = resultSet.getInt("duration");
                int lessonID = resultSet.getInt("idlessons");
                int userID1 = resultSet.getInt("idusers2");
                userLesson = new Lesson(lessonID, date, time, duration, userID1);
            }
            return userLesson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //endregion

    //region Calendar
    //- - - - - - - - - - - - - - - -  Calendar - - - - - - - - - - - - - - - -
    public static ArrayList<Lesson> getDayLessons(String date) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE (date = ?) ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            statement.setString(1, date);
            resultSet = statement.executeQuery();
            Lesson dayLesson = null;
            ArrayList<Lesson> dayLessons = new ArrayList<>();
            while (resultSet.next()) {
                String time = resultSet.getString("time");
                int duration = resultSet.getInt("duration");
                int lessonID = resultSet.getInt("idlessons");
                int userID1 = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                dayLesson = new Lesson(lessonID, date, time, duration, userID1, isApproved);
                dayLessons.add(dayLesson);
            }
            return dayLessons;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<String> getUsernameList() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT username FROM db_users WHERE isTeacher != 1");
            resultSet = statement.executeQuery();
            ArrayList<String> usernameList = new ArrayList<>();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                usernameList.add("'" + username + "'");
            }
            return usernameList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getUserID(String username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int userID = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT idusers FROM db_users WHERE username=?");
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userID = resultSet.getInt("idusers");
            }
            return userID;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //endregion

    //region Student Functions

    public static List<Lesson> doGetUserApprovedLessons(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE (idusers2 = ? AND isApproved = 1) ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();
            List<Lesson> approvedLessons = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                Lesson approvedLesson = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved);
                approvedLessons.add(approvedLesson);
            }
            return approvedLessons;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static List<Lesson> doGetUserUnapprovedLessons(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE (idusers2 = ? AND isApproved = 0) ORDER BY STR_TO_DATE(CONCAT(date, ' ', time), '%Y-%m-%d %H:%i') ASC");
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();
            List<Lesson> unapprovedLessons = new ArrayList<>();
            while (resultSet.next()) {
                int lessonID = resultSet.getInt("idlessons");
                String lessonDate = resultSet.getString("date");
                String lessonTime = resultSet.getString("time");
                int lDuration = resultSet.getInt("duration");
                int uID = resultSet.getInt("idusers2");
                int isApproved = resultSet.getInt("isApproved");
                Lesson unapprovedLesson = new Lesson(lessonID, lessonDate, lessonTime, lDuration, uID, isApproved);
                unapprovedLessons.add(unapprovedLesson);
            }
            return unapprovedLessons;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }


    //endregion

    //region Lesson Counter & Balance Updater
    public static ArrayList<User> doGetUserList() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM db_users WHERE (isTeacher = 0)");
            resultSet = statement.executeQuery();
            ArrayList<User> userArrayList = new ArrayList<>();
            while (resultSet.next()) {
                int userID = resultSet.getInt("idusers");
                int tuitionFee = resultSet.getInt("tuitionFee");
                float balance = resultSet.getFloat("balance");
                int lessonCount = resultSet.getInt("lessonCount");
                User u = new User(userID, balance, tuitionFee, lessonCount);
                userArrayList.add(u);
            }
            return userArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<Lesson> findUserIDLessonsNotCounted(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT * FROM lessons WHERE (idusers2 = ? AND isApproved!=? AND isCounted = ?)");
            statement.setInt(1, userID);
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            resultSet = statement.executeQuery();
            ArrayList<Lesson> userLessonList = new ArrayList<>();
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");
                int lessonID = resultSet.getInt("idlessons");
                Lesson userLesson = new Lesson(lessonID, date, time);
                userLessonList.add(userLesson);
            }
            return userLessonList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getLessonCount(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT lessonCount FROM db_users WHERE idusers = '" + userID + "'");
            resultSet = statement.executeQuery();
            int lc = 0;
            while (resultSet.next()) {
                lc = resultSet.getInt("lessonCount");
            }
            return lc;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static float getBalance(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT balance FROM db_users WHERE idusers = '" + userID + "'");
            resultSet = statement.executeQuery();
            float balance = 0;
            while (resultSet.next()) {
                balance = resultSet.getFloat("balance");
            }
            return balance;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getTuitionFee(int userID) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(mySqlConnect + myDB + "?useSSL=false", dbUser, dbPass);
            statement = conn.prepareStatement("SELECT tuitionFee FROM db_users WHERE idusers = '" + userID + "'");
            resultSet = statement.executeQuery();
            int fee = 0;
            while (resultSet.next()) {
                fee = resultSet.getInt("tuitionFee");
            }
            return fee;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //endregion
}