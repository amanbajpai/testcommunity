package com.gdc.isfacademy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.ProjectUtil;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        ProjectUtil.checkTime(this);
    }
}
