package com.library.AsyncHelper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.library.HttpRequest.HttpUrlConn;
import com.library.Security.CertificateSHA1Fingerprint;
import com.sm.solvelistviewprobmel.BuildConfig;

import java.util.HashMap;

/**
 * Created by Rz Rasel on 01-Aug-16.
 */
public class AsyncBusiness {
    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
    private static AsyncBusiness instance = null;
    //|------------------------------------------------------------|
    private Context context;
    private ListenerHandler listenerHandler;
    private HashMap<String, String> urlHeaderList = new HashMap<String, String>();

    //|------------------------------------------------------------|
    public AsyncBusiness() {
        //LogWriter.Log("Constructor called");
    }

    public static AsyncBusiness getInstance() {
        if (instance == null) {
            instance = new AsyncBusiness();
        }
        return instance;
    }

    //|------------------------------------------------------------|
    public void setUrlHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlHeaderList = argUrlHeaderList;
        //LogWriter.Log("PRINT_HEADER_LIST_ASYNC_BUSINESS00000:- " + argUrlHeaderList.toString());
        //urlHeaderList.putAll(argUrlHeaderList);
    }
    //|------------------------------------------------------------|

    public void execute(Context argContext, String argRequestUrl, HashMap<String, String> argPostParameter, ListenerHandler argListenerHandler) {
        this.context = argContext;
        listenerHandler = argListenerHandler;
        new AsyncHandler(argPostParameter).execute(argRequestUrl);
    }
    //|------------------------------------------------------------|

    public interface ListenerHandler {
        void onPreExecute();

        void doInBackground(HashMap<String, String> argHttpData);

        void onPostExecute(HashMap<String, String> argResult);
    }
    //|------------------------------------------------------------|

    private class AsyncHandler extends AsyncTask<String, Void, HashMap<String, String>> {
        private HashMap<String, String> urlParameter = null;
        private HashMap<String, String> hashMapAsyncResult;
        private HttpUrlConn httpUrlConn = null;

        public AsyncHandler(HashMap<String, String> argPostParameter) {
            urlParameter = argPostParameter;
        }

        @Override
        protected void onPreExecute() {
            if (listenerHandler != null) {
                listenerHandler.onPreExecute();
            }
        }

        @Override
        protected HashMap<String, String> doInBackground(String... argUrls) {
            String requestUrl = argUrls[0];
            //LogWriter.Log(requestUrl);
            //HashMap<String, String> urlParameter = new HashMap<String, String>();
            /*urlParameter.put("sha1_key", "sha1_key");
            urlParameter.put("name", "TestName");
            urlParameter.put("id", "TestId");*/
            CertificateSHA1Fingerprint certSHA1Fprint = CertificateSHA1Fingerprint.getInstance();
            //REQUEST_AUTHORIZATION
            if (BuildConfig.DEBUG) {
                urlParameter.put("auth_key", "F3:DE:B3:32:11:1D:E1:AB:26:5D:D8:D8:B2:15:EF:E3:BF:04:CA:2C");
                urlParameter.put("package_name", context.getPackageName());
                urlParameter.put("app_version", getAppVersion(context));
            } else {
                urlParameter.put("auth_key", certSHA1Fprint.getCertificateSHA1Fingerprint(context));
                urlParameter.put("package_name", context.getPackageName());
                urlParameter.put("app_version", getAppVersion(context));
            }

            hashMapAsyncResult = new HashMap<String, String>();
            httpUrlConn = HttpUrlConn.getInstance();
            if (urlHeaderList.size() > 0) {
                //LogWriter.Log("PRINT_HEADER_LIST_ASYNC_BUSINESS:- " + urlHeaderList.toString());
                httpUrlConn.setUrlHeader(urlHeaderList);
            }
            httpUrlConn.postInit(requestUrl, urlParameter, new HttpUrlConn.ListenerHandler() {
                @Override
                public void HTTPDataListener(int argResponseCode, String argHTTPData) {
                    hashMapAsyncResult.put("response_code", argResponseCode + "");
                    hashMapAsyncResult.put("response_data", argHTTPData + "");
                    listenerHandler.doInBackground(hashMapAsyncResult);
                }
            });
            return hashMapAsyncResult;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> argResult) {
            if (listenerHandler != null) {
                listenerHandler.onPostExecute(argResult);
            }
        }

        public String getAppVersion(Context context) {
            PackageManager manager = context.getPackageManager();
            try {
                //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                //return packageInfo.versionCode;
                //packageInfo.packageName;
                return packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                return "";
            }
        }
    }
}