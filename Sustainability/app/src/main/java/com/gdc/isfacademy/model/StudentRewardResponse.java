package com.gdc.isfacademy.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("ALL")
public class StudentRewardResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("rewards")
    @Expose
    private List<RewardStudentResponse> rewards;

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

    public List<RewardStudentResponse> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardStudentResponse> rewards) {
        this.rewards = rewards;
    }
}