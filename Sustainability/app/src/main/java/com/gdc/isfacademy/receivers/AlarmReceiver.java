package com.gdc.isfacademy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gdc.isfacademy.utils.ProjectUtil;

/**
 * Created by ashishthakur on 10/4/18.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        ProjectUtil.checkTime(context);
        ProjectUtil.sendNotification();
        ProjectUtil.showToast(context,"Reciever call");

        Log.e(TAG, "AlarmReceiver:onReceive() called");
    }
}
