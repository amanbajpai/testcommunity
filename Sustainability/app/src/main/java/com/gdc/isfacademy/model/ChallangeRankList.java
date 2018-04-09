package com.gdc.isfacademy.model;

/**
 * Created by ashishthakur on 9/4/18.
 */

public class ChallangeRankList {
    String rankPostion;
    String rankPoints;
    String rankerName;

    public ChallangeRankList(String rankPostion, String rankPoints, String rankerName) {
        this.rankPostion = rankPostion;
        this.rankPoints = rankPoints;
        this.rankerName = rankerName;
    }

    public String getRankPostion() {
        return rankPostion;
    }

    public void setRankPostion(String rankPostion) {
        this.rankPostion = rankPostion;
    }

    public String getRankPoints() {
        return rankPoints;
    }

    public void setRankPoints(String rankPoints) {
        this.rankPoints = rankPoints;
    }

    public String getRankerName() {
        return rankerName;
    }

    public void setRankerName(String rankerName) {
        this.rankerName = rankerName;
    }
}
