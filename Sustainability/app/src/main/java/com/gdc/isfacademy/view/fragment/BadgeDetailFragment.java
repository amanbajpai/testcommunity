package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.model.BadgeStudentResponse;
import com.gdc.isfacademy.view.adapter.BadgeDetailAdapter;

import java.util.ArrayList;

/**
 * Created by ashishthakur on 15/8/18.
 */

public class BadgeDetailFragment extends BaseFragment {
    public static final String TAG = "BadgeDetailFragment";
    private BadgeDetailAdapter badgeDetailAdapter;

    private RecyclerView badge_detail_recylerview;
ArrayList<BadgeStudentResponse>badgeStudentResponses;

    public static BadgeDetailFragment newInstance() {
        BadgeDetailFragment badgeDetailFragment = new BadgeDetailFragment();
        return badgeDetailFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.badge_detail_fragment, null);
        init(view);

        return view;
    }


    public void init(View view){
        badgeStudentResponses=new ArrayList<>();
        Bundle bundle = getArguments();
        badgeStudentResponses = (ArrayList<BadgeStudentResponse>) bundle.getSerializable("list");
        badge_detail_recylerview=(RecyclerView)view.findViewById(R.id.badge_detail_recylerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        badge_detail_recylerview.setLayoutManager(linearLayoutManager);
        badgeDetailAdapter=new BadgeDetailAdapter(getActivity(),badgeStudentResponses);
        badge_detail_recylerview.setAdapter(badgeDetailAdapter);

    }
}
