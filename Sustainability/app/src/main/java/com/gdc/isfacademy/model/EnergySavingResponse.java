package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



@SuppressWarnings("ALL")
public class EnergySavingResponse {

    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("currentCons")
    @Expose
    private CurrentCons currentCons;
    @SerializedName("lastWeekCons")
    @Expose
    private LastWeekCons lastWeekCons;

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

    public CurrentCons getCurrentCons() {
        return currentCons;
    }

    public void setCurrentCons(CurrentCons currentCons) {
        this.currentCons = currentCons;
    }

    public LastWeekCons getLastWeekCons() {
        return lastWeekCons;
    }

    public void setLastWeekCons(LastWeekCons lastWeekCons) {
        this.lastWeekCons = lastWeekCons;
    }

    public class CurrentCons implements Serializable {

        @SerializedName("value")
        @Expose
        private Float value;
        @SerializedName("lastUpdateTs")
        @Expose
        private Long lastUpdateTs;
        @SerializedName("lastUpdateDate")
        @Expose
        private String lastUpdateDate;
        @SerializedName("unit")
        @Expose
        private String unit;

        public Float getValue() {
            return value;
        }

        public void setValue(Float value) {
            this.value = value;
        }

        public Long getLastUpdateTs() {
            return lastUpdateTs;
        }

        public void setLastUpdateTs(Long lastUpdateTs) {
            this.lastUpdateTs = lastUpdateTs;
        }

        public String getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(String lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

    }

    public class LastWeekCons {

        @SerializedName("value")
        @Expose
        private Float value;
        @SerializedName("lastUpdateTs")
        @Expose
        private Long lastUpdateTs;
        @SerializedName("lastUpdateDate")
        @Expose
        private String lastUpdateDate;
        @SerializedName("unit")
        @Expose
        private String unit;

        public Float getValue() {
            return value;
        }

        public void setValue(Float value) {
            this.value = value;
        }

        public Long getLastUpdateTs() {
            return lastUpdateTs;
        }

        public void setLastUpdateTs(Long lastUpdateTs) {
            this.lastUpdateTs = lastUpdateTs;
        }

        public String getLastUpdateDate() {
            return lastUpdateDate;
        }

        public void setLastUpdateDate(String lastUpdateDate) {
            this.lastUpdateDate = lastUpdateDate;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

    }
}
