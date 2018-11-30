package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


@Entity(
        // Whether an all properties constructor should be generated.
        // A no-args constructor is always required.
        generateConstructors = true,

        // Whether getters and setters for properties should be generated if missing.
        generateGettersSetters = true
)
public class ChallangeRankList {

    @Id(autoincrement = true)
    Long id;
    @SerializedName("studentId")
    @Expose
    String studentId;

    @SerializedName("studentName")
    @Expose
    String studentName;

    @SerializedName("house")
    @Expose
    String house;

    @SerializedName("value")
    @Expose
    String value;
    @SerializedName("lastUpdateDate")
    @Expose
    String lastUpdateDate;

    @SerializedName("lastUpdateTs")
    @Expose
    String lastUpdateTs;

    @SerializedName("unit")
    @Expose
    String unit;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("checkIsme")
    @Expose
    boolean checkIsme = false;

    String ranking;


    int finalRankStudent;


    public int getFinalRankStudent() {
        return finalRankStudent;
    }

    public void setFinalRankStudent(int finalRankStudent) {
        this.finalRankStudent = finalRankStudent;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @Generated(hash = 355939908)
    public ChallangeRankList(Long id, String studentId, String studentName, String house, String value,
            String lastUpdateDate, String lastUpdateTs, String unit, String type, boolean checkIsme, String ranking,
            int finalRankStudent) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.house = house;
        this.value = value;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateTs = lastUpdateTs;
        this.unit = unit;
        this.type = type;
        this.checkIsme = checkIsme;
        this.ranking = ranking;
        this.finalRankStudent = finalRankStudent;
    }

    @Generated(hash = 20761602)
    public ChallangeRankList() {
    }


    public boolean isCheckIsme() {
        return checkIsme;
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

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateTs() {
        return lastUpdateTs;
    }

    public void setLastUpdateTs(String lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean getCheckIsme() {
        return this.checkIsme;
    }

    public void setCheckIsme(boolean checkIsme) {
        this.checkIsme = checkIsme;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
