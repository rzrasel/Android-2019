package com.sm.solvelistviewprobmel;

import android.content.Context;

import com.library.AsyncHelper.AsyncBusiness;
import com.library.LogWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HTTPTestRequest {
    private Context context;
    private HTTPEventListenerHandler httpEventListenerHandler;
    private HashMap<String, String> httpParameter = new HashMap<String, String>();
    String httpRequestURL = "https://wbandapp.000webhostapp.com/get-data.php";

    public HTTPTestRequest(Context argContext) {
        this.context = argContext;
    }

    public void HTTPRequestExecute(HTTPEventListenerHandler argHTTPEventListenerHandler) {
        //|------------------------------------------------------------|
        httpParameter.clear();
        httpEventListenerHandler = argHTTPEventListenerHandler;
        //|------------------------------------------------------------|
        AsyncBusiness asyncBusiness = new AsyncBusiness();
        asyncBusiness.execute(context, httpRequestURL, httpParameter, new AsyncBusiness.ListenerHandler() {
            @Override
            public void onPreExecute() {
                //progressDialog = ProgressDialog.show(context, "", "Loading...", true);
            }

            @Override
            public void doInBackground(HashMap<String, String> argHttpData) {
            }

            @Override
            public void onPostExecute(HashMap<String, String> argResult) {
                //LogWriter.Log("RequestForGetShopLocation_HTTPRequestExecute");
                LogWriter.Log("http_response_code: " + argResult.get("response_code") + " - " + argResult.get("response_data"));
                //ArrayList<HashMap<String, String>> responseMappedData = new ArrayList<HashMap<String, String>>();
                //getJsonMappedData(argResult.get("response_data"));
                //progressDialog.dismiss();
                String jsonString = argResult.get("response_code");
                if (CheckJSON.isValidJSON(jsonString)) {
                    LogWriter.Log("JOSON IS VALID");
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        /*for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString(ModelListData.TagProperty.NAME.getValue());
                            String imageUrl = jsonObject.getString(ModelDataList.TagProperty.IMAGE_URL.getValue());
                            String videoUrl = jsonObject.getString(ModelDataList.TagProperty.VIDEO_URL.getValue());
                            LogWriter.Log("NAME: " + name + " VIDEO: " + videoUrl);
                            onSetOnlineListItems(name, imageUrl);
                        }
                        adapterListAdapter.notifyDataSetChanged();*/
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        LogWriter.Log("ERROR: " + e.getMessage());
                    }
                }
                if (httpEventListenerHandler != null) {
                    httpEventListenerHandler.HTTPExecute(argResult);
                }
            }
        });
        //|------------------------------------------------------------|
    }

    //|------------------------------------------------------------|
    public interface HTTPEventListenerHandler {
        public void HTTPExecute(HashMap<String, String> argHTTPResponseData);
    }
    //|------------------------------------------------------------|
}