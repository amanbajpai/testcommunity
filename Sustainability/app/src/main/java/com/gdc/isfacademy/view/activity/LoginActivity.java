package com.gdc.isfacademy.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import com.gdc.isfacademy.utils.LocationUtils;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.NavigateAfterGetLocation;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightEditText;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity implements View.OnClickListener, NavigateAfterGetLocation {

    public static double latitude, longitude;
    AppCompatTextView loginBtn, forgotPasswordBtn;
    Context mContext;
    LocationUtils locationUtils;

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
                    locationUtils = new LocationUtils(this, this,true);


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
            Log.d("json", "" + jsonObject.toString());
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
                    if (loginParentResponse.getStudentInfo() != null) {
                        Tracker tracker = ISFApp.getAppInstance().getGoogleAnalyticsTracker();
                        tracker.setClientId(loginParentResponse.getStudentInfo().getStudentId());
                        if (getAddressFromUserCurrentLocation() != null && !getAddressFromUserCurrentLocation().equalsIgnoreCase("")) {
                            tracker.setLocation(getAddressFromUserCurrentLocation());
                        }
                        //  tracker.setLocation();
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


    @Override
    public void navigate(double lat, double longi,boolean isFromLogin) {
        latitude = lat;
        longitude = longi;
        hideProgressDialog();
        if(isFromLogin){
            showLoginDialog(mContext);
        }
        getAddressFromUserCurrentLocation();


    }

    public String getAddressFromUserCurrentLocation() {
        Geocoder geocoder;
        List<Address> addresses;
        String address = "";
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            Log.d("address", "" + address );
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            //facebookLogin.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == AppConstants.REQUEST_LOCATION) {
                if (resultCode == Activity.RESULT_OK) {
                    showProgressDialog(mContext);
                    locationUtils.getLocation();
                } else {
                    LoginActivity.longitude = 0.0;
                    LoginActivity.latitude = 0.0;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
