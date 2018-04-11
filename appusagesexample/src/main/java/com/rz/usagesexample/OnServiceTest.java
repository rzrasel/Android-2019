package com.rz.usagesexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.rz.librarycore.apppackage.APPStaticPackageInfo;
import com.rz.librarycore.http.HTTPMethod;
import com.rz.librarycore.http.OnFeedHTTPEventListenerHandler;
import com.rz.librarycore.http.PowerFeedHTTPAsyncTask;
import com.rz.librarycore.log.LogWriter;
import com.rz.librarycore.log.SecureKeyManager;
import com.rz.librarycore.storage.SharePrefPrivateHandler;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Rz Rasel on 2018-03-19.
 */

public class OnServiceTest extends Service {
    private Context context;
    private SharePrefPrivateHandler sharePrefHandler;
    private boolean runInfinity = true;

    @Override
    public void onCreate() {
        LogWriter.Log("SERVICE_CREATE");
    }

    @Override
    public IBinder onBind(Intent argIntent) {
        LogWriter.Log("SERVICE_BIND");
        return null;
    }

    @Override
    public int onStartCommand(Intent argIntent, int argFlags, int argStartId) {
        context = this;
        // Let it continue running until it is stopped.
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        LogWriter.Log("SERVICE_STARTED");
        sharePrefHandler = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        Object objIsFirstRun = sharePrefHandler.getValue("app_is_first_run");
        Object objIsAPPFirstRun = sharePrefHandler.getValue("app_is_first_run_online");
        boolean isFirstRun = false;
        boolean isAPPFirstRun = false;
        if (objIsFirstRun != null) {
            isFirstRun = (Boolean) objIsFirstRun;
        }
        if (objIsAPPFirstRun != null) {
            isAPPFirstRun = (Boolean) objIsAPPFirstRun;
        }
        while (runInfinity) {
            objIsAPPFirstRun = sharePrefHandler.getValue("app_is_first_run_online");
            if (objIsAPPFirstRun != null) {
                isAPPFirstRun = (Boolean) objIsAPPFirstRun;
            }
            if (isFirstRun && !isAPPFirstRun) {
                //onPostMethodOne();
                sharePrefHandler.printAllKeyValue();
            }
            onPostMethodOne();
            runInfinity = false;
        }
        onPrepareHTTPHashMapRequest();
        return START_STICKY;
    }

    @Override
    public void onStart(Intent argIntent, int agrStartid) {
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        LogWriter.Log("SERVICE_ON_START");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        LogWriter.Log("SERVICE_DESTROYED");
    }

    private void onPostMethodOne() {
        //sharePrefHandler = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        HashMap<String, String> urlHeaders = new HashMap<String, String>();
        HashMap<String, String> urlRequestParameters = new HashMap<String, String>();
        /*urlHeaders.put("head1", "headeValue1");
        urlHeaders.put("head2", "headeValue2");*/
        /*urlRequestParameters.put("auth_key_test", sharePrefHandler.getValue(SecureKeyManager.KeyAppAuthKey) + "");
        urlRequestParameters.put("package_name_test", APPStaticPackageInfo.getPackageName(context));
        urlRequestParameters.put("app_version_code_test", APPStaticPackageInfo.getVersionCode(context) + "");
        urlRequestParameters.put("app_version_name_test", APPStaticPackageInfo.getVersionName(context));*/
        urlRequestParameters.putAll(PrepareHTTPRequest.getURLPostParameters(context));
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
                    System.out.println("RETURNED_VALUE_DO_IN_BACK------: " + urlData);
                    // System.out.println(obj);
                    //onJSONParse(urlData);
                    // System.out.println(obj);
                }
                return argURLParams;
            }

            @Override
            public void onPostExecute(Object argResult) {
                //LogWriter.Log("onPostExecute: " + Arrays.toString(argResult) + "");
                if (argResult instanceof String[]) {
                    String[] strArray = (String[]) argResult;
                    System.out.println("RETURNED_VALUE********: " + Arrays.toString(strArray));
                    //System.out.println(obj);
                }
                Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                LogWriter.Log("onPostExecute------: " + format.format(new Date()));
                LogWriter.Log("onPostExecute------: " + argResult + "");
                sharePrefHandler.setValue("app_is_first_run_online", true);
                //runInfinity = false;
            }

            @Override
            public void onProgressUpdate(Integer... argProgressValue) {
            }

            @Override
            public void onCancelled() {
            }
        });
        powerFeedHTTPAsyncTask.setHTTPMethod(HTTPMethod.POST)
                .setUrlHeader(urlHeaders)
                .setURLParameters(urlRequestParameters)
                .onExecute(context, "http://jagoron24.com/app-tv-bangla-url.php");
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
            SharePrefPrivateHandler statPreferences = null;
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
            urlRequestParameters.put("app_request_time", staticFormat.format(new Date()));
            return urlRequestParameters;
        }
    }

    private void onPrepareHTTPHashMapRequest() {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, String> urlRequestParameters = new HashMap<>();
        String appAuthKey = "";
        String appPackageName = "";
        String appVersionCode = "";
        String appVersionName = "";

        String appPrimaryId = "";
        String appSecondaryId = "";
        String appGlobalIp = "";
        String appHardwareIp = "";
        String appNetLatitude = "";
        String appNetLongitude = "";
        String appNetCountry = "";

        appAuthKey = getStringValue(sharePrefHandler.getValue("app_auth_key"));
        appPackageName = getStringValue(sharePrefHandler.getValue("app_package_name"));
        appVersionCode = getStringValue(sharePrefHandler.getValue("app_version_code"));
        appVersionName = getStringValue(sharePrefHandler.getValue("app_version_name"));

        appPrimaryId = getStringValue(sharePrefHandler.getValue("device_primary_id"));
        appSecondaryId = getStringValue(sharePrefHandler.getValue("device_secondary_id"));
        appGlobalIp = getStringValue(sharePrefHandler.getValue("device_global_net_ip"));
        appHardwareIp = getStringValue(sharePrefHandler.getValue("device_hardware_ip"));

        appNetLatitude = getStringValue(sharePrefHandler.getValue("device_net_latitude"));
        appNetLongitude = getStringValue(sharePrefHandler.getValue("device_net_longitude"));
        appNetCountry = getStringValue(sharePrefHandler.getValue("device_net_country"));
        urlRequestParameters.put("auth_key", appAuthKey);
        urlRequestParameters.put("fcm_key_token", null);
        urlRequestParameters.put("bundle_identity", appPackageName);
        urlRequestParameters.put("version_code", appVersionCode);
        urlRequestParameters.put("version_name", appVersionName);

        urlRequestParameters.put("primary_id", appPrimaryId);
        urlRequestParameters.put("secondary_id", appSecondaryId);
        urlRequestParameters.put("global_ip", appGlobalIp);
        urlRequestParameters.put("hardware_ip", appHardwareIp);
        urlRequestParameters.put("net_latitude", appNetLatitude);
        urlRequestParameters.put("net_longitude", appNetLongitude);
        urlRequestParameters.put("net_country", appNetCountry);
        urlRequestParameters.put("request_time", format.format(new Date()));
        LogWriter.Log("HTTP_HASH_MAP_REQUEST: " + urlRequestParameters.toString());
        Iterator<Map.Entry<String, String>> iterator = urlRequestParameters.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
            if (iterator.hasNext()) {
                stringBuilder.append("&");
            }
        }
        LogWriter.Log("HTTP_HASH_MAP_REQUEST: " + stringBuilder.toString());
    }

    private String getStringValue(Object argObject) {
        if (argObject != null) {
            return (String) argObject.toString();
        }
        return null;
    }
}
/*
emon jaiga inshchoi kothao achhe
jekhane sobai manushera manusher moton

https://www.youtube.com/watch?v=JDf7DhzMOBs
ekjon pagol bollo eto vul he, vul he
tomra bolle ore pagol
pagol kokhono boleni pagol ke he
https://stackoverflow.com/questions/2809877/how-to-convert-map-to-url-query-string
                               A8:31:C1:5E:DC:30:53:D9:62:37:35:A5:52:13:4D:AC:BE:7A:D9:9F
MAP-KEY-VALUE: app_auth_key - A8:31:C1:5E:DC:30:53:D9:62:37:35:A5:52:13:4D:AC:BE:7A:D9:9F - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_package_name - com.rz.usagesexample - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_version_code - 201803001 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_version_name - 201803.00.1 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_primary_id - M4B30Z - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_secondary_id - 3794e4e8ffe7eede - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_global_net_ip - 43.224.119.52 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_hardware_ip - 192.168.0.19 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118

MAP-KEY-VALUE: device_net_latitude - 23.726 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_net_longitude - 90.4251 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: device_net_country - Bangladesh - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118

MAP-KEY-VALUE: app_initialization_date - 2018-03-20 15:41:20 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: private_data_date - 2018-03-20 15:41:21 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_first_run_date - 2018-03-20 16:50:02 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: security_entry_date - 2018-03-20 15:41:21 - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: is_private_data_force_update - false - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_is_first_run - true - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
MAP-KEY-VALUE: app_is_first_run_online - true - Class Name:- com.rz.librarycore.storage.SharePrefPrivateHandler - Method Name:- printAllKeyValue - Line Number:- 118
*/
/*
CREATE TABLE IF NOT EXISTS tbtmp_app_key_store
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    akstor_aukey_id                 BIGINT(20)        NOT NULL,
    akstor_aukey_sha1_key           TEXT              NOT NULL,
    akstor_aukey_status             BOOLEAN           NOT NULL,
    akstor_aukey_remarks            TEXT              NULL,
    akstor_aukey_create_date        DATETIME          NOT NULL,
    akstor_aukey_modify_date        DATETIME          NOT NULL,
    CONSTRAINT                      pk_appke_akstor_aukey_id PRIMARY KEY (akstor_aukey_id)
)

CREATE TABLE IF NOT EXISTS tbtmp_app_project
(
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    apjt_aproj_name                 VARCHAR(255)      NOT NULL,
    apjt_aproj_details              TEXT              NOT NULL,
    apjt_aproj_type                 VARCHAR(255)      NOT NULL,
    apjt_aproj_pkg_bundle           VARCHAR(255)      NOT NULL,
    apjt_aproj_latest_ver_code      VARCHAR(255)      NOT NULL,
    apjt_aproj_latest_ver_name      VARCHAR(255)      NOT NULL,
    apjt_aproj_lowest_valid_code    VARCHAR(255)      NOT NULL,
    apjt_aproj_lowest_valid_name    VARCHAR(255)      NOT NULL,
    apjt_aproj_status               BOOLEAN           NOT NULL,
    apjt_aproj_on_published         BOOLEAN           NOT NULL,
    apjt_aproj_create_date          DATETIME          NOT NULL,
    apjt_aproj_modify_date          DATETIME          NOT NULL,
    apjt_aproj_created_by           BIGINT(20)        NOT NULL,
    apjt_aproj_modified_by          BIGINT(20)        NOT NULL,
    CONSTRAINT                      pk_apppr_apjt_aproj_id PRIMARY KEY (apjt_aproj_id)
)
apjt_aproj_ver_code
apjt_aproj_ver_name
apjt_aproj_pkg_bundle
apjt_aproj_type
apjt_aproj_name
akstor_aukey_sha1_key

CREATE TABLE IF NOT EXISTS tbtmp_user_device_key
(
    user_prof_id                    BIGINT(20)        NULL,
    apjt_aproj_id                   BIGINT(20)        NOT NULL,
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivkey_udkey_fcm_token         TEXT              NULL,
    udivkey_udkey_build_id          VARCHAR(255)      NOT NULL,
    udivkey_udkey_android_id        VARCHAR(255)      NOT NULL,
    udivkey_udkey_uuid_id           VARCHAR(255)      NULL,
    udivkey_udkey_app_version       VARCHAR(255)      NOT NULL,
    udivkey_udkey_create_date       DATETIME          NOT NULL,
    udivkey_udkey_modify_date       DATETIME          NOT NULL,
    udivkey_udkey_created_by        BIGINT(20)        NULL,
    udivkey_udkey_modified_by       BIGINT(20)        NULL,
    CONSTRAINT                      pk_userd_udivkey_udkey_id PRIMARY KEY (udivkey_udkey_id)
)

user_prof_id
udivkey_udkey_fcm_token
udivkey_udkey_build_id
udivkey_udkey_android_id
udivkey_udkey_app_version
udivloc_loc_global_ip
udivloc_loc_hardware_ip
udivloc_loc_net_lat
udivloc_loc_net_lon
udivloc_loc_net_country
udivloc_loc_hardware_lat
udivloc_loc_hardware_lon
দোষটা ছিল আমারই, কেন সাঁতার দিয়ে পার হয়েছি নদী


CREATE TABLE IF NOT EXISTS tbtmp_user_device_location
(
    udivkey_udkey_id                BIGINT(20)        NOT NULL,
    udivloc_loc_id                  BIGINT(20)        NOT NULL,
    udivloc_loc_global_ip           TEXT              NOT NULL,
    udivloc_loc_hardware_ip         TEXT              NOT NULL,
    udivloc_loc_net_lat             TEXT              NOT NULL,
    udivloc_loc_net_lon             TEXT              NOT NULL,
    udivloc_loc_net_country         TEXT              NOT NULL,
    udivloc_loc_hardware_lat        TEXT              NULL,
    udivloc_loc_hardware_lon        TEXT              NULL,
    udivloc_loc_type                TEXT              NOT NULL,
    udivloc_loc_create_date         DATETIME          NOT NULL,
    CONSTRAINT                      pk_userd_udivloc_loc_id PRIMARY KEY (udivloc_loc_id)
)

*/