package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rz.librarycore.http.HTTPMethod;
import com.rz.librarycore.http.OnFeedHTTPEventListenerHandler;
import com.rz.librarycore.http.PowerFeedHTTPAsyncTask;
import com.rz.librarycore.log.LogWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


public class FragTest extends android.app.Fragment {
    private Activity activity;
    private Context context;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater argInflater, ViewGroup argContainer, Bundle argSavedInstanceState) {
        rootView = argInflater.inflate(R.layout.frag_test, argContainer, false);
        activity = getActivity();
        //context = argContainer.getContext();
        context = argInflater.getContext();
        onPostMethodOne();
        return rootView;
    }

    private void onPostMethodOne() {
        HashMap<String, String> urlHeaders = new HashMap<String, String>();
        HashMap<String, String> urlRequestParameters = new HashMap<String, String>();
        //urlRequestParameters.putAll(PrepareHTTPRequest.getURLPostParameters(context));
        PowerFeedHTTPAsyncTask powerFeedHTTPAsyncTask = new PowerFeedHTTPAsyncTask(new OnFeedHTTPEventListenerHandler() {
            @Override
            public void onPreExecute() {
            }

            @Override
            public Object doInBackground(String... argURLParams) {
                LogWriter.Log("RETURNED_VALUE: " + String.valueOf(argURLParams));
                if (argURLParams instanceof String[]) {
                    /*String[] strArray = (String[]) argURLParams;
                    System.out.println("RETURNED_VALUE********: " + Arrays.toString(strArray));*/
                    String[] strArray = (String[]) argURLParams;
                    //String urlData = Arrays.toString(strArray);
                    String urlData = strArray[0];
                    System.out.println("RETURNED_VALUE_DO_IN_BACK: " + urlData);
                    // System.out.println(obj);
                    //onJSONParse(urlData);
                    // System.out.println(obj);
                }
                return argURLParams;
            }

            //Arrays.asList(strArray).toString().substring(1).replaceFirst("]", "").replace(", ", "")
            @Override
            public void onPostExecute(Object argResult) {
                //LogWriter.Log("onPostExecute: " + Arrays.toString(argResult) + "");
                if (argResult instanceof String[]) {
                    String[] strArray = (String[]) argResult;
                    String urlParsedData = Arrays.asList(strArray).toString().substring(1).replaceFirst("]", "");
                    System.out.println("RETURNED_VALUE********: " + urlParsedData);
                    if (CheckJSON.isValidJSON(urlParsedData)) {
                        LogWriter.Log("JOSON IS VALID");
                        try {
                            JSONArray jsonArray = new JSONArray(urlParsedData);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String imageUrl = jsonObject.getString("image_url");
                                String videoUrl = jsonObject.getString("video_url");
                                LogWriter.Log("NAME: " + name + " VIDEO: " + videoUrl);
                            }
                        } catch (JSONException e) {
                            //e.printStackTrace();
                            LogWriter.Log("ERROR: " + e.getMessage());
                        }
                    }
                    //System.out.println(obj);
                }
                Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                LogWriter.Log("onPostExecute: " + format.format(new Date()));
                LogWriter.Log("onPostExecute: " + argResult + "");
            }

            @Override
            public void onProgressUpdate(Integer... argProgressValue) {
            }

            @Override
            public void onCancelled() {
            }
        });
        //http://fbapp.epizy.com/api-data-structure/get-data.php
        //https://wbandapp.000webhostapp.com/
        powerFeedHTTPAsyncTask
                .setHTTPMethod(HTTPMethod.POST)
                .setUrlHeader(urlHeaders)
                .setURLParameters(urlRequestParameters)
                .onExecute(context, "https://wbandapp.000webhostapp.com/get-data.php");
    }

    @Override
    public void onAttach(Context argContext) {
        super.onAttach(argContext);
        /*if (argContext instanceof Activity) {
            this.listener = (FragmentActivity) argContext;
        }*/
        //fragmentCallback = (FragmentCallback) argContext;
        try {
            //eventListener = (EventListener) context;
            //fragmentCallback = (FragmentCallback) argContext;
        } catch (ClassCastException e) {
            //LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
        /*try {
            //eventListener = (EventListener) context;
            fragmentEventListener = (FragmentEventListener) context;
        } catch (ClassCastException e) {
            LogWriter.Log(context.toString() + " must implement EventListener");
            //throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }*/
    }

    @Override
    public void onDetach() {
        //fragmentCallback = null;
        super.onDetach();
        /*super.onDetach();
        this.listener = null;*/
    }

    public static class PrepareHTTPRequest {
        private Context context;
        //private HashMap<String, String> mapConstantParameters = new HashMap<>();

        public PrepareHTTPRequest(Context argContext) {
            context = argContext;
        }

        public void onExecute() {
            //
        }

        public static HashMap<String, String> getURLPostParameters(Context argContext) {
            Format staticFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HashMap<String, String> urlRequestParameters = new HashMap<>();
            /*SharePrefPrivateHandler statPreferences = null;
            statPreferences = new SharePrefPrivateHandler(argContext, APPStaticPackageInfo.getPackageName(argContext));
            urlRequestParameters.put("auth_key", statPreferences.getValue(SecureKeyManager.KeyAppAuthKey) + "");
            urlRequestParameters.put("fcm_key_token", statPreferences.getValue(SecureKeyManager.KeyAppFCMKeyToken) + "");
            urlRequestParameters.put("package_name", APPStaticPackageInfo.getPackageName(argContext));
            urlRequestParameters.put("app_version_code", APPStaticPackageInfo.getVersionCode(argContext) + "");
            urlRequestParameters.put("app_version_name", APPStaticPackageInfo.getVersionName(argContext));
            urlRequestParameters.put("app_global_ip", statPreferences.getValue(SecureKeyManager.KeyDeviceGlobalNetIp) + "");
            urlRequestParameters.put("app_hardware_ip", statPreferences.getValue(SecureKeyManager.KeyDeviceHardWareIp) + "");
            urlRequestParameters.put("app_net_lat", statPreferences.getValue(SecureKeyManager.KeyDeviceNetLatitude) + "");
            urlRequestParameters.put("app_net_lng", statPreferences.getValue(SecureKeyManager.KeyDeviceNetLongitude) + "");
            urlRequestParameters.put("app_net_country", statPreferences.getValue(SecureKeyManager.KeyDeviceNetCountry) + "");
            urlRequestParameters.put("app_primary_id", statPreferences.getValue(SecureKeyManager.KeyDevicePrimaryId) + "");
            urlRequestParameters.put("app_secondary_id", statPreferences.getValue(SecureKeyManager.KeyDeviceSecondaryId) + "");
            urlRequestParameters.put("app_request_time", staticFormat.format(new Date()));*/
            return urlRequestParameters;
        }
    }
}