package com.gdc.isfacademy.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrieveDailyConsResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("student")
    @Expose
    private List<StudentRetriveDailyCons> studentRetriveDailyConsumption;
    @SerializedName("avg")
    @Expose
    private List<AvgRetriveDailyConsumption> avgRetriveDailyConsumptions;
    @SerializedName("target")
    @Expose
    private Object target;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<StudentRetriveDailyCons> getStudent() {
        return studentRetriveDailyConsumption;
    }

    public void setStudent(List<StudentRetriveDailyCons> student) {
        this.studentRetriveDailyConsumption = student;
    }

    public List<AvgRetriveDailyConsumption> getAvg() {
        return avgRetriveDailyConsumptions;
    }

    public void setAvg(List<AvgRetriveDailyConsumption> avg) {
        this.avgRetriveDailyConsumptions = avg;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

}