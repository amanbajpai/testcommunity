package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 18/4/18.
 */

@SuppressWarnings("ALL")
public class StudentInfo {
    String studentId;
    String studentName;
    String studentKey;
    String studentHouse;
    String group;


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

    public String getStudentKey() {
        return studentKey;
    }

    public void setStudentKey(String studentKey) {
        this.studentKey = studentKey;
    }

    public String getStudentHouse() {
        return studentHouse;
    }

    public void setStudentHouse(String studentHouse) {
        this.studentHouse = studentHouse;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
