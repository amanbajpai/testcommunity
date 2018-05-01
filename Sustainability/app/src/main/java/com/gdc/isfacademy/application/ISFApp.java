package com.gdc.isfacademy.application;

import android.app.Application;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.googleanalitics.AnalyticsTrackers;
import com.gdc.isfacademy.model.DaoMaster;
import com.gdc.isfacademy.model.DaoSession;
import com.gdc.isfacademy.netcom.Api;
import com.gdc.isfacademy.netcom.ApiConstants;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
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

        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);



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


    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    /***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     */
    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();

        // Set screen name.
        t.setScreenName(screenName);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /***
     * Tracking exception
     *
     * @param e exception to be tracked
     */
    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(
                            new StandardExceptionParser(this, null)
                                    .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build()
            );
        }
    }

    /***
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     */
    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().
                setCategory(category).
                setAction(action).
                setLabel(label).build());
    }
}
