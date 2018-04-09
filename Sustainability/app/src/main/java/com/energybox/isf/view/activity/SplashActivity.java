package com.energybox.isf.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.energybox.isf.R;
import com.energybox.isf.utils.AppConstants;

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
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
