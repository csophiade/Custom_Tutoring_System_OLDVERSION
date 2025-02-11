package com.springboot.webapp;

public class Lesson {
    private int lessonID;
    private String lessonDate;
    private String lessonTime;
    private int lDuration;
    private int uID;
    private int isApproved;
    private String userFirstName;
    private String userLastName;
    private String userAddress;
    private String userContact;
    private int userDuration;
    private int isCounted;


    public Lesson(int lessonID, String lessonDate, String lessonTime, int lDuration, int uID, int isApproved, String userFirstName, String userLastName, String userAddress, String userContact, int userDuration){
        this.lessonID = lessonID;
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.lDuration = lDuration;
        this.uID = uID;
        this.isApproved = isApproved;
    }

    public Lesson(int lessonID, String date, String time, int duration, int idusers, int isApproved) {
        this.lessonID = lessonID;
        this.lessonDate = date;
        this.lessonTime = time;
        this.lDuration = duration;
        this.uID = idusers;
        this.isApproved = isApproved;
    }

    public Lesson(int lessonID, String date, String time, int duration, int userID) {
        this.lessonID = lessonID;
        this.lessonDate = date;
        this.lessonTime = time;
        this.lDuration = duration;
        this.uID = userID;
    }

    public Lesson(int lessonID, String lessonDate, String lessonTime){
        this.lessonID = lessonID;
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
    }

    // region Getters & Setters
    public int getLessonID() {
        return lessonID;
    }
    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getLessonDate() {
        return lessonDate;
    }
    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }
    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public int getlDuration() {
        return lDuration;
    }
    public void setlDuration(int lDuration) {
        this.lDuration = lDuration;
    }

    public int getuID() {
        return uID;
    }
    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContact() {
        return userContact;
    }
    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public int getUserDuration() {
        return userDuration;
    }
    public void setUserDuration(int userDuration) {
        this.userDuration = userDuration;
    }
    //endregion
}
