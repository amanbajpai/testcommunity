package com.gdc.isfacademy.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.model.LoginParentResponse;
import com.gdc.isfacademy.netcom.ApiConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                    /*Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();*/
                    showLoginDialog(mContext);

                } else {
                    ProjectUtil.showInvalidTimeDialog(LoginActivity.this);
                }


                break;
            case R.id.forgot_password_btn:
                break;
        }
    }

    public void showLoginDialog(Context context) {

        final Dialog dialog = new Dialog(context, android.R.style.Theme_Holo_Dialog);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.default_login_dialog, null);
        dialog.setTitle("LOGIN");
        final OpenSansLightEditText nameET = (OpenSansLightEditText) view.findViewById(R.id.nameET);
        final OpenSansLightEditText passET = (OpenSansLightEditText) view.findViewById(R.id.passET);
        TextView okBT = (TextView) view.findViewById(R.id.okBT);
        TextView cancelBT = (TextView) view.findViewById(R.id.cancelBT);
        okBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameET.getText().toString().trim().isEmpty()) {
                    nameET.setError(getString(R.string.txt_validate_student_id));
                } else if (passET.getText().toString().trim().isEmpty()) {
                    passET.setError(getString(R.string.txt_validate_password));

                } else {
                    dialog.cancel();
                    loginUser(nameET.getText().toString().trim(), passET.getText().toString().trim());
                }
            }
        });
        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }


    public void loginUser(String userName, String userPassowrd) {
        JSONObject jsonObject = null;
        RequestBody body = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put(ApiConstants.ApiParams.STUDENT_ID, userName);
            jsonObject.put(ApiConstants.ApiParams.PASSWORD, userPassowrd);
            Log.d("json",""+jsonObject.toString());
            body = RequestBody.create(okhttp3.MediaType.parse("application/json"),
                    (new JSONObject(jsonObject.toString())).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        showProgressDialog(mContext);
        final Call<LoginParentResponse> loginRequest = ((ISFApp) mContext.getApplicationContext())
                .getApi().login(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        body);
        ProjectUtil.showLog(AppConstants.REQUEST, "" + loginRequest.request().url(), AppConstants.ERROR_LOG);

        loginRequest.enqueue(new Callback<LoginParentResponse>() {
            @Override
            public void onResponse(Call<LoginParentResponse> call, Response<LoginParentResponse> response) {
                hideProgressDialog();
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                if (response.body() != null) {
                    LoginParentResponse loginParentResponse = response.body();
                    if(loginParentResponse.getStudentInfo()!=null){
                        MyPref.getInstance(mContext).writePrefs(AppConstants.STUDENT_NAME,
                                loginParentResponse.getStudentInfo().getStudentName());
                        MyPref.getInstance(mContext).writePrefs(AppConstants.STUDENT_ID,
                                loginParentResponse.getStudentInfo().getStudentId());
                        MyPref.getInstance(mContext).writePrefs(AppConstants.STUDENT_KEY,
                                loginParentResponse.getStudentInfo().getStudentKey());
                        MyPref.getInstance(mContext).writePrefs(AppConstants.STUDENT_HOUSE,
                                loginParentResponse.getStudentInfo().getStudentHouse());
                        Intent intent = new Intent(mContext, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }


            }

            @Override
            public void onFailure(Call<LoginParentResponse> call, Throwable t) {
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }


}
