package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.gdc.isfacademy.view.adapter.ChallengeAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
*
* Class Responsible for student ranking according to type friend and house,
* also start daily quiz for students.
*
* */
@SuppressWarnings("ALL")
public class ChallengeFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "ExploreFragment";
    public static boolean isDataRefresh = false;
    private XRecyclerView challenge_recylerview;
    private HomeActivity activity;
    private Context context;
    private ChallengeAdapter challengeAdapter;
    private ArrayList<ChallangeRankList> challangeRankLists;
    private AppCompatTextView friendsRankBtn, houseRankBtn;
    private OpenSansSemiBoldTextView add_friend_tv;
    private ImageView start_quize_image;
    private String isQusetionAnswerSubmited = "";
    private TextView startNowTv;
    public static boolean isListNeedRefresh=false;

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
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.challenge_fragment, null);
        if (HomeActivity.isFromLink) {
            HomeActivity.isFromLink = false;
            isDataRefresh = false;
            initView(view);
            ((HomeActivity) getActivity()).pushFragments(new AddFriendFragment(), null, true);
        } else {
            isDataRefresh = true;
            HomeActivity.isFromLink = false;
            initView(view);
            getQuizSubmittedStatus();
            getStudentRanking(AppConstants.RANK_TYPE_FRIEND);

        }
        return view;
    }


    /*
    *
    * Initializing view for the screen
    * @Param view for getting view of the xml objects.
    *
    * */
    private void initView(View view) {
        challangeRankLists = new ArrayList<>();
        challenge_recylerview = (XRecyclerView) view.findViewById(R.id.challenge_recylerview);
        challenge_recylerview.setLoadingMoreEnabled(false);
        challenge_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_challange,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        challenge_recylerview.addHeaderView(voucherHeader);
        startNowTv = (TextView) voucherHeader.findViewById(R.id.startNowTv);
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
        startNowTv.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.GONE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.VISIBLE);
        if (!isDataRefresh) {
            isDataRefresh = true;
            getQuizSubmittedStatus();
            getStudentRanking(AppConstants.RANK_TYPE_FRIEND);
        }
        if(isListNeedRefresh){
            isListNeedRefresh=false;
            getStudentRanking(AppConstants.RANK_TYPE_FRIEND);

        }


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.add_friend_tv:
                ((HomeActivity) getActivity()).pushFragments(new AddFriendFragment(), null, true);
                break;
            case R.id.friendsRankBtn:
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_selector));
                houseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
                houseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner));
                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                getStudentRanking(AppConstants.RANK_TYPE_FRIEND);
                break;
            case R.id.houseRankBtn:
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_deselector));
                houseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_selector));
                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_text_and_spinner));
                houseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                getStudentRanking(AppConstants.RANK_TYPE_HOUSE);
                break;
            case R.id.startNowTv:
                if (isQusetionAnswerSubmited.equalsIgnoreCase(AppConstants.FALSE)) {
                    ((HomeActivity) getActivity()).pushFragments(new QuizeFragment(), null, true);
                } else if (isQusetionAnswerSubmited.equalsIgnoreCase(AppConstants.TRUE)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(AppConstants.ANSWER_COUNT, MyPref.getInstance(getActivity()).readIntegerPrefs(MyPref.QUIZ_COUNT));
                    ((HomeActivity) getActivity()).pushFragments(QuizeCompletedFragement.newInstance(), bundle, true);
                } else {
                    ProjectUtil.showToast(getActivity(), getString(R.string.txt_try_again));
                }

                break;
        }
    }


    /*
    *
    *
    * Api call for getting status for daily quiz submission
    * by student for every day accordingly.
    *
    * */
    private void getQuizSubmittedStatus() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }
        showProgressDialog(getActivity());
        Call<CommonResponse> call = ISFApp.getAppInstance()
                .getApi()
                .checkStudenQuestions(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().isSubmitted().equalsIgnoreCase(AppConstants.TRUE)) {
                                isQusetionAnswerSubmited = response.body().isSubmitted();
                                if (response.body().getCorrect() != null) {
                                    MyPref.getInstance(getActivity()).writeIntegerPrefs(MyPref.QUIZ_COUNT, Integer.parseInt(response.body().getCorrect()));
                                }
                                start_quize_image.setImageResource(R.drawable.challenge_header_result);
                            } else if (response.body().isSubmitted().equalsIgnoreCase(AppConstants.FALSE)) {
                                isQusetionAnswerSubmited = response.body().isSubmitted();
                                start_quize_image.setImageResource(R.drawable.challenge_header);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                ProjectUtil.showToast(ISFApp.getAppInstance(),ISFApp.getAppInstance().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });

    }

    /**
     * Api call for getting getting student ranking in term of type given.
     *
     * @param type used to get rank according to type friend and gender.
     */
    private void getStudentRanking(final String type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }
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
                            String value=response.body().getRankings().get(0).getValue();
                            int valueRank=1;
                            for (int i = 0; i < response.body().getRankings().size(); i++) {
                                ChallangeRankList challangeRankList = new ChallangeRankList();
                                if(value.equalsIgnoreCase(response.body().getRankings().get(i).getValue())){
                                    value=response.body().getRankings().get(i).getValue();
                                    challangeRankList.setFinalRankStudent(valueRank);
                                }
                                else {
                                    valueRank=i+1;
                                    value=response.body().getRankings().get(i).getValue();
                                    challangeRankList.setFinalRankStudent(valueRank);

                                }
                                int rank = i + 1;

                                challangeRankList.setRanking("" + rank);
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
                            for (int i=0;i<challangeRankLists.size();i++){
                                Log.e("ranking is",""+challangeRankLists.get(i).getFinalRankStudent());
                            }
                            challengeAdapter.updateList(getActivity(), challangeRankLists);

                        } else {
                            challangeRankLists = new ArrayList<ChallangeRankList>();
                            challengeAdapter.updateList(getActivity(), challangeRankLists);


                        }
                    } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                        ProjectUtil.logoutFromApp(getActivity());
                    } else {
                        challangeRankLists = new ArrayList<ChallangeRankList>();
                        challengeAdapter.updateList(getActivity(), challangeRankLists);
                        ProjectUtil.showToast(ISFApp.getAppInstance().getApplicationContext(), response.body().getResponseMessage());

                    }
                }

            }

            @Override
            public void onFailure(Call<RankingParentResponse> call, Throwable t) {
                ProjectUtil.showToast(ISFApp.getAppInstance(),ISFApp.getAppInstance().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }


}
