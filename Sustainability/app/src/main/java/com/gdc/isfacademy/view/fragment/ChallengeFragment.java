package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.HouseList;
import com.gdc.isfacademy.model.HouseParentResponse;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    public static boolean isListNeedRefresh = false;
    private XRecyclerView challenge_recylerview;
    private HomeActivity activity;
    private Context context;
    private ChallengeAdapter challengeAdapter;
    private ArrayList<ChallangeRankList> challangeRankLists;
    private ArrayList<HouseList> houseLists;
    private AppCompatTextView friendsRankBtn, studentHouseRankBtn, allHouseRankBtn;
    private OpenSansSemiBoldTextView add_friend_tv;
    private ImageView start_quize_image,addFriendIv;
    private String isQusetionAnswerSubmited = "";
    private TextView startNowTv;

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
        houseLists = new ArrayList<>();
        challenge_recylerview = (XRecyclerView) view.findViewById(R.id.challenge_recylerview);
        challenge_recylerview.setLoadingMoreEnabled(false);
        challenge_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_challange,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        challenge_recylerview.addHeaderView(voucherHeader);
        startNowTv = (TextView) voucherHeader.findViewById(R.id.startNowTv);
        friendsRankBtn = (AppCompatTextView) voucherHeader.findViewById(R.id.friendsRankBtn);
        studentHouseRankBtn = (AppCompatTextView) voucherHeader.findViewById(R.id.houseRankBtn);
        allHouseRankBtn = (AppCompatTextView) voucherHeader.findViewById(R.id.allHouseRankBtn);

        studentHouseRankBtn.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_HOUSE));
        friendsRankBtn.setOnClickListener(this);
        studentHouseRankBtn.setOnClickListener(this);
        allHouseRankBtn.setOnClickListener(this);

        /*
        *
        * Setting first drawable selector for the tabs button
        *
        * */
        friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_selector));
        studentHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
        allHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.all_house_selector));

        /*
        *
        * Setting first text color for the tabs button
        *
        * */
        friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        studentHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
        allHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));

        add_friend_tv = (OpenSansSemiBoldTextView) voucherHeader.findViewById(R.id.add_friend_tv);
        start_quize_image = (ImageView) voucherHeader.findViewById(R.id.start_quize_image);
        addFriendIv = (ImageView) voucherHeader.findViewById(R.id.addFriendIv);
        add_friend_tv.setOnClickListener(this);
        startNowTv.setOnClickListener(this);
        addFriendIv.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        challenge_recylerview.setLayoutManager(linearLayoutManager);
        challengeAdapter = new ChallengeAdapter(getActivity(), challangeRankLists);
        challenge_recylerview.setAdapter(challengeAdapter);
        challenge_recylerview.setHasFixedSize(true);
        challenge_recylerview.setItemViewCacheSize(100);
        challenge_recylerview.setDrawingCacheEnabled(true);
        challenge_recylerview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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
        if (isListNeedRefresh) {
            isListNeedRefresh = false;
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
            case R.id.addFriendIv:
                ((HomeActivity) getActivity()).pushFragments(new AddFriendFragment(), null, true);
                break;
            case R.id.friendsRankBtn:
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_selector));
                studentHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
                allHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.all_house_selector));

                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                allHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
                studentHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
                getStudentRanking(AppConstants.RANK_TYPE_FRIEND);
                break;
            case R.id.houseRankBtn:
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_deselector));
                studentHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_selector));
                allHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.all_house_selector));

                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
                studentHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                allHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));

                getStudentRanking(AppConstants.RANK_TYPE_HOUSE);
                break;
            case R.id.allHouseRankBtn:
                friendsRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.friend_deselector));
                studentHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.house_deselector));
                allHouseRankBtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.all_house_selected_selector));

                friendsRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
                studentHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_challnge));
                allHouseRankBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                getHouseRanking();
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
                ProjectUtil.showToast(ISFApp.getAppInstance(), ISFApp.getAppInstance().getString(R.string.something_went_wrong));
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
                            String value = response.body().getRankings().get(0).getValue();
                            int valueRank = 1;
                            for (int i = 0; i < response.body().getRankings().size(); i++) {
                                ChallangeRankList challangeRankList = new ChallangeRankList();
                                if (value.equalsIgnoreCase(response.body().getRankings().get(i).getValue())) {
                                    value = response.body().getRankings().get(i).getValue();

                                    challangeRankList.setFinalRankStudent(valueRank);
                                } else {
                                    valueRank = i + 1;
                                    value = response.body().getRankings().get(i).getValue();
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
                                challangeRankList.setFromHouse(false);
                                if (response.body().getRankings().get(i).getStudentId().equalsIgnoreCase(studenId)) {
                                    challangeRankList.setCheckIsme(true);
                                } else {
                                    challangeRankList.setCheckIsme(false);
                                }
                                challangeRankLists.add(challangeRankList);
                            }
                            challengeAdapter.updateList(getActivity(), challangeRankLists);


                        } else {
                            challangeRankLists = new ArrayList<ChallangeRankList>();
                            challengeAdapter.updateList(getActivity(),challangeRankLists);
                            ProjectUtil.showToast(getActivity(),"No Friends available");

                          /*  challangeRankLists = new ArrayList<ChallangeRankList>();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            challenge_recylerview.setLayoutManager(linearLayoutManager);
                            challengeAdapter = new ChallengeAdapter(getActivity(), challangeRankLists);
                            challenge_recylerview.setAdapter(challengeAdapter);*/

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
                ProjectUtil.showToast(ISFApp.getAppInstance(), ISFApp.getAppInstance().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });



    }



    /*
    *
    *
    * Api call for House Ranking
    *
    *
    *
    * */


    private void getHouseRanking() {
        houseLists = new ArrayList<>();
        challangeRankLists = new ArrayList<>();
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getString(R.string.txt_network_error));
            return;
        }
        showProgressDialog(getActivity());
        Call<HouseParentResponse> call = ISFApp.getAppInstance()
                .getApi()
                .getHouseRankings(AppConstants.API_KEY,
                        AppConstants.CONTENT_TYPE,
                        MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

        ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

        call.enqueue(new Callback<HouseParentResponse>() {
            @Override
            public void onResponse(Call<HouseParentResponse> call, Response<HouseParentResponse> response) {
                ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                hideProgressDialog();
                if (response.body() != null) {
                    if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                        if (response.body().getHouseLists() != null && response.body().getHouseLists().size() > 0) {
                            String value = response.body().getHouseLists().get(0).getValue();
                            int valueRank = 1;
                            String studentHouse = MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_HOUSE);
                            DecimalFormat mFormat = new DecimalFormat("###,###,##0.00"); // use two decimal

                            for (int i = 0; i < response.body().getHouseLists().size(); i++) {
                                HouseList houseList = new HouseList();
                                if (value.equalsIgnoreCase(response.body().getHouseLists().get(i).getValue())) {
                                    value = response.body().getHouseLists().get(i).getValue();
                                    houseList.setFinalRankStudent(valueRank);
                                } else {
                                    valueRank = i + 1;
                                    value = response.body().getHouseLists().get(i).getValue();
                                    houseList.setFinalRankStudent(valueRank);

                                }

                                float houseValue = new BigDecimal(response.body().getHouseLists().get(i).getValue()).setScale(2, BigDecimal.ROUND_DOWN).floatValue();

                                houseList.setHouse(response.body().getHouseLists().get(i).getHouse());
                                houseList.setValue(mFormat.format(houseValue));
                                houseList.setHomeRoomId(response.body().getHouseLists().get(i).getHomeRoomId());
                                houseList.setFromHouse(true);
                                if (response.body().getHouseLists().get(i).getHouse().equalsIgnoreCase(studentHouse)) {
                                    houseList.setCheckIsme(true);
                                } else {
                                    houseList.setCheckIsme(false);
                                }
                                houseLists.add(houseList);
                            }

                            if (houseLists != null && houseLists.size() > 0) {
                                for (int i = 0; i < houseLists.size(); i++) {
                                    challangeRankLists.add(new ChallangeRankList(houseLists.get(i).getHouse(),
                                            houseLists.get(i).getValue(),
                                            houseLists.get(i).getFinalRankStudent(),
                                            houseLists.get(i).isCheckIsme(),
                                            houseLists.get(i).isFromHouse()));
                                }
                                challengeAdapter.updateList(getActivity(), challangeRankLists);
                            }


                          /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            challenge_recylerview.setLayoutManager(linearLayoutManager);
                            houseRankAdapter = new HouseRankAdapter(getActivity(), houseLists);
                            challenge_recylerview.setAdapter(houseRankAdapter);*/
                        }


                    } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                        ProjectUtil.logoutFromApp(getActivity());
                    } else {
                        challangeRankLists = new ArrayList<ChallangeRankList>();
                        challengeAdapter.updateList(getActivity(), challangeRankLists);

                        ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<HouseParentResponse> call, Throwable t) {
                ProjectUtil.showToast(ISFApp.getAppInstance(), ISFApp.getAppInstance().getString(R.string.something_went_wrong));
                t.printStackTrace();
                hideProgressDialog();
            }
        });


    }

}
