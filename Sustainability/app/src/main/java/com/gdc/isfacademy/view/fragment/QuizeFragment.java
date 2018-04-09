package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.adapter.QuizePagerAdapter;

import net.alexandroid.utils.indicators.IndicatorsView;

/**
 * Created by akshaydashore on 3/4/18
 */
public class QuizeFragment extends BaseFragment {

    public static final String TAG = "QuizeFragment";

    private ViewPager viewpager;
    private IndicatorsView indicatorsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView  = inflater.inflate(R.layout.quize_fragment_layout,null);
        initView(rootView);
        return rootView;
    }

    private void initView(View view) {

        viewpager =  (ViewPager)view.findViewById(R.id.viewpager);
        QuizePagerAdapter adapter = new QuizePagerAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        indicatorsView = (IndicatorsView)view.findViewById(R.id.indicatorsView);

        indicatorsView.setViewPager(viewpager);

        indicatorsView.setSmoothTransition(true);

        indicatorsView.setIndicatorsClickChangePage(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView(rootView);

    }
}
