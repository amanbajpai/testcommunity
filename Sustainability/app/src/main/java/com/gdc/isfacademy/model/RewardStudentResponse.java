package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardStudentResponse {
    @SerializedName("isfRewardsId")
    @Expose
    private String isfRewardsId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("expTs")
    @Expose
    private String expTs;

    boolean isItemOpen=false;

    public boolean isItemOpen() {
        return isItemOpen;
    }

    public void setItemOpen(boolean itemOpen) {
        isItemOpen = itemOpen;
    }

    public String getIsfRewardsId() {
        return isfRewardsId;
    }

    public void setIsfRewardsId(String isfRewardsId) {
        this.isfRewardsId = isfRewardsId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpTs() {
        return expTs;
    }

    public void setExpTs(String expTs) {
        this.expTs = expTs;
    }

}
