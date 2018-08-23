package com.gdc.isfacademy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class BadgeStudentResponse implements Serializable {

    @SerializedName("badgesType")
    @Expose
    private String badgesType;
    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("badgeAllotedFor")
    @Expose
    private String badgeAllotedFor;

    @SerializedName("resourceIdImage")
    @Expose
    private int resourceIdImage;

    public int getResourceIdImage() {
        return resourceIdImage;
    }

    public void setResourceIdImage(int resourceIdImage) {
        this.resourceIdImage = resourceIdImage;
    }

    public String getBadgeAllotedFor() {
        return badgeAllotedFor;
    }

    public void setBadgeAllotedFor(String badgeAllotedFor) {
        this.badgeAllotedFor = badgeAllotedFor;
    }

    public BadgeStudentResponse(String badgesType, String value) {
        this.badgesType = badgesType;
        this.value = value;
    }
    public BadgeStudentResponse(String badgeAllotedFor) {
        this.badgeAllotedFor = badgeAllotedFor;
    }

    public String getBadgesType() {
        return badgesType;
    }

    public void setBadgesType(String  badgesType) {
        this.badgesType = badgesType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
