package com.gdc.isfacademy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdc.isfacademy.R;
import com.gdc.isfacademy.utils.AppConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ashishthakur on 23/1/19.
 */

public class FaqFragment extends BaseFragment {
    public static final String TAG = "FaqFragment";
    WebView webviewFaq;

    public static FaqFragment newInstance() {
        FaqFragment faqFragment = new FaqFragment();
        return faqFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        webviewFaq = (WebView) rootView.findViewById(R.id.webView);
        webviewFaq.getSettings().setJavaScriptEnabled(true);
        webviewFaq.getSettings().setDomStorageEnabled(true);
        webviewFaq.setWebViewClient(new FaqFragment.AppWebViewClients());
        /*webviewFaq.getSettings().setBuiltInZoomControls(true);
        webviewFaq.getSettings().setSupportZoom(true);*/
        webviewFaq.loadDataWithBaseURL("", AppConstants.getContent(getActivity(),ReadFromfile("image_faq_badge")), "text/html", "UTF-8", "");
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




    public String ReadFromfile(String fileName) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = getActivity().getResources().getAssets()
                    .open(fileName, Context.MODE_PRIVATE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        Log.e("imagePath",""+returnString.toString());
        return returnString.toString();
    }
}
