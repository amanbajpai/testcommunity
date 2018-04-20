package com.gdc.isfacademy.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.ProjectUtil;


public class BaseActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;

    @Override
    protected void onResume() {
        super.onResume();
        ProjectUtil.checkTime(this);
    }

    /**
     * Use to show loading dialog.
     */
    public void showProgressDialog(Context context) {

        if (!CheckNetworkState.isOnline(context)) {
            ProjectUtil.showToast(context, context.getString(R.string.txt_network_error));
            return;
        }
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

    /**
     * Use to hide loading dialog.
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
