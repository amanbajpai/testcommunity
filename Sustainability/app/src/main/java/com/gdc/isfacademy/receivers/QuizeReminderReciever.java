package com.gdc.isfacademy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.fragment.HomeFragment;



public class QuizeReminderReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        HomeFragment.isQuizSubmited = false;
        if (MyPref.getInstance(context).readIntegerPrefs(MyPref.QUIZ_COUNT) < 0) {
            ProjectUtil.sendNotification(ISFApp.getAppInstance().getApplicationContext().getString(R.string.txt_quize_reminder));
        }
    }
}
