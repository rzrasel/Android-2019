package com.library.iConn;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.library.LogWriter;

/**
 * Created by Rz Rasel on 01-Aug-16.
 */
public class NetConnDetect {
    private static NetConnDetect instance = null;
    public static NetConnDetect getInstance() {
        if (instance == null) {
            instance = new NetConnDetect();
        }
        return instance;
    }
    public boolean isConnected(Context argContext) {
        //if (Build.VERSION.SDK_INT >= 23)
        int hasPermission = 0;
        hasPermission = ContextCompat.checkSelfPermission((Activity) argContext, Manifest.permission.INTERNET);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            LogWriter.Log("Check it", "Please set the permission INTERNET");
            return false;
        }
        hasPermission = ContextCompat.checkSelfPermission((Activity) argContext, Manifest.permission.ACCESS_NETWORK_STATE);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            LogWriter.Log("Check it", "Please set the permission ACCESS_NETWORK_STATE");
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        //Toast.makeText(mContext,mContext.getString(R.string.please_connect_to_internet),Toast.LENGTH_SHORT).show();
        return false;
    }

    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

    /**
     * Checking for all possible internet providers
     **/
    public boolean isConnectingToInternet(Context argContext) {
        ConnectivityManager connectivity = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

    private boolean checkInternetConnection_temp(Context argContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isConnected_01(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
//<uses-permission android:name="android.permission.INTERNET" />
//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />