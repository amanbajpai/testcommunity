package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.application.ISFApp;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.FriendRequestResponse;
import com.gdc.isfacademy.model.ParentFriendListResponse;
import com.gdc.isfacademy.model.TotalFriendResponse;
import com.gdc.isfacademy.netcom.CheckNetworkState;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.MyPref;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.AllFriendAdapter;
import com.gdc.isfacademy.view.adapter.FriendRequestListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightEditText;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("ALL")
public class AddFriendFragment extends BaseFragment implements View.OnClickListener,
        FriendRequestListAdapter.OnFriendRequestListItemClick, AllFriendAdapter.OnAllFriendItemClickListner {
    public static final String TAG = "AddFriendFragment";
    public boolean isSeeAll = true;
    ArrayList<FriendRequestResponse> friendsBeanLists;
    ArrayList<FriendRequestResponse> sortedFriendList;

    ArrayList<TotalFriendResponse> allFriendLists;
    OpenSansLightEditText search_edit_text;
    Context context;
    FriendRequestListAdapter adapter;
    AllFriendAdapter allFriendAdapter;
    AppCompatTextView seeAllBtn;
    LinearLayoutManager manager;
    NestedScrollView nestedScrollView;
    private XRecyclerView recycler_view, recycler_all_friend;
    private TextView headerFriendRequestTitle, headerAllFriendTitle;
    private LinearLayout friendRequestStatusLayout;
    private ImageView arrowStatusIv;
    private RelativeLayout headerSendFriendRequest, headerFriendRequest;
    private View friendRequestHeader, addedFriendHeader;

    public static AddFriendFragment newInstance() {
        AddFriendFragment addFriendFragment = new AddFriendFragment();
        return addFriendFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.add_friend_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {


        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);
        seeAllBtn = (AppCompatTextView) view.findViewById(R.id.seeAllBtn);
        friendRequestStatusLayout = (LinearLayout) view.findViewById(R.id.friendRequestStatusLayout);
        arrowStatusIv = (ImageView) view.findViewById(R.id.arrowStatusIv);
        headerSendFriendRequest = (RelativeLayout) view.findViewById(R.id.send_friend_request_email_layout);


        recycler_view = (XRecyclerView) view.findViewById(R.id.recycler_view);
        recycler_view.setLoadingMoreEnabled(false);
        recycler_view.setPullRefreshEnabled(false);
        recycler_view.setNestedScrollingEnabled(false);

        recycler_all_friend = (XRecyclerView) view.findViewById(R.id.recycler_all_friend);
        recycler_all_friend.setPullRefreshEnabled(false);
        recycler_all_friend.setLoadingMoreEnabled(false);
        recycler_all_friend.setNestedScrollingEnabled(false);


        friendRequestHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_friend_list,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        addedFriendHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_friend_list,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        headerAllFriendTitle = (TextView) addedFriendHeader.findViewById(R.id.title_header);
        headerFriendRequestTitle = (TextView) friendRequestHeader.findViewById(R.id.title_header);
        headerFriendRequestTitle.setText(getString(R.string.txt_friend_request_header));
        friendRequestStatusLayout.setOnClickListener(this);
        headerSendFriendRequest.setOnClickListener(this);
        getFriendList();
        if (friendsBeanLists == null || friendsBeanLists.isEmpty()) {
            friendRequestStatusLayout.setVisibility(View.GONE);
        }


    }

    /*
    *
    *
    * Filltering list according to data;
    *
    * */
    public void setListing(ArrayList<FriendRequestResponse> friendsBeanLists) {
        if (friendsBeanLists != null && friendsBeanLists.size() > 0) {
            if (friendsBeanLists.size() > 3) {
                friendRequestStatusLayout.setVisibility(View.VISIBLE);
                seeAllBtn.setText(getString(R.string.txt_see_all) + (friendsBeanLists.size()) + ")");
                arrowStatusIv.setImageResource(R.drawable.small_arrow);
                sortedFriendList = new ArrayList<>();
                sortedFriendList.add(new FriendRequestResponse(friendsBeanLists.get(0).getStudentName()));
                sortedFriendList.add(new FriendRequestResponse(friendsBeanLists.get(1).getStudentName()));
                sortedFriendList.add(new FriendRequestResponse(friendsBeanLists.get(2).getStudentName()));
                adapter = new FriendRequestListAdapter(context, sortedFriendList, recycler_view);
                manager = new LinearLayoutManager(context);
                manager.setSmoothScrollbarEnabled(true);
                recycler_view.setAdapter(adapter);
                recycler_view.setLayoutManager(manager);
                adapter.setOnFriendRequestListItemClick(this);
            } else {
                friendRequestStatusLayout.setVisibility(View.GONE);
                adapter = new FriendRequestListAdapter(context, friendsBeanLists, recycler_view);
                manager = new LinearLayoutManager(context);
                manager.setSmoothScrollbarEnabled(true);
                recycler_view.setAdapter(adapter);
                recycler_view.setLayoutManager(manager);
                adapter.setOnFriendRequestListItemClick(this);

            }
        }


    }


    /*
    * 
    * 
    * Api call for getting friend list and request
    * 
    * */
    public void getFriendList() {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        friendsBeanLists = new ArrayList<>();
        sortedFriendList = new ArrayList<>();
        allFriendLists = new ArrayList<>();
        try {
            showProgressDialog(getActivity());
            Call<ParentFriendListResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .getFriendList(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY));

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<ParentFriendListResponse>() {
                @Override
                public void onResponse(Call<ParentFriendListResponse> call, Response<ParentFriendListResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (response.body().getRequestFriend() != null && response.body().getRequestFriend().size() > 0) {
                                friendsBeanLists = response.body().getRequestFriend();
                                recycler_view.addHeaderView(friendRequestHeader);
                                setListing(response.body().getRequestFriend());
                            } else {
                                recycler_view.removeAllHeaderView();
                                friendRequestStatusLayout.setVisibility(View.GONE);

                            }
                            if (response.body().getFriend() != null && response.body().getFriend().size() > 0) {
                                allFriendLists = response.body().getFriend();
                                recycler_all_friend.addHeaderView(addedFriendHeader);
                                LinearLayoutManager manager1 = new LinearLayoutManager(context);
                                allFriendAdapter = new AllFriendAdapter(context, allFriendLists, recycler_all_friend);
                                manager1.setSmoothScrollbarEnabled(true);
                                recycler_all_friend.setAdapter(allFriendAdapter);
                                recycler_all_friend.setLayoutManager(manager1);
                                allFriendAdapter.setOnAllFriendItemClickListner(AddFriendFragment.this);
                                headerAllFriendTitle.setText(getString(R.string.txt_all_friends) + (allFriendLists.size()) + ")");
                            } else {
                                recycler_all_friend.removeAllHeaderView();
                            }
                        } else {
                            friendRequestStatusLayout.setVisibility(View.GONE);
                            recycler_view.removeAllHeaderView();
                            recycler_all_friend.removeAllHeaderView();
                            ProjectUtil.showToast(getActivity(), response.body().getResponseMessage());

                        }

                    }
                    hideProgressDialog();

                }

                @Override
                public void onFailure(Call<ParentFriendListResponse> call, Throwable t) {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_friend_request_email_layout:
                ((HomeActivity) getActivity()).pushFragments(new SendFriendRequestFragment().newInstance(), null, false);
                break;
            case R.id.friendRequestStatusLayout:
                if (isSeeAll) {
                    isSeeAll = false;
                    friendRequestStatusLayout.setVisibility(View.VISIBLE);
                    arrowStatusIv.setImageResource(R.drawable.small_arrow_up);
                    seeAllBtn.setText(getString(R.string.txt_see_min));
                    adapter.setList(getActivity(), friendsBeanLists, recycler_view);
                } else {
                    isSeeAll = true;
                    friendRequestStatusLayout.setVisibility(View.VISIBLE);
                    arrowStatusIv.setImageResource(R.drawable.small_arrow);
                    seeAllBtn.setText(getString(R.string.txt_see_all) + (friendsBeanLists.size()) + ")");
                    adapter.setList(getActivity(), sortedFriendList, recycler_view);
                    recycler_view.post(new Runnable() {
                        @Override
                        public void run() {
                            int scrollTo = ((View) recycler_view.getParent().getParent()).getTop()
                                    + recycler_view.getTop();
                            nestedScrollView.smoothScrollTo(0, scrollTo);
                            //recycler_view.smoothScrollToPosition(friendsBeanLists.size());
                        }
                    });
                }
                break;
        }
    }


    @Override
    public void onItemClick(int id, int postion) {
        switch (id) {
            case R.id.accept_tv:
                acceptFriendRequest(postion, adapter.getData().get(postion));
                break;
            case R.id.declined:
                deleteRemoveAlert(postion, 1, adapter.getData().get(postion).getStudentId());
                break;
        }
    }

    @Override
    public void onAllFriendItemClick(int id, int postion) {
        switch (id) {
            case R.id.removeTv:
                deleteRemoveAlert(postion, 2, allFriendAdapter.getData().get(postion).getStudentId());
                break;
        }
    }


    /*
    *
    *
    * Api call for accepting friend List
    *
    * */
    private void acceptFriendRequest(final int postion, FriendRequestResponse friendRequestResponse) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .acceptFriendRequest(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            friendRequestResponse.getStudentId());

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                           adapter.removeItemAtPostion(postion);
                            //getFriendList();
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
* Api call to delete friend 
* 
* */

    private void removeFriendFromList(final int postion, String friendId, final int type) {
        if (!CheckNetworkState.isOnline(getActivity())) {
            ProjectUtil.showToast(getActivity(), getResources().getString(R.string.something_went_wrong));
            return;
        }
        try {
            showProgressDialog(getActivity());
            Call<CommonResponse> call = ISFApp.getAppInstance()
                    .getApi()
                    .removeFriendFromList(AppConstants.API_KEY,
                            AppConstants.CONTENT_TYPE,
                            MyPref.getInstance(getActivity()).readPrefs(AppConstants.STUDENT_KEY),
                            friendId);

            ProjectUtil.showLog(AppConstants.REQUEST, "" + call.request().url(), AppConstants.ERROR_LOG);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                    ProjectUtil.showLog(AppConstants.RESPONSE, "" + new Gson().toJson(response.body()), AppConstants.ERROR_LOG);
                    hideProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getResponseCode().equalsIgnoreCase(AppConstants.RESPONSE_CODE_SUCCUSS)) {
                            if (type == 1) {
                                adapter.removeItemAtPostion(postion);
                            } else {
                                allFriendAdapter.removeItemAtPostion(postion);
                            }
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
* Alert Dailoge if user want to exit from app
*
* */
    public void deleteRemoveAlert(final int postion, final int type, final String friendId) {
        String alertMessage;
        if (type == 1) {
            alertMessage = getString(R.string.dialog_message_decline_friend_request);
        } else {
            alertMessage = getString(R.string.dialog_message_delete_friend);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        builder.setTitle(R.string.app_name)
                .setMessage(ProjectUtil.fromHtmlAlert(getActivity().getResources().getString(R.string.dialog_message_exit)))
                .setPositiveButton(R.string.dialog_btn_yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeFriendFromList(postion, friendId, type);

                    }

                })
                .setNegativeButton(R.string.dialog_btn_no, null);

        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
    }
    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }

}
