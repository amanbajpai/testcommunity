package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.view.activity.HomeActivity;

/**
 * Created by akshaydashore on 3/4/18
 */

public class QuizePagerFragment extends BaseFragment {
    public static final String TAG = "QuizePagerFragment";
    private CardView answer_three, answer_two, answer_one;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quize_pager_item, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        answer_three = (CardView) view.findViewById(R.id.answer_three);
        answer_two = (CardView) view.findViewById(R.id.answer_three);
        answer_one = (CardView) view.findViewById(R.id.answer_three);

        answer_three.setOnClickListener(this);
        answer_two.setOnClickListener(this);
        answer_one.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);

        int id = view.getId();
        switch (id) {

            case R.id.answer_three:
            case R.id.answer_two:
            case R.id.answer_one:
                ((HomeActivity) getActivity()).pushFragments(new QuizeCompletedFragement(), null, true);
                break;
        }


    }

}
