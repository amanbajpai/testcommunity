package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.RewardStudentResponse;
import com.gdc.isfacademy.model.StudentRewardResponse;
import com.gdc.isfacademy.model.StudentStatusResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.adapter.RewardsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class RewardsFragment extends BaseFragment implements RewardsAdapter.OnRewardItemClickListner {
    public static final String TAG = "RewardsFragment";

    XRecyclerView rewards_recylerview;
    List<RewardStudentResponse> rewardListResponses;
    RewardsAdapter rewardsAdapter;
    private AppCompatTextView title_reward_text,gift_text;


    public static RewardsFragment newInstance() {
        RewardsFragment rewardsFragment = new RewardsFragment();
        return rewardsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.rewards_fragment, container, false);
        init();
        getStudentRewards();
        return rootView;
    }

    public void init() {
        rewardListResponses = new ArrayList<>();
        rewards_recylerview = (XRecyclerView) rootView.findViewById(R.id.rewards_recylerview);
        rewards_recylerview.setLoadingMoreEnabled(false);
        rewards_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_rewards,
                (ViewGroup) rootView.findViewById(android.R.id.content), false);
        title_reward_text=(AppCompatTextView)voucherHeader.findViewById(R.id.title_text);
        gift_text=(AppCompatTextView)voucherHeader.findViewById(R.id.gift_text);
        rewards_recylerview.addHeaderView(voucherHeader);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewards_recylerview.setLayoutManager(linearLayoutManager);
        rewardsAdapter = new RewardsAdapter(getActivity(), rewardListResponses);
        rewardsAdapter.setOnRewardItemClickListner(this);
        rewards_recylerview.setAdapter(rewardsAdapter);

    }


    @Override
    public void onClick(int id, int pos) {
        switch (id) {
            case R.id.rewards_relative_layout:
                if (rewardListResponses.get(pos).isItemOpen()) {
                    rewardListResponses.get(pos).setItemOpen(false);
                } else {
                    rewardListResponses.get(pos).setItemOpen(true);
                }
                rewardsAdapter.notifyDataSetChanged();
                break;
        }
    }


    private void getStudentRewards() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;

        }
        try {
            showProgressDialog(getActivity());
            Call<StudentRewardResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentRewards(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<StudentRewardResponse>() {
                @Override
                public void onResponse(Call<StudentRewardResponse> call, Response<StudentRewardResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().getRewards() != null && response.body().getRewards().size() > 0) {
                                rewardListResponses=response.body().getRewards();
                                rewardsAdapter.setList(getActivity(),rewardListResponses);
                            }

                        }
                        else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        }
                    }
                    getStudentStatus();

                }

                @Override
                public void onFailure(Call<StudentRewardResponse> call, Throwable t) {
                    getStudentStatus();

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


    private void getStudentStatus() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;

        }
        try {
            Call<StudentStatusResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentStatus(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<StudentStatusResponse>() {
                @Override
                public void onResponse(Call<StudentStatusResponse> call, Response<StudentStatusResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if(response.body().getIsfStudentStatus()!=null){
                                if(response.body().getIsfStudentStatus().getRankingCount()!=null){
                                    if(Integer.parseInt(response.body().getIsfStudentStatus().getRankingCount())<=5){
                                        title_reward_text.setText(getString(R.string.txt_reward_text));
                                        gift_text.setVisibility(View.GONE);
                                        gift_text.setText(getString(R.string.txt_gift));
                                    }
                                    else {
                                        gift_text.setVisibility(View.GONE);
                                        title_reward_text.setText(getString(R.string.txt_no_reward_text));
                                    }

                                }
                                else {
                                    gift_text.setVisibility(View.GONE);
                                    title_reward_text.setText(getString(R.string.txt_no_reward_text));
                                }

                            }
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        }
                        else {
                            gift_text.setVisibility(View.GONE);
                            title_reward_text.setText(getString(R.string.txt_no_reward_text));
                        }
                    }

                }

                @Override
                public void onFailure(Call<StudentStatusResponse> call, Throwable t) {
                    if (getActivity() != null) {
                        ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                    }
                    t.printStackTrace();
                    hideProgressDialog();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
