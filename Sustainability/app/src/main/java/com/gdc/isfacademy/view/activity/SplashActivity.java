package com.gdc.isfacademy.view.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;

@SuppressWarnings("ALL")
public class SplashActivity extends AppCompatActivity {
    Context mContext;
    public static boolean isMapStatusFromSplash=false;



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
                    if(MyPref.getInstance(mContext).readPrefs(AppConstants.IS_STATUS_MAP).equalsIgnoreCase(AppConstants.OTHER)){
                        isMapStatusFromSplash=true;
                    }
                    else {
                        isMapStatusFromSplash=false;
                    }
                    if(MyPref.getInstance(mContext).readPrefs(AppConstants.STUDENT_KEY).
                            equalsIgnoreCase("")){
                        startActivity(new Intent(mContext, LoginActivity.class), ActivityOptions.makeCustomAnimation(mContext,R.anim.slide_in,R.anim.slide_out).toBundle());
                        finishAfterTransition();
                    }
                    else {
                        startActivity(new Intent(mContext, HomeActivity.class), ActivityOptions.makeCustomAnimation(mContext,R.anim.slide_in,R.anim.slide_out).toBundle());
                        finishAfterTransition();
                    }



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
