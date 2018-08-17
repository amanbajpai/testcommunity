package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.model.LogStudentResponse;
import com.gdc.isfacademy.model.StudentBadgeResponse;
import com.gdc.isfacademy.model.StudentLogResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.BadgeAdapter;
import com.gdc.isfacademy.view.adapter.ProfileAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class ProfileFragment extends BaseFragment {
    public static final String TAG = "ProfileFragment";
    private ImageView btnInstagram, btnGooglePlus, btnWeChat, btnSnapChat, btnWhatsApp;
    private List<LogStudentResponse> logStudentResponses;
    private ProfileAdapter adapter;
    private RecyclerView recycler_view;
    private android.content.Context context;
    private ImageView iv_one, iv_two, iv_three, iv_four, iv_five;
    private LinearLayout badge_icon_layout;
    private List<BadgeStudentResponse> badgeStudentResponses;
    private RelativeLayout badgeRelativeLayout;
    private RecyclerView badge_recylerview;
    private BadgeAdapter badgeAdapter;
    private ArrayList<BadgeStudentResponse> badgeStudentResponsesNew;
    private AppCompatTextView no_badges_alloted;
    private CardView card_view_badges;
    private CardView cardViewFroStudentLog;


    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.profile_fragment, null);
        initView(view);
        getStudentActivityLogs();
        getStudentBadges();
        return view;
    }


    /*
    *
    * Initializing Views for profile Screen.
    * @param view containing the the view holder of screen items.
    *
    * */
    private void initView(View view) {
        logStudentResponses = new ArrayList<>();
        badgeStudentResponses = new ArrayList<>();

        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_profile);
        card_view_badges = (CardView) view.findViewById(R.id.card_view_badges);
        no_badges_alloted = (AppCompatTextView) view.findViewById(R.id.no_badges_alloted);
        badgeRelativeLayout = (RelativeLayout) view.findViewById(R.id.badgeRelativeLayout);
        cardViewFroStudentLog = (CardView) view.findViewById(R.id.cardViewFroStudentLog);
        AppCompatTextView studentHouse = (AppCompatTextView) view.findViewById(R.id.studentHouse);
        AppCompatTextView studentname = (AppCompatTextView) view.findViewById(R.id.studentName);
        btnInstagram = (ImageView) view.findViewById(R.id.btn_instagram);
        btnGooglePlus = (ImageView) view.findViewById(R.id.btn_google_plus);
        btnSnapChat = (ImageView) view.findViewById(R.id.btn_snapchat);
        btnWhatsApp = (ImageView) view.findViewById(R.id.btn_whatsapp);
        btnWeChat = (ImageView) view.findViewById(R.id.btn_we_chat);
        badge_recylerview = (RecyclerView) view.findViewById(R.id.badge_recylerview);
        badge_icon_layout = (LinearLayout) view.findViewById(R.id.badge_icon_layout);


        badgeAdapter = new BadgeAdapter(getActivity(), badgeStudentResponses);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        badge_recylerview.setLayoutManager(linearLayoutManager);
        badge_recylerview.setAdapter(badgeAdapter);
        studentname.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_NAME));
        studentHouse.setText(MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_HOUSE));

        adapter = new ProfileAdapter(context, logStudentResponses);
        recycler_view.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayout.VERTICAL, false);
        recycler_view.setLayoutManager(manager);
        badgeStudentResponsesNew = new ArrayList<>();

        badgeRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Bundle bundle = new Bundle();
                 bundle.putSerializable(AppConstants.LIST_BADGE, badgeStudentResponsesNew);
                ((HomeActivity) getActivity()).pushFragments(new BadgeDetailFragment(), bundle, true);

            }
        });
    }


    /*
    *
    * Api calling for getting list of student logs
    * for showing points earned,quiz completion,Energy saving etc.
    *
    * */
    private void getStudentActivityLogs() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<StudentLogResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentLogs(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));
            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);
            call.enqueue(new Callback<StudentLogResponse>() {
                @Override
                public void onResponse(Call<StudentLogResponse> call, Response<StudentLogResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().getLogStudentResponses() != null && response.body().getLogStudentResponses().size() > 0) {
                                cardViewFroStudentLog.setVisibility(View.VISIBLE);
                                logStudentResponses = response.body().getLogStudentResponses();
                                adapter.setList(getActivity(), logStudentResponses);
                            } else {
                                cardViewFroStudentLog.setVisibility(View.GONE);
                            }

                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());

                        }
                    }

                }

                @Override
                public void onFailure(Call<StudentLogResponse> call, Throwable t) {
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


    /*
    *
    * Api call for getting list of badge types and value,
    * which is used to claculate the current badge allotment
    * for student weather it is locked or unlocked badge
    *
    * */
    private void getStudentBadges() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;

        }
        try {
            showProgressDialog(getActivity());
            Call<StudentBadgeResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getStudentBadges(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<StudentBadgeResponse>() {
                @Override
                public void onResponse(Call<StudentBadgeResponse> call, Response<StudentBadgeResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {

                            if (response.body().getBadgeStudentResponses() != null && response.body().getBadgeStudentResponses().size() > 0) {
                                badgeStudentResponses = response.body().getBadgeStudentResponses();
                                badgeStudentResponsesNew.addAll(badgeStudentResponses);
                                setBadgeData(badgeStudentResponsesNew);

                            } else {
                                no_badges_alloted.setVisibility(View.VISIBLE);
                                no_badges_alloted.setText(getString(R.string.txt_no_badge_list));
                            }


                        } else if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.ERROR_CODE_STUDENT_KEY_NOT_MATCHED)) {
                            ProjectUtil.logoutFromApp(getActivity());


                        }
                    }

                }

                @Override
                public void onFailure(Call<StudentBadgeResponse> call, Throwable t) {
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


    /*
    *
    * Method used to Update Badge List.
    * @param badgeData containg list of badges from api response.
    *
    * */
    public void setBadgeData(ArrayList<BadgeStudentResponse> badgeData) {
        badgeAdapter.setList(getActivity(), badgeData);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (badgeAdapter.isBadgeAlloted()) {
                    badge_recylerview.setVisibility(View.VISIBLE);
                    no_badges_alloted.setVisibility(View.GONE);

                } else {
                    badge_recylerview.setVisibility(View.GONE);
                    no_badges_alloted.setVisibility(View.VISIBLE);
                }
            }
        }, AppConstants.UPDATE_BADGE_TIME);


    }


}