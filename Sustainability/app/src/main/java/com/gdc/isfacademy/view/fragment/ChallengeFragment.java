package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.ChallangeRankList;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.ChallengeAdapter;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by akshaydashore on 3/4/18
 */

public class ChallengeFragment extends BaseFragment {
    public static final String TAG = "ExploreFragment";

    public XRecyclerView challenge_recylerview;
    HomeActivity activity;
    Context context;
    ChallengeAdapter challengeAdapter;
    ArrayList<ChallangeRankList> challangeRankLists;
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
        challangeRankLists = getList();
        challenge_recylerview = (XRecyclerView) view.findViewById(R.id.challenge_recylerview);
        challenge_recylerview.setLoadingMoreEnabled(false);
        challenge_recylerview.setPullRefreshEnabled(false);
        View voucherHeader = LayoutInflater.from(getActivity()).inflate(R.layout.header_challange,
                (ViewGroup) view.findViewById(android.R.id.content), false);

        challenge_recylerview.addHeaderView(voucherHeader);
        add_friend_tv = (OpenSansSemiBoldTextView) voucherHeader.findViewById(R.id.add_friend_tv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        challenge_recylerview.setLayoutManager(linearLayoutManager);
        challengeAdapter = new ChallengeAdapter(getActivity(),challangeRankLists);
        challenge_recylerview.setAdapter(challengeAdapter);
        start_quize_image = (ImageView) voucherHeader.findViewById(R.id.start_quize_image);
        add_friend_tv.setOnClickListener(this);
        start_quize_image.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity)getActivity()).backBtn.setVisibility(View.GONE);
        ((HomeActivity)getActivity()).sliderIcon.setVisibility(View.VISIBLE);


    }

    /*
    *
    * Dummy List
    *
    * */

    public ArrayList<ChallangeRankList> getList() {
        challangeRankLists.add(new ChallangeRankList("1", "2,753", "Sebrina"));
        challangeRankLists.add(new ChallangeRankList("2", "2,664", "Kelsi"));
        challangeRankLists.add(new ChallangeRankList("3", "2,653", "Jose"));
        challangeRankLists.add(new ChallangeRankList("4", "2,053", "Eliz"));
        challangeRankLists.add(new ChallangeRankList("5", "1,922", "Gabby"));
        challangeRankLists.add(new ChallangeRankList("6", "1,053", "Isabelle"));
        challangeRankLists.add(new ChallangeRankList("7", "1,045", "Jane Doe"));
        challangeRankLists.add(new ChallangeRankList("8", "950", "Sean"));
        challangeRankLists.add(new ChallangeRankList("9", "500", "Michal"));
        challangeRankLists.add(new ChallangeRankList("10", "434", "Hajel"));
        challangeRankLists.add(new ChallangeRankList("11", "378", "Marcus"));
        challangeRankLists.add(new ChallangeRankList("12", "301", "Susan"));
        challangeRankLists.add(new ChallangeRankList("13", "289", "Philip"));
        challangeRankLists.add(new ChallangeRankList("14", "200", "Stephen"));
        challangeRankLists.add(new ChallangeRankList("15", "198", "Maria"));
        challangeRankLists.add(new ChallangeRankList("16", "190", "Novak"));


        return challangeRankLists;
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
