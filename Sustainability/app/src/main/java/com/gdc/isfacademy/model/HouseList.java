package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 28/1/19.
 */

public class HouseList {
    boolean checkIsme = false;
    int finalRankStudent;
    String homeRoomId;
    String house;
    String value;
    boolean isFromHouse=false;

    public boolean isFromHouse() {
        return isFromHouse;
    }

    public void setFromHouse(boolean fromHouse) {
        isFromHouse = fromHouse;
    }

    public boolean isCheckIsme() {
        return checkIsme;
    }

    public void setCheckIsme(boolean checkIsme) {
        this.checkIsme = checkIsme;
    }

    public int getFinalRankStudent() {
        return finalRankStudent;
    }

    public void setFinalRankStudent(int finalRankStudent) {
        this.finalRankStudent = finalRankStudent;
    }

    public String getHomeRoomId() {
        return homeRoomId;
    }

    public void setHomeRoomId(String homeRoomId) {
        this.homeRoomId = homeRoomId;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
