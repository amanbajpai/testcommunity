package com.gdc.isfacademy.database;

import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.ChallangeRankListDao;

import java.util.ArrayList;
import java.util.List;

   /*
    *
    *
    * Class responsible for "All DATABASE CRUD OPERATIONS" ,
    * Insert or update all StudentRetriveDailyCons Housing data class.
    *
    *
    * */

@SuppressWarnings("ALL")
public class DbHalper {

    public static DbHalper dbHalper;


    public static DbHalper getInstance() {
        if (dbHalper == null) {
            dbHalper = new DbHalper();
        }
        return dbHalper;
    }


    /*
    *
    *
    * Method Used for inserting student ranking
    * according to student type "Friend and House".
    *
    *
    * */
    public synchronized void insertChallangeRankingList(ArrayList<ChallangeRankList> challangeRankLists, String type) {
        List<ChallangeRankList> getChallangeList = ISFApp.getAppInstance().getDaoSession().getChallangeRankListDao()
                .queryBuilder().where(ChallangeRankListDao.Properties.Type.eq(type)).list();
        ISFApp.getAppInstance().getDaoSession().getChallangeRankListDao().deleteInTx(getChallangeList);
        ISFApp.getAppInstance().getDaoSession().getChallangeRankListDao().
                insertInTx(challangeRankLists);
    }






}