package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.CommonResponse;
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
    LinearLayoutManager linearLayoutManager;
    private AppCompatTextView title_reward_text, gift_text;
    private String redeemToken;

    public static RewardsFragment newInstance() {
        RewardsFragment rewardsFragment = new RewardsFragment();
        return rewardsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.rewards_fragment, container, false);
        init();
        getStudentRewards(false);
        return rootView;
    }

    public void init() {
        rewardListResponses = new ArrayList<>();
        rewards_recylerview = (XRecyclerView) rootView.findViewById(R.id.rewards_recylerview);
        rewards_recylerview.setLoadingMoreEnabled(false);
        rewards_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_rewards,
                (ViewGroup) rootView.findViewById(android.R.id.content), false);
        title_reward_text = (AppCompatTextView) voucherHeader.findViewById(R.id.title_text);
        gift_text = (AppCompatTextView) voucherHeader.findViewById(R.id.gift_text);
        rewards_recylerview.addHeaderView(voucherHeader);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewards_recylerview.setLayoutManager(linearLayoutManager);
        rewardsAdapter = new RewardsAdapter(getActivity(), rewardListResponses, rewards_recylerview);
        rewardsAdapter.setOnRewardItemClickListner(this);
        rewards_recylerview.setAdapter(rewardsAdapter);

    }


    @Override
    public void onClick(int id, final int position, String staffCode) {
        switch (id) {
            case R.id.rewards_relative_layout:
               /* if (rewardListResponses.get(pos).isItemOpen()) {
                    rewardListResponses.get(pos).setItemOpen(false);
                } else {
                    rewardListResponses.get(pos).setItemOpen(true);
                }*/
                redeemToken = "";
                if (!rewardListResponses.get(position).isItemOpen()) {
                    for (int i = 0; i < rewardListResponses.size(); i++) {
                        rewardListResponses.get(i).setItemOpen(false);
                    }
                    rewardListResponses.get(position).setItemOpen(true);
                    rewardsAdapter.notifyDataSetChanged();
                    rewards_recylerview.post(new Runnable() {
                        @Override
                        public void run() {
                            linearLayoutManager.scrollToPositionWithOffset(position, position);
                        }
                    });
                    if(rewardListResponses.get(position).getStatus().equalsIgnoreCase(AppConstants.ACTIVE_REWARD)){
                        getTokenForRedeem(rewardListResponses.get(position).getIsfRewardsId());
                    }
                } else {
                    rewardListResponses.get(position).setItemOpen(false);
                    rewardsAdapter.notifyDataSetChanged();

                }

                break;
            case R.id.cancelBtn:
                rewardListResponses.get(position).setItemOpen(false);
                rewardsAdapter.notifyDataSetChanged();
                break;
            case R.id.redeemBtn:
                redeemReward(redeemToken, staffCode, position);
                break;
            case R.id.closeBtn:
                rewardListResponses.get(position).setItemOpen(false);
                rewardsAdapter.notifyDataSetChanged();
                break;
        }
    }


    /*
    *
    *
    * Api call for getting student reward list
    *
    * */
    private void getStudentRewards(boolean fromReward) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            if (!fromReward) {
                showProgressDialog(getActivity());

            }
            Call<StudentRewardResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentRewards(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<StudentRewardResponse>() {
                @Override
                public void onResponse(Call<StudentRewardResponse> call, Response<StudentRewardResponse> response) {
                    hideProgressDialog();
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().getRewards() != null && response.body().getRewards().size() > 0) {
                                rewardListResponses = response.body().getRewards();
                                rewardsAdapter.setList(getActivity(), rewardListResponses);
                                title_reward_text.setText(getString(R.string.txt_reward_text));
                                gift_text.setVisibility(View.GONE);
                                gift_text.setText(getString(R.string.txt_gift));
                            }
                            else {
                                gift_text.setVisibility(View.GONE);
                                title_reward_text.setText(getString(R.string.txt_no_reward_text));
                            }
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        }
                    }
                    //getStudentStatus();

                }

                @Override
                public void onFailure(Call<StudentRewardResponse> call, Throwable t) {
                    //getStudentStatus();
                    hideProgressDialog();
                    gift_text.setVisibility(View.GONE);
                    title_reward_text.setText(getString(R.string.txt_no_reward_text));
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


    /*
    *
    *
    * Api call for getting reward item token.
    *
    * */
    private void getTokenForRedeem(String rewardId) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getRewardToken(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            rewardId);

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            redeemToken = response.body().getToken();
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
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


    /*
    *
    *
    * Api call for redeem student reward
    *
    * */
    private void redeemReward(String token, String staffCode, final int postion) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        else if(staffCode.isEmpty()){
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.txt_please_enter_staff_code));
            return;
        }
        else if(token.isEmpty()){
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.txt_please_genrate_token));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .redeemReward(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            token, staffCode);

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            ProjectUtil.showToast(getActivity(), getString(R.string.txt_redeem_successfully));
                            rewardListResponses.get(postion).setItemOpen(false);
                            rewardsAdapter.notifyDataSetChanged();
                            getStudentRewards(true);
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_INVALID_STAFF_CODE)
                                || response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_INVALID_STAFF_CODE_SECOND)) {
                            if (response.body().getResponseMessage() != null) {
                                ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                            } else {
                                ProjectUtil.showToast(getActivity(), getString(R.string.txt_invalid_staff));
                            }
                            hideProgressDialog();

                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_INVALID_VOUCHER)) {
                            if (response.body().getResponseMessage() != null) {
                                ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                            } else {
                                ProjectUtil.showToast(getActivity(), getString(R.string.txt_voucher_invalid));
                            }
                            hideProgressDialog();
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                            hideProgressDialog();

                        } else {
                            ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                            hideProgressDialog();
                        }
                    }

                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    hideProgressDialog();
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


    /*
    *
    *
    * Api call for getting student current ranking,challange and share status
    *
    * */
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
                            if (response.body().getIsfStudentStatus() != null) {
                                if (response.body().getIsfStudentStatus().getRankingCount() != null) {
                                    if (Integer.parseInt(response.body().getIsfStudentStatus().getRankingCount()) <= 5) {
                                        title_reward_text.setText(getString(R.string.txt_reward_text));
                                        gift_text.setVisibility(View.GONE);
                                        gift_text.setText(getString(R.string.txt_gift));
                                    } else {
                                        gift_text.setVisibility(View.GONE);
                                        title_reward_text.setText(getString(R.string.txt_no_reward_text));
                                    }

                                } else {
                                    gift_text.setVisibility(View.GONE);
                                    title_reward_text.setText(getString(R.string.txt_no_reward_text));
                                }

                            }
                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());
                        } else {
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
