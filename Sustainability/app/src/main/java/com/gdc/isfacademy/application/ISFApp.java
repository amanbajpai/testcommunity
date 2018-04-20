package com.gdc.isfacademy.application;

import android.app.Application;

import com.gdc.isfacademy.model.DaoMaster;
import com.gdc.isfacademy.model.DaoSession;
import com.gdc.isfacademy.netcom.Api;
import com.gdc.isfacademy.netcom.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.greendao.database.Database;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class ISFApp extends Application {
    static ISFApp appInstance;
    private Retrofit retrofit;
    private Api api;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = ISFApp.this;
        retrofit = getApiClient(ApiConstants.API_SERVER_URL);
        api = retrofit.create(Api.class);



        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "isf-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public static ISFApp getAppInstance() {
        if (appInstance == null) {
            appInstance = new ISFApp();
        }
        return appInstance;
    }
    public Api getApi() {
        return api;
    }



    private Retrofit getApiClient(String baseUrl) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
