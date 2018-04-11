package com.gdc.isfacademy.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    AppCompatTextView loginBtn, forgotPasswordBtn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        ProjectUtil.setAlarm(this);

    }

    public void initViews() {
        mContext = LoginActivity.this;
        loginBtn = (AppCompatTextView) findViewById(R.id.login_with_student_id_btn);
        forgotPasswordBtn = (AppCompatTextView) findViewById(R.id.forgot_password_btn);
        loginBtn.setOnClickListener(this);
        forgotPasswordBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_with_student_id_btn:
                if (MyPref.getInstance(LoginActivity.this).readBooleanPrefs(MyPref.IS_VALID_TIME)) {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ProjectUtil.showInvalidTimeDialog(LoginActivity.this);
                }


                break;
            case R.id.forgot_password_btn:
                break;
        }
    }
}
