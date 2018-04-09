package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 5/4/18.
 */

public class RewardListResponse {
    boolean isItemOpen=false;


    public RewardListResponse(boolean isItemOpen) {
        this.isItemOpen = isItemOpen;
    }

    public boolean isItemOpen() {
        return isItemOpen;
    }

    public void setItemOpen(boolean itemOpen) {
        isItemOpen = itemOpen;
    }
}
