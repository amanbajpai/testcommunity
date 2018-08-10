package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Badge {

    @SerializedName("badgesType")
    @Expose
    private Integer badgesType;
    @SerializedName("value")
    @Expose
    private Double value;

    public Integer getBadgesType() {
        return badgesType;
    }

    public void setBadgesType(Integer badgesType) {
        this.badgesType = badgesType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
