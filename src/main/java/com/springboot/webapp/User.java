package com.springboot.webapp;

import javax.servlet.http.HttpServletRequest;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int isTeacher;
    private String address;
    private String studentGrade;
    private String studentDoB;
    private int tuitionFee;
    private float balance;
    private int lessonCount;
    private int lessonDuration;
    private String emailAddress;
    private String contactNumber;

    public User(int userID, String firstName, String lastName, String username, String password, int isTeacher, String address, String studentGrade,
                String studentDoB, int tuitionFee, float balance, int lessonCount, int lessonDuration, String emailAddress, String phoneNumber) {
            this.userID = userID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.isTeacher = isTeacher;
            this.address = address;
            this.studentGrade = studentGrade;
            this.studentDoB = studentDoB;
            this.tuitionFee = tuitionFee;
            this.balance = balance;
            this.lessonCount = lessonCount;
            this.lessonDuration = lessonDuration;
            this.emailAddress = emailAddress;
            this.contactNumber = phoneNumber;
    }

    public User(int userID, String firstName, String lastName, String username, String password, String address, String studentGrade, String studentDoB,
                int tuitionFee, float balance, int lessonCount, int lessonDuration, String emailAddress, String phoneNumber) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.studentGrade = studentGrade;
        this.studentDoB = studentDoB;
        this.tuitionFee = tuitionFee;
        this.balance = balance;
        this.lessonCount = lessonCount;
        this.lessonDuration = lessonDuration;
        this.emailAddress = emailAddress;
        this.contactNumber = phoneNumber;
    }

    public User(String firstName, String lastName, String address, String contactNumber, int lessonDuration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.lessonDuration = lessonDuration;
    }

    public User(int userID, float balance, int tuitionFee, int lessonCount){
        this.userID = userID;
        this.balance = balance;
        this.tuitionFee = tuitionFee;
        this.lessonCount = lessonCount;
    }

    //region getters and setters
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsTeacher() {
        return isTeacher;
    }
    public void setIsTeacher(int isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentGrade() {
        return studentGrade;
    }
    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentDoB() {
        return studentDoB;
    }
    public void setStudentDoB(String studentDoB) {
        this.studentDoB = studentDoB;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }
    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getLessonCount() {
        return lessonCount;
    }
    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getLessonDuration() {
        return lessonDuration;
    }
    public void setLessonDuration(int lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    //endregion
}
