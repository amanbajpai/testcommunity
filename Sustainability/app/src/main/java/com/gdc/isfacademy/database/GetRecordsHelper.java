package com.gdc.isfacademy.database;

import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.ChallangeRankListDao;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 10/5/18.
 */

public class GetRecordsHelper extends DbHalper {



    /*
    *
    *
    *
    * get student ranking list according to type
    *
    *
    * */

    public synchronized ArrayList<ChallangeRankList> getRankingAccrodingToType(String type) {

        ArrayList<ChallangeRankList> challangeRankLists = (ArrayList<ChallangeRankList>) ISFApp.getAppInstance().getDaoSession().getChallangeRankListDao()
                .queryBuilder().where(ChallangeRankListDao.Properties.Type.eq(type)).list();

        return challangeRankLists;
    }
}
