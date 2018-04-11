package com.gdc.isfacademy.application;

import android.app.Application;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class Sustainability extends Application {
    static Sustainability appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = Sustainability.this;

    }

    public static Sustainability getAppInstance() {
        if (appInstance == null) {
            appInstance = new Sustainability();
        }
        return appInstance;
    }

}
