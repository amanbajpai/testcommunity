package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 9/4/18.
 */

@SuppressWarnings("ALL")
public class FriendsBeanList {
    String name;
    int viewFlag;

    public int getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(int viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public FriendsBeanList(String name) {
        this.name = name;
    }
}
