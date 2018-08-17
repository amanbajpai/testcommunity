package com.gdc.isfacademy.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        navigateScreen();

    }



    public void navigateScreen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(AppConstants.SPLASH_HOLD_TIME);
                    if(MyPref.getInstance(mContext).readPrefs(AppConstants.STUDENT_KEY).
                            equalsIgnoreCase("")){
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(mContext, HomeActivity.class));
                        finish();
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
