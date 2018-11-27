package com.gdc.isfacademy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;

public class ForgotPasswordActivity extends BaseActivity {
    public Toolbar toolbar;
    private WebView web_view_forgot_password;
    public ImageView sliderIcon, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
    }

    public void init() {
        // Set a toolbar to replace the action bar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        web_view_forgot_password=(WebView)findViewById(R.id.web_view_forgot_password);
        web_view_forgot_password.getSettings().setJavaScriptEnabled(true);
        web_view_forgot_password.getSettings().setDomStorageEnabled(true);
        web_view_forgot_password.setWebViewClient(new AppWebViewClients());
        web_view_forgot_password.loadUrl(AppConstants.FORGOT_PASSWORD);
        sliderIcon = (ImageView) findViewById(R.id.sliderIcon);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setVisibility(View.VISIBLE);
        sliderIcon.setVisibility(View.GONE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class AppWebViewClients extends WebViewClient {

        public AppWebViewClients() {
            showProgressDialog(ForgotPasswordActivity.this);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideProgressDialog();
        }
    }
}
