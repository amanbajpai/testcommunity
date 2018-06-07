package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 7/6/18.
 */

public class StudentSavedCostResponse extends CommonResponse {
    String saveEnergy;
    String saveCost;
    String comparison;
    String lastUpdateTs;
    String lastUpdateDate;
    String unit;

    public String getSaveEnergy() {
        return saveEnergy;
    }

    public void setSaveEnergy(String saveEnergy) {
        this.saveEnergy = saveEnergy;
    }

    public String getSaveCost() {
        return saveCost;
    }

    public void setSaveCost(String saveCost) {
        this.saveCost = saveCost;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getLastUpdateTs() {
        return lastUpdateTs;
    }

    public void setLastUpdateTs(String lastUpdateTs) {
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
