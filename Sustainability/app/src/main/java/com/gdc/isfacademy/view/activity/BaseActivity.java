package com.gdc.isfacademy.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.ProgressBar;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.ProjectUtil;



@SuppressWarnings("ALL")
public class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    ProgressBar progressBar;

    @Override
    protected void onResume() {
        super.onResume();
        ProjectUtil.checkTime(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_cyclic);
    }

    /**
     * Use to show loading dialog.
     */
    public void showProgressDialog(Context context) {


        if (!CheckNetworkState.isOnline(context)) {
            ProjectUtil.showToast(context, context.getString(R.string.txt_network_error));
            return;
        }

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N_MR1){
            if (mProgressDialog == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mProgressDialog = new ProgressDialog(new ContextThemeWrapper(context, R.style.MyProgressDialog));
                } else {
                    mProgressDialog = new ProgressDialog(context);
                }
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage(context.getResources().getString(R.string.txt_please_wait));
                mProgressDialog.show();
            }

         /*   if(progressBar!=null){
                progressBar.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    progressBar = new ProgressBar(new ContextThemeWrapper(context, R.style.MyProgressDialog));
                } else {
                    progressBar = new ProgressBar(context);
                }
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }*/
        }
        else {
            if (mProgressDialog == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mProgressDialog = new ProgressDialog(new ContextThemeWrapper(context, R.style.MyProgressDialog));
                } else {
                    mProgressDialog = new ProgressDialog(context);
                }
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage(context.getResources().getString(R.string.txt_please_wait));
                mProgressDialog.show();
            }
        }


    }

    /**
     * Use to hide loading dialog.
     */
    public void hideProgressDialog() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N_MR1){
            /*getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.GONE);*/
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }else {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
        }


        }
    }
}
