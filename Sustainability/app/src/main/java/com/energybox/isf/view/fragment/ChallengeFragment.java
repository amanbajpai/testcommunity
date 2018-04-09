package com.energybox.isf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.energybox.isf.R;
import com.energybox.isf.view.activity.HomeActivity;
import com.energybox.isf.view.adapter.ChallengeAdapter;
import com.energybox.isf.view.customs.customfonts.OpenSansSemiBoldTextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by akshaydashore on 3/4/18
 */

public class ChallengeFragment extends BaseFragment {
    public static final String TAG = "ExploreFragment";

    public XRecyclerView challenge_recylerview;
    HomeActivity activity;
    Context context;
    ChallengeAdapter challengeAdapter;
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
        challenge_recylerview = (XRecyclerView) view.findViewById(R.id.challenge_recylerview);
        challenge_recylerview.setLoadingMoreEnabled(false);
        challenge_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_challange,
                (ViewGroup) view.findViewById(android.R.id.content), false);

        challenge_recylerview.addHeaderView(voucherHeader);
        add_friend_tv = (OpenSansSemiBoldTextView) voucherHeader.findViewById(R.id.add_friend_tv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        challenge_recylerview.setLayoutManager(linearLayoutManager);
        challengeAdapter = new ChallengeAdapter(getActivity());
        challenge_recylerview.setAdapter(challengeAdapter);
        start_quize_image = (ImageView) voucherHeader.findViewById(R.id.start_quize_image);
        add_friend_tv.setOnClickListener(this);
        start_quize_image.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        switch (id) {

            case R.id.add_friend_tv:
                ((HomeActivity) getActivity()).pushFragments(new AddFriendFragment(), null, true);
                break;

            case R.id.start_quize_image:
                ((HomeActivity) getActivity()).pushFragments(new QuizeFragment(), null, true);
                break;
        }
    }

}
