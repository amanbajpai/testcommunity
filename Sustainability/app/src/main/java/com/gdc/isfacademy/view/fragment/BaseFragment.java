package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdc.isfacademy.view.activity.HomeActivity;



@SuppressWarnings("ALL")
public class BaseFragment extends Fragment{
    View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
