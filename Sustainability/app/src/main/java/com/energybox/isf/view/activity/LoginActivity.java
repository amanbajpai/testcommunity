package com.energybox.isf.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.energybox.isf.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatTextView loginBtn, forgotPasswordBtn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
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
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.forgot_password_btn:
                break;
        }
    }
}
