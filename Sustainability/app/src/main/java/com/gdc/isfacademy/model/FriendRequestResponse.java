package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 19/11/18.
 */

public class FriendRequestResponse {
    private String studentId;
    private String studentName;
    private String studentHouse;
    private String studentKey;

    public FriendRequestResponse(String studentName) {
        this.studentName = studentName;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentHouse() {
        return studentHouse;
    }

    public void setStudentHouse(String studentHouse) {
        this.studentHouse = studentHouse;
    }

    public String getStudentKey() {
        return studentKey;
    }

    public void setStudentKey(String studentKey) {
        this.studentKey = studentKey;
    }
}
