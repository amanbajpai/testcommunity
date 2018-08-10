package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvgRetriveDailyConsumption {

    @SerializedName("ts")
    @Expose
    private Integer ts;
    @SerializedName("value")
    @Expose
    private Double value;

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
