package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.gdc.isfacademy.R;



@SuppressWarnings("ALL")
public class AboutISfFragment extends BaseFragment {
    WebView aboutIsfWebView;
    public static final String TAG = "AboutISfFragment";


    public static AboutISfFragment newInstance() {
        AboutISfFragment addFriendFragment = new AboutISfFragment();
        return addFriendFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.about_isf_fragment, null);
        aboutIsfWebView=(WebView)view.findViewById(R.id.aboutIsfWebView);
        aboutIsfWebView.getSettings().setJavaScriptEnabled(true);
        aboutIsfWebView.getSettings().setDomStorageEnabled(true);
        aboutIsfWebView.loadUrl("https://www.isf.edu.hk/");

        return view;
    }
}
