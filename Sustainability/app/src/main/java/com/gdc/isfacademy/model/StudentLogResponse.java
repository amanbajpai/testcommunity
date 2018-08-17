package com.gdc.isfacademy.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("ALL")
public class StudentLogResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("logs")
    @Expose
    private List<LogStudentResponse> logStudentResponses = null;

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

    public List<LogStudentResponse> getLogStudentResponses() {
        return logStudentResponses;
    }

    public void setLogStudentResponses(List<LogStudentResponse> logs) {
        this.logStudentResponses = logs;
    }

}
