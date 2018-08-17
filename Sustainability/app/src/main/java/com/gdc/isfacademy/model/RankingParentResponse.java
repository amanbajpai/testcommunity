package com.gdc.isfacademy.model;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 19/4/18.
 */

@SuppressWarnings("ALL")
public class RankingParentResponse extends CommonResponse {
    ArrayList<ChallangeRankList>rankings;

    public ArrayList<ChallangeRankList> getRankings() {
        return rankings;
    }

    public void setRankings(ArrayList<ChallangeRankList> rankings) {
        this.rankings = rankings;
    }
}
