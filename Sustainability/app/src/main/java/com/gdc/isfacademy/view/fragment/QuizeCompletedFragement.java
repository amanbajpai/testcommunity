package com.gdc.isfacademy.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.RankingParentResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizeCompletedFragement extends BaseFragment {

    public static final String TAG = "QuizeCompletedFragement";
    HomeActivity activity;
    Context context;
    int count;
    private OpenSansSemiBoldTextView share_tv;

    public static QuizeCompletedFragement newInstance() {
        QuizeCompletedFragement quizeFragment = new QuizeCompletedFragement();
        return quizeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (HomeActivity) getActivity();
        Bundle bundle = getArguments();
        count = bundle.getInt(AppConstants.ANSWER_COUNT);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_quize_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        share_tv = (OpenSansSemiBoldTextView) view.findViewById(R.id.share_tv);
        share_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        switch (id) {

            case R.id.share_tv:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Earned" + " " + count + " " + "Points in ISF Community Quiz");
                sendIntent.setType("text/plain");
                startActivityForResult(Intent.createChooser(sendIntent, getResources().getText(R.string.txt_send_to)), 101);

                // ProjectUtil.showShareDialog(context, this);
                break;

            case R.id.profile_share_tv:
                break;

        }
    }


    private void submitShare() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

//        if (challangeRankLists == null || challangeRankLists.size() == 0)
//            showProgressDialog(getActivity());
        showProgressDialog(getActivity());
        Call<CommonResponse> call = ISFApp.getAppInstance()
                .getApi()
                .submitSharePoints(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        ((HomeActivity) getActivity()).pushFragments(ChallengeFragment.newInstance(), null, true);

                    }
                    else {
                        ((HomeActivity) getActivity()).pushFragments(ChallengeFragment.newInstance(), null, true);

                    }

                }


            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                ((HomeActivity) getActivity()).pushFragments(ChallengeFragment.newInstance(), null, true);
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                submitShare();

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("cancel", "Cancel");
            }
        }
    }
}
