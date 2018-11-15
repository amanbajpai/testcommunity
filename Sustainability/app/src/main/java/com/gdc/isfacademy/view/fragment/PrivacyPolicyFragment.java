package com.gdc.isfacademy.view.fragment;

import android.annotation.SuppressLint;
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

public class PrivacyPolicyFragment extends BaseFragment {
    WebView webViewPrivacyPolicy;
    public static final String TAG = "PrivacyPolicyFragment";


    public static PrivacyPolicyFragment newInstance() {
        PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
        return privacyPolicyFragment;
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.webview_fragment, null);
        webViewPrivacyPolicy=(WebView)view.findViewById(R.id.webView);
        webViewPrivacyPolicy.getSettings().setJavaScriptEnabled(true);
        webViewPrivacyPolicy.getSettings().setDomStorageEnabled(true);
        webViewPrivacyPolicy.setWebViewClient(new AppWebViewClients());
        webViewPrivacyPolicy.loadUrl(AppConstants.PRIVACY_POLICY_URL);

        return view;
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
