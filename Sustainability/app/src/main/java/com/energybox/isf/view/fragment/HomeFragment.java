package com.energybox.isf.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.energybox.isf.R;
import com.energybox.isf.view.activity.HomeActivity;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class HomeFragment extends BaseFragment {
    public static final String TAG = "HomeFragment";

    private RelativeLayout how_much_save_rl;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.home_fragment, container, false);
        initView(layout);
        return layout;
    }

    private void initView(View layout) {

        how_much_save_rl = (RelativeLayout) layout.findViewById(R.id.how_much_save_rl);
        how_much_save_rl.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        switch (id) {

            case R.id.how_much_save_rl:
                HowMuchSaveFragment fragment = new HowMuchSaveFragment();
                ((HomeActivity) getActivity()).pushFragments(fragment, new Bundle(), true);
                break;

        }

    }


}
