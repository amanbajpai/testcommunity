package com.gdc.isfacademy.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;

/**
 * Created by ashishthakur on 16/11/18.
 */

public class RealTimeSchoolFragment extends BaseFragment {
    public static final String TAG = "RealTimeSchoolFragment";
    WebView realTimeWebView;

    public static RealTimeSchoolFragment newInstance() {
        RealTimeSchoolFragment realTimeSchoolFragment = new RealTimeSchoolFragment();
        return realTimeSchoolFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        realTimeWebView = (WebView) rootView.findViewById(R.id.webView);
        realTimeWebView.getSettings().setJavaScriptEnabled(true);
        realTimeWebView.getSettings().setDomStorageEnabled(true);
        realTimeWebView.setWebViewClient(new AppWebViewClients());
        if (Build.VERSION.SDK_INT >= 19) {
            realTimeWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            realTimeWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        realTimeWebView.loadUrl(AppConstants.REAL_TIME_SITE);

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
