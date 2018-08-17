package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 9/4/18.
 */

@SuppressWarnings("ALL")
public class FriendsBeanList {
    String name;
    boolean isRequestSend=false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequestSend() {
        return isRequestSend;
    }

    public void setRequestSend(boolean requestSend) {
        isRequestSend = requestSend;
    }

    public FriendsBeanList(String name) {
        this.name = name;
    }
}
