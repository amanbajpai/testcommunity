package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogStudentResponse {

    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("count")
    @Expose
    private Double count;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

}
