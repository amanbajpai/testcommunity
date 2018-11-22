package com.gdc.isfacademy.model;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 19/11/18.
 */

public class ParentFriendListResponse extends CommonResponse {
    ArrayList<FriendRequestResponse>requestFriend;
    ArrayList<TotalFriendResponse>friend;

    public ArrayList<FriendRequestResponse> getRequestFriend() {
        return requestFriend;
    }

    public void setRequestFriend(ArrayList<FriendRequestResponse> requestFriend) {
        this.requestFriend = requestFriend;
    }

    public ArrayList<TotalFriendResponse> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<TotalFriendResponse> friend) {
        this.friend = friend;
    }
}
