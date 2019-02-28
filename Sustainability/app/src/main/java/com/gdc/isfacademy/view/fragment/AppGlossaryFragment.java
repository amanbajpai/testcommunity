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
public class AppGlossaryFragment  extends BaseFragment {
    public static final String TAG = "AppGlossaryFragment";
    public static AppGlossaryFragment newInstance() {
        return new AppGlossaryFragment();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        WebView webviewFaq = (WebView) rootView.findViewById(R.id.webView);
        webviewFaq.getSettings().setJavaScriptEnabled(true);
        webviewFaq.getSettings().setDomStorageEnabled(true);
        webviewFaq.setWebViewClient(new AppWebViewClients());
        webviewFaq.loadDataWithBaseURL("", AppConstants.getGlossary(), "text/html", "UTF-8", "");
        return rootView;
    }

    public class AppWebViewClients extends WebViewClient {

        AppWebViewClients() {
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
