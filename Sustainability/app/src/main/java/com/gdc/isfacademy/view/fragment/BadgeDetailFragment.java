package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.adapter.BadgeDetailAdapter;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class BadgeDetailFragment extends BaseFragment {
    public static final String TAG = "BadgeDetailFragment";
    ArrayList<BadgeStudentResponse> badgeStudentResponses;
    ArrayList<BadgeStudentResponse> localBadgeStudentResponses;

    private BadgeDetailAdapter badgeDetailAdapter;
    private RecyclerView badge_detail_recylerview;

    public static BadgeDetailFragment newInstance() {
        BadgeDetailFragment badgeDetailFragment = new BadgeDetailFragment();
        return badgeDetailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.badge_detail_fragment, null);
        init(view);

        return view;
    }



    public void init(View view) {
        badgeStudentResponses = new ArrayList<>();
        Bundle bundle = getArguments();
        badgeStudentResponses = (ArrayList<BadgeStudentResponse>) bundle.getSerializable("list");
        localBadgeStudentResponses=new ArrayList<>();
        localBadgeStudentResponses.add(new BadgeStudentResponse("1","0.00"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("2","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("3","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("4","0"));
        //localBadgeStudentResponses.add(new BadgeStudentResponse("5","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("6","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("7","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("8","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("9","0"));
        localBadgeStudentResponses.add(new BadgeStudentResponse("10","0"));

        for (int i=0;i<localBadgeStudentResponses.size();i++){

            for (int j=0;j<badgeStudentResponses.size();j++){
                if(localBadgeStudentResponses.get(i).getBadgesType().equalsIgnoreCase(badgeStudentResponses.get(j).getBadgesType())){
                    localBadgeStudentResponses.get(i).setBadgesType(badgeStudentResponses.get(j).getBadgesType());
                    localBadgeStudentResponses.get(i).setValue(badgeStudentResponses.get(j).getValue());

                }
            }
        }
        badge_detail_recylerview = (RecyclerView) view.findViewById(R.id.badge_detail_recylerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        badge_detail_recylerview.setLayoutManager(linearLayoutManager);
        badgeDetailAdapter = new BadgeDetailAdapter(getActivity(), localBadgeStudentResponses);
        badge_detail_recylerview.setAdapter(badgeDetailAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).backBtn.setVisibility(View.VISIBLE);
        ((HomeActivity) getActivity()).sliderIcon.setVisibility(View.GONE);
    }
}
