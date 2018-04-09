package com.energybox.isf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.energybox.isf.R;
import com.energybox.isf.utils.ProjectUtil;
import com.energybox.isf.view.activity.HomeActivity;
import com.energybox.isf.view.customs.customfonts.OpenSansSemiBoldTextView;

/**
 * Created by akshaydashore on 3/4/18
 */
public class QuizeCompletedFragement extends BaseFragment {

    public static final String TAG = "QuizeCompletedFragement";

    private OpenSansSemiBoldTextView share_tv;
    HomeActivity activity;
    Context context;

    public static QuizeCompletedFragement newInstance() {
        QuizeCompletedFragement quizeFragment = new QuizeCompletedFragement();
        return quizeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (HomeActivity) getActivity();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_quize_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        share_tv = (OpenSansSemiBoldTextView) view.findViewById(R.id.share_tv);
        share_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        switch (id) {

            case R.id.share_tv:
                ProjectUtil.showShareDialog(context, this);
                break;

            case R.id.profile_share_tv:
                break;

        }
    }


}
