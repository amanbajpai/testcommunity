package com.gdc.isfacademy.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentBadgeResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("badges")
    @Expose
    private List<BadgeStudentResponse> badgeStudentResponses;

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

    public List<BadgeStudentResponse> getBadgeStudentResponses() {
        return badgeStudentResponses;
    }

    public void setBadgeStudentResponses(List<BadgeStudentResponse> badgeStudentResponses) {
        this.badgeStudentResponses = badgeStudentResponses;
    }

}
