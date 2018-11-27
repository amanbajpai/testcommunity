package com.gdc.isfacademy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ashishthakur on 13/11/18.
 */

public class SendFriendRequestFragment extends BaseFragment {
    public static final String TAG = "SendFriendRequestFragment";
    TextView send_btn_tv, extraText;
    EditText emailAddress, bodyEmail;


    public static SendFriendRequestFragment newInstance() {
        SendFriendRequestFragment sendFriendRequestFragment = new SendFriendRequestFragment();
        return sendFriendRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.send_friend_request_fragment, container, false);
        send_btn_tv = (TextView) rootView.findViewById(R.id.send_btn_tv);
        emailAddress = (EditText) rootView.findViewById(R.id.emailAddressEt);
        bodyEmail = (EditText) rootView.findViewById(R.id.bodyEmailEt);
        bodyEmail.setText("Hello,this is"+" "+MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_NAME)+" "+"Sending you a friend Request.");
        extraText = (TextView) rootView.findViewById(R.id.extraText);

        send_btn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFriendRequest(emailAddress.getText().toString().trim(),
                        bodyEmail.getText().toString().trim());
            }
        });
        return rootView;
    }


    public void sendFriendRequest(String email, String body) {
        if (email.isEmpty()) {
            ProjectUtil.showToast(getActivity(), "Please enter email address");
            return;
        } else if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .sendFriendRequest(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            email,
                            body);

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                            emailAddress.setText("");
                            bodyEmail.setText("");
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        } else {
                            ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {

                    if (getActivity() != null) {
                        ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }
}
