package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.RewardStudentResponse;
import com.gdc.isfacademy.view.adapter.RewardsAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by ashishthakur on 13/11/18.
 */

public class SendFriendRequestFragment extends BaseFragment {
    public static final String TAG = "SendFriendRequestFragment";


    public static SendFriendRequestFragment newInstance() {
        SendFriendRequestFragment sendFriendRequestFragment = new SendFriendRequestFragment();
        return sendFriendRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.send_friend_request_fragment, container, false);
        return rootView;
    }
}
