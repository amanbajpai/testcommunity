package com.gdc.isfacademy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;

/**
 * Created by ashishthakur on 10/4/18.
 */

public class BootCompleteReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            MyPref.getInstance(context).setAlarmTime("0");
            MyPref.getInstance(context).setHasAlarmSet(false);
            ProjectUtil.setAlarm(context);
            if(MyPref.getInstance(context).readIntegerPrefs(MyPref.QUIZ_COUNT)==0){
                ProjectUtil.setAlarmReminder(context);
            }
        }
    }
}