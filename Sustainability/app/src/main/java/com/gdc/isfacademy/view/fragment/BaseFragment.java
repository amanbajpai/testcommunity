package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.view.activity.HomeActivity;

/**
 * Created by ashishthakur on 29/3/18.
 */

public class BaseFragment extends Fragment implements View.OnClickListener{
    View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

    }


    /**
     * Use to show loading dialog.
     */
    protected void showProgressDialog(Context context) {
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity)getActivity()).showProgressDialog(context);
        }
    }

    /**
     * Use to hide loading dialog.
     */
    protected void hideProgressDialog() {
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity)getActivity()).hideProgressDialog();
        }
    }
}
