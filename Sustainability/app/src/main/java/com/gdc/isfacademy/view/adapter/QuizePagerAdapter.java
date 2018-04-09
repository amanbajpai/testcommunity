package com.gdc.isfacademy.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gdc.isfacademy.view.fragment.QuizePagerFragment;

/**
 * Created by akshaydashore on 3/4/18
 */

public class QuizePagerAdapter extends FragmentStatePagerAdapter {


    public QuizePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        QuizePagerFragment fragment = new QuizePagerFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
