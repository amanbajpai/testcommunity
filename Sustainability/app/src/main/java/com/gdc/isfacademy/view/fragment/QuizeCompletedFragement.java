package com.gdc.isfacademy.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;
import com.gdc.isfacademy.utils.ProjectUtil;
import com.gdc.isfacademy.view.activity.HomeActivity;
import com.gdc.isfacademy.view.customs.customfonts.OpenSansSemiBoldTextView;

/**
 * Created by akshaydashore on 3/4/18
 */
public class QuizeCompletedFragement extends BaseFragment {

    public static final String TAG = "QuizeCompletedFragement";
    HomeActivity activity;
    Context context;
    int count;
    private OpenSansSemiBoldTextView share_tv;

    public static QuizeCompletedFragement newInstance() {
        QuizeCompletedFragement quizeFragment = new QuizeCompletedFragement();
        return quizeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        activity = (HomeActivity) getActivity();
        Bundle bundle = getArguments();
        count = bundle.getInt(AppConstants.ANSWER_COUNT);

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
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Earned" + " " + count + " " + "Points in ISF Community Quize");
                sendIntent.setType("text/plain");
                startActivityForResult(Intent.createChooser(sendIntent, getResources().getText(R.string.txt_send_to)), 101);
                // ProjectUtil.showShareDialog(context, this);
                break;

            case R.id.profile_share_tv:
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                ((HomeActivity) getActivity()).pushFragments(ChallengeFragment.newInstance(), null, true);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                ProjectUtil.showToast(getActivity(),"Cancel");
            }
        }
    }
}
