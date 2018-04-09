package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.gdc.isfacademy.model.RewardListResponse;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.adapter.RewardsAdapter;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 5/4/18.
 */

public class RewardsFragment extends BaseFragment implements RewardsAdapter.OnRewardItemClickListner {
    public static final String TAG = "RewardsFragment";

    XRecyclerView rewards_recylerview;
    ArrayList<RewardListResponse> rewardListResponses;
    RewardsAdapter rewardsAdapter;

    public static RewardsFragment newInstance() {
        RewardsFragment rewardsFragment = new RewardsFragment();
        return rewardsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.rewards_fragment, container, false);
        init();
        return rootView;
    }

    public void init() {
        rewardListResponses = new ArrayList<>();
        rewardListResponses.add(new RewardListResponse(false));
        rewards_recylerview = (XRecyclerView) rootView.findViewById(R.id.rewards_recylerview);
        rewards_recylerview.setLoadingMoreEnabled(false);
        rewards_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_rewards,
                (ViewGroup) rootView.findViewById(android.R.id.content), false);
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
}
