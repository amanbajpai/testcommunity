package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 18/5/18.
 */

@SuppressWarnings("ALL")
public class StudentFootPrintResponse extends CommonResponse {

    public CO2 co2;
    public Tree tree;

    public CO2 getCo2() {
        return co2;
    }

    public void setCo2(CO2 co2) {
        this.co2 = co2;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public class CO2 {
        String value;
        String lastUpdateTs;
        String lastUpdateDate;
        String unit;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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

    public class Tree {
        String value;
        String lastUpdateTs;
        String lastUpdateDate;
        String unit;
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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


}
