package com.gdc.isfacademy.netcom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetworkState {

    /**
     * This method is use for check the network status.
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else {
                return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        } else {
            return false;
        }

    }
}