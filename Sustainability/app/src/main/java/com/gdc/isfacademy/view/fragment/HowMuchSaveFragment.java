package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;

/**
 * Created by akshaydashore on 2/4/18
 */

public class HowMuchSaveFragment extends BaseFragment {

    public static final String TAG = "HowMuchSaveFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static HowMuchSaveFragment newInstance() {
        HowMuchSaveFragment howMuchSaveFragment = new HowMuchSaveFragment();
        return howMuchSaveFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.how_much_saved_layout, null);
        return view;
    }

}
