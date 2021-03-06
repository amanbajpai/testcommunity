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


@SuppressWarnings("ALL")
public class AboutISfFragment extends BaseFragment {
    public static final String TAG = "AboutISfFragment";
    WebView webViewAboutUs;

    public static AboutISfFragment newInstance() {
        AboutISfFragment aboutISfFragment = new AboutISfFragment();
        return aboutISfFragment;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.webview_fragment, null);
        webViewAboutUs = (WebView) view.findViewById(R.id.webView);
        webViewAboutUs.getSettings().setJavaScriptEnabled(true);
        webViewAboutUs.getSettings().setDomStorageEnabled(true);
        webViewAboutUs.setWebViewClient(new AppWebViewClients());
        webViewAboutUs.loadUrl(AppConstants.ABOUT_US_URL);

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
