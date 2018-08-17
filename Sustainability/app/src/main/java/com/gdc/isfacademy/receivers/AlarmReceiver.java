package com.gdc.isfacademy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.utils.ProjectUtil;



@SuppressWarnings("ALL")
public class AlarmReceiver extends BroadcastReceiver {

    private final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        ProjectUtil.checkTime(context);
        ProjectUtil.sendNotification(ISFApp.getAppInstance().getApplicationContext().getString(R.string.txt_daily_challange));
        Log.e(TAG, "AlarmReceiver:onReceive() called");
    }
}
