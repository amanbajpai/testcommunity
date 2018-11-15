package com.gdc.isfacademy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;

/**
 * Created by ashishthakur on 5/9/18.
 */

public class TermsConditionFragment extends BaseFragment {
    public static final String TAG = "TermsConditionFragment";
    WebView webviewTermsAndCondition;

    public static TermsConditionFragment newInstance() {
        TermsConditionFragment termsConditionFragment = new TermsConditionFragment();
        return termsConditionFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        webviewTermsAndCondition = (WebView) rootView.findViewById(R.id.webView);
        webviewTermsAndCondition.getSettings().setJavaScriptEnabled(true);
        webviewTermsAndCondition.getSettings().setDomStorageEnabled(true);
        webviewTermsAndCondition.setWebViewClient(new AppWebViewClients());
        webviewTermsAndCondition.loadUrl(AppConstants.TERMS_AND_CONDITION_URL);
        return rootView;
    }

    public class AppWebViewClients extends WebViewClient {

        public AppWebViewClients() {
            showProgressDialog(getActivity());
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            hideProgressDialog();
        }
    }
}
