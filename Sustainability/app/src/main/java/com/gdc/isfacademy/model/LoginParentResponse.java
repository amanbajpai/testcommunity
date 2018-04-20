package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 18/4/18.
 */

public class LoginParentResponse extends CommonResponse {
    StudentInfo studentInfo;

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }
}
