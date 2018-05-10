package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.database.DbHalper;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.ChallangeRankListDao;
import com.gdc.isfacademy.model.RankingParentResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.ChallengeAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChallengeFragment extends BaseFragment {
    public static final String TAG = "ExploreFragment";

    public XRecyclerView challenge_recylerview;
    HomeActivity activity;
    Context context;
    ChallengeAdapter challengeAdapter;
    ArrayList<ChallangeRankList> challangeRankLists;
    AppCompatTextView friendsRankBtn, houseRankBtn;
    private OpenSansSemiBoldTextView add_friend_tv;
    private ImageView start_quize_image;

    public static ChallengeFragment newInstance() {
        ChallengeFragment challengeFragment = new ChallengeFragment();
        return challengeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (HomeActivity) getActivity();
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.challenge_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        challangeRankLists = new ArrayList<>();
        // challangeRankLists = getList();
        challenge_recylerview = (XRecyclerView) view.findViewById(R.id.challenge_recylerview);
        challenge_recylerview.setLoadingMoreEnabled(false);
        challenge_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_challange,
                (ViewGroup) view.findViewById(android.R.id.content), false);

        challenge_recylerview.addHeaderView(voucherHeader);
        friendsRankBtn = (AppCompatTextView) voucherHeader.findViewById(R.id.friendsRankBtn);
        houseRankBtn = (AppCompatTextView) voucherHeader.findViewById(R.id.houseRankBtn);
        friendsRankBtn.setOnClickListener(this);
        houseRankBtn.setOnClickListener(this);

        friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_selector));
        houseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
        houseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner));
        friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));


        add_friend_tv = (OpenSansSemiBoldTextView) voucherHeader.findViewById(R.id.add_friend_tv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        challenge_recylerview.setLayoutManager(linearLayoutManager);
        challengeAdapter = new ChallengeAdapter(getActivity(), challangeRankLists);
        challenge_recylerview.setAdapter(challengeAdapter);
        start_quize_image = (ImageView) voucherHeader.findViewById(R.id.start_quize_image);
        add_friend_tv.setOnClickListener(this);
        start_quize_image.setOnClickListener(this);

     /*   challangeRankLists = DbHalper.getInstance().getRankingAccrodingToType(AppConstants.RANK_TYPE_FRIEND);
        if (challangeRankLists != null && challangeRankLists.size() > 0) {
            challengeAdapter.updateList(getActivity(), challangeRankLists);

        }*/
        getStudentRanking(AppConstants.RANK_TYPE_FRIEND);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.VISIBLE);


    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        switch (id) {

            case R.id.add_friend_tv:
                ((HomeActivity) getActivity()).pushFragments(new AddFriendFragment(), null, true);
                break;
            case R.id.friendsRankBtn:
             /*   challangeRankLists.clear();
                challangeRankLists = DbHalper.getInstance().getRankingAccrodingToType(AppConstants.RANK_TYPE_FRIEND);
                if (challangeRankLists != null && challangeRankLists.size() > 0) {
                    challengeAdapter.updateList(getActivity(), challangeRankLists);

                }*/
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_selector));
                houseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
                houseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner));
                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                getStudentRanking(AppConstants.RANK_TYPE_FRIEND);

                break;
            case R.id.houseRankBtn:
         /*       challangeRankLists.clear();
                challangeRankLists = DbHalper.getInstance().getRankingAccrodingToType(AppConstants.RANK_TYPE_HOUSE);
                if (challangeRankLists != null && challangeRankLists.size() > 0) {
                    challengeAdapter.updateList(getActivity(), challangeRankLists);

                }*/
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_deselector));
                houseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_selector));
                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner));
                houseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                getStudentRanking(AppConstants.RANK_TYPE_HOUSE);
                break;

            case R.id.start_quize_image:
                String answeredDate = MyPref.getInstance(getActivity())
                        .readPrefs(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_ID));

                if (!ProjectUtil.getTodayDate().equalsIgnoreCase(answeredDate)) {
                    ((HomeActivity) getActivity()).pushFragments(new QuizeFragment(), null, true);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.answer_limit_exceeds), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void getStudentRanking(final String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }

        /*if (challangeRankLists == null || challangeRankLists.size() == 0)
           showProgressDialog(getActivity());*/
        showProgressDialog(getActivity());
        Call<RankingParentResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getStudentRankings(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                        type);

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<RankingParentResponse>() {
            @Override
            public void onResponse(Call<RankingParentResponse> call, Response<RankingParentResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        if (response.body().getRankings() != null && response.body().getRankings().size() > 0) {
                            String studenId = MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_ID);
                            challangeRankLists.clear();
                            for (int i = 0; i < response.body().getRankings().size(); i++) {
                                ChallangeRankList challangeRankList = new ChallangeRankList();
                                challangeRankList.setCheckIsme(response.body().getRankings().get(i).getCheckIsme());
                                challangeRankList.setHouse(response.body().getRankings().get(i).getHouse());
                                challangeRankList.setLastUpdateDate(response.body().getRankings().get(i).getLastUpdateDate());
                                challangeRankList.setLastUpdateTs(response.body().getRankings().get(i).getLastUpdateTs());
                                challangeRankList.setStudentName(response.body().getRankings().get(i).getStudentName());
                                challangeRankList.setValue(response.body().getRankings().get(i).getValue());
                                challangeRankList.setType(type);
                                challangeRankList.setStudentId(response.body().getRankings().get(i).getStudentId());
                                challangeRankList.setUnit(response.body().getRankings().get(i).getUnit());
                                if (response.body().getRankings().get(i).getStudentId().equalsIgnoreCase(studenId)) {
                                    challangeRankList.setCheckIsme(true);
                                } else {
                                    challangeRankList.setCheckIsme(false);
                                }
                                challangeRankLists.add(challangeRankList);
                            }

                            //DbHalper.getInstance().insertChallangeRankingList(challangeRankLists,type);

                            challengeAdapter.updateList(getActivity(), challangeRankLists);

                        }
                    } else {
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }


            }

            @Override
            public void onFailure(Call<RankingParentResponse> call, Throwable t) {

                ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }


}
