package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.FriendsBeanList;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.AllFriendAdapter;
import com.gdc.isfacademy.view.adapter.FriendListAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansLightEditText;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class AddFriendFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "AddFriendFragment";
    public boolean isSeeAll = true;
    ArrayList<FriendsBeanList> friendsBeanLists;
    ArrayList<FriendsBeanList> newfriendsBeanLists;
    OpenSansLightEditText search_edit_text;
    Context context;
    FriendListAdapter adapter;
    AllFriendAdapter allFriendAdapter;
    AppCompatTextView seeAllBtn;
    ArrayList<FriendsBeanList> friendsBean;
    LinearLayoutManager manager;
    NestedScrollView nestedScrollView;
    private XRecyclerView recycler_view, recycler_view_new;
    private TextView headerFriendRequestTitle, headerAllFriendTitle;
    private LinearLayout friendRequestStatusLayout;
    private ImageView arrowStatusIv;
    private RelativeLayout headerSendFriendRequest, headerFriendRequest;

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
        friendsBeanLists = new ArrayList<>();
        newfriendsBeanLists = new ArrayList<>();
        friendsBeanLists = getList();
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedScrollView);
        seeAllBtn = (AppCompatTextView) view.findViewById(R.id.seeAllBtn);
        friendRequestStatusLayout = (LinearLayout) view.findViewById(R.id.friendRequestStatusLayout);
        arrowStatusIv = (ImageView) view.findViewById(R.id.arrowStatusIv);

        recycler_view = (XRecyclerView) view.findViewById(R.id.recycler_view);
        recycler_view.setLoadingMoreEnabled(false);
        recycler_view.setPullRefreshEnabled(false);
        recycler_view.setNestedScrollingEnabled(false);

        recycler_view_new = (XRecyclerView) view.findViewById(R.id.recycler_view_new);
        recycler_view_new.setPullRefreshEnabled(false);
        recycler_view_new.setLoadingMoreEnabled(false);
        recycler_view_new.setNestedScrollingEnabled(false);

        View friendRequestHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_friend_list,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        View addedFriendHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_friend_list,
                (ViewGroup) view.findViewById(android.R.id.content), false);
        headerAllFriendTitle = (TextView) addedFriendHeader.findViewById(R.id.title_header);
        headerFriendRequestTitle = (TextView) friendRequestHeader.findViewById(R.id.title_header);
        headerSendFriendRequest = (RelativeLayout) friendRequestHeader.findViewById(R.id.send_friend_request_email_layout);
        headerFriendRequest = (RelativeLayout) addedFriendHeader.findViewById(R.id.send_friend_request_email_layout);
        headerSendFriendRequest.setVisibility(View.VISIBLE);
        headerFriendRequest.setVisibility(View.GONE);
        recycler_view_new.addHeaderView(addedFriendHeader);
        recycler_view.addHeaderView(friendRequestHeader);
        headerFriendRequestTitle.setText(getString(R.string.txt_friend_request_header));
        friendRequestStatusLayout.setOnClickListener(this);
        headerSendFriendRequest.setOnClickListener(this);
        setListing();


    }

    /*
    *
    *
    * Filltering list according to data;
    *
    * */
    public void setListing() {
        if (friendsBeanLists != null && friendsBeanLists.size() > 0) {
            if (friendsBeanLists.size() > 3) {
                friendRequestStatusLayout.setVisibility(View.VISIBLE);
                seeAllBtn.setText(getString(R.string.txt_see_all) + (friendsBeanLists.size()) + ")");
                arrowStatusIv.setImageResource(R.drawable.small_arrow);
                friendsBean = new ArrayList<>();
                friendsBean.add(new FriendsBeanList(friendsBeanLists.get(0).getName()));
                friendsBean.add(new FriendsBeanList(friendsBeanLists.get(1).getName()));
                friendsBean.add(new FriendsBeanList(friendsBeanLists.get(2).getName()));
                adapter = new FriendListAdapter(context, friendsBean);
                manager = new LinearLayoutManager(context);
                manager.setSmoothScrollbarEnabled(true);
                recycler_view.setAdapter(adapter);
                recycler_view.setLayoutManager(manager);
            } else {
                friendRequestStatusLayout.setVisibility(View.GONE);
                adapter = new FriendListAdapter(context, friendsBeanLists);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setSmoothScrollbarEnabled(true);
                recycler_view.setAdapter(adapter);
                recycler_view.setLayoutManager(manager);
            }
        }
        LinearLayoutManager manager1 = new LinearLayoutManager(context);
        allFriendAdapter = new AllFriendAdapter(context, newfriendsBeanLists);
        manager1.setSmoothScrollbarEnabled(true);
        recycler_view_new.setAdapter(allFriendAdapter);
        recycler_view_new.setLayoutManager(manager1);
        newfriendsBeanLists = getNewList();
        headerAllFriendTitle.setText(getString(R.string.txt_all_friends) + (newfriendsBeanLists.size()) + ")");


    }


    /*
      *
      * Dummy friend request List
      * */
    public ArrayList<FriendsBeanList> getList() {

        friendsBeanLists.add(new FriendsBeanList("Isabelle"));
        friendsBeanLists.add(new FriendsBeanList("Sebrina"));
        friendsBeanLists.add(new FriendsBeanList("Kelsie"));
        friendsBeanLists.add(new FriendsBeanList("Sean"));
        friendsBeanLists.add(new FriendsBeanList("Eliz"));
        friendsBeanLists.add(new FriendsBeanList("Jose"));
        friendsBeanLists.add(new FriendsBeanList("Michal"));
        friendsBeanLists.add(new FriendsBeanList("Rimmy"));
        friendsBeanLists.add(new FriendsBeanList("John"));
        friendsBeanLists.add(new FriendsBeanList("Rogerr"));
        friendsBeanLists.add(new FriendsBeanList("Nathan"));
        friendsBeanLists.add(new FriendsBeanList("Kangiso"));
        friendsBeanLists.add(new FriendsBeanList("Dale"));

        return friendsBeanLists;
    }


    /*
    *
    * Dummy alredy friend list List
    * */
    public ArrayList<FriendsBeanList> getNewList() {

        newfriendsBeanLists.add(new FriendsBeanList("1"));
        newfriendsBeanLists.add(new FriendsBeanList("2"));
        newfriendsBeanLists.add(new FriendsBeanList("3"));
        newfriendsBeanLists.add(new FriendsBeanList("4"));
        newfriendsBeanLists.add(new FriendsBeanList("5"));
        newfriendsBeanLists.add(new FriendsBeanList("6"));
        newfriendsBeanLists.add(new FriendsBeanList("7"));
        newfriendsBeanLists.add(new FriendsBeanList("8"));
        newfriendsBeanLists.add(new FriendsBeanList("9"));
        newfriendsBeanLists.add(new FriendsBeanList("10"));


        return newfriendsBeanLists;
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
                    adapter.setList(getActivity(), friendsBean, recycler_view);
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
}
