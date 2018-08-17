package com.gdc.isfacademy.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gdc.isfacademy.view.fragment.QuizePagerFragment;

import java.util.List;



@SuppressWarnings("ALL")
public class QuizePagerAdapter extends FragmentStatePagerAdapter {
    List<QuizePagerFragment> fragmentList;

    public QuizePagerAdapter(FragmentManager fm, List<QuizePagerFragment> fragmentList) {
        super(fm);

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
