package com.sm.solvelistviewprobmel;

import android.content.Context;

import com.library.AsyncHelper.AsyncBusiness;
import com.library.LogWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HTTPTestRequest {
    private Context context;
    private HTTPEventListenerHandler httpEventListenerHandler;
    private HashMap<String, String> httpParameter = new HashMap<String, String>();
    //String httpRequestURL = "https://wbandapp.000webhostapp.com/get-data.php";
    String httpRequestURL = "http://eboardresults.com/app/stud/";

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
                //ArrayList<HashMap<String, String>> responseMappedData = new ArrayList<HashMap<String, String>>();
                //getJsonMappedData(argResult.get("response_data"));
                //progressDialog.dismiss();
                String jsonString = argResult.get("response_data");
                LogWriter.Log("http_response_code: " + argResult.get("response_code") + " - " + jsonString);
                ArrayList<ModelListData> dataModelItems = new ArrayList<>();
                /*if (CheckJSON.isValidJSON(jsonString)) {
                    LogWriter.Log("JOSN IS VALID");
                    try {
                        JSONArray jsonArray = new JSONArray(jsonString);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString(ModelListData.JSONTag.NAME.getValue());
                            LogWriter.Log("JSON_NAME: " + name);
                            dataModelItems.add(ModelListData.onGetSetRow(name));
                        }
                        //adapterListAdapter.notifyDataSetChanged();
                        if (httpEventListenerHandler != null) {
                            httpEventListenerHandler.HTTPExecute(dataModelItems);
                        }
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        LogWriter.Log("JSON_ERROR: " + e.getMessage());
                    }
                }*/
                //https://www.youtube.com/watch?v=ioRXNTuiZIo
                //https://www.youtube.com/watch?v=q3KqBThsKOk
                //https://www.youtube.com/watch?v=UDZyRDK50ck
                //https://www.youtube.com/watch?v=ntorJi-36ds
                //https://www.youtube.com/watch?v=WfmprIQ74rM
                //https://www.youtube.com/watch?v=Nmsq9Vo65fk
                //https://www.youtube.com/watch?v=JNlIZhipUbY
                //https://www.youtube.com/watch?v=b1_p2qQf_Ts
                //https://www.youtube.com/watch?v=J1bve2jKRjk
                //https://www.youtube.com/watch?v=H1ZsiQy764Q
                //https://www.youtube.com/watch?v=MTEhhkpkTQo
                //https://www.youtube.com/watch?v=aSt2ytqCN6c
                //https://www.youtube.com/watch?v=Ya7OE9av8Fs
                //https://www.youtube.com/watch?v=Y21ZRfz3IfY
                //https://www.youtube.com/watch?v=koUEv1anTb8
                //https://www.youtube.com/watch?v=W28lB__r6x4
                dataModelItems.add(ModelListData.onGetSetRow("Goodie Giveaway 2 - Magic Flip Script", "ioRXNTuiZIo"));
                dataModelItems.add(ModelListData.onGetSetRow("Time stretching After Effects tutorial - Stretch Layers & Compositions", "q3KqBThsKOk"));
                dataModelItems.add(ModelListData.onGetSetRow("Animating Cogs With The Time Expression - After Effects Tutorial", "UDZyRDK50ck"));
                dataModelItems.add(ModelListData.onGetSetRow("Top 10 Outro Template No Text + Free Download + No Copyright", "ntorJi-36ds"));
                dataModelItems.add(ModelListData.onGetSetRow("After effects expressions tutorial", "WfmprIQ74rM"));
                dataModelItems.add(ModelListData.onGetSetRow("Ferris Wheel Style Layer Rotation - After Effects", "Nmsq9Vo65fk"));
                dataModelItems.add(ModelListData.onGetSetRow("Circle Align Demo", "JNlIZhipUbY"));
                dataModelItems.add(ModelListData.onGetSetRow("Text Animators in After Effects", "b1_p2qQf_Ts"));
                dataModelItems.add(ModelListData.onGetSetRow("Working with Codecs in Motion Graphics", "J1bve2jKRjk"));
                dataModelItems.add(ModelListData.onGetSetRow("How to Use Master Properties in After Effects", "H1ZsiQy764Q"));
                dataModelItems.add(ModelListData.onGetSetRow("How to swing a tennis ball in cricket cricket tricks", "MTEhhkpkTQo"));
                dataModelItems.add(ModelListData.onGetSetRow("How To Swing A Tennis Ball In Cricket Tricks and Tips", "aSt2ytqCN6c"));
                dataModelItems.add(ModelListData.onGetSetRow("How to bowl googly | how to bowl wrong'un | Bowling Technique | Cricket |", "Ya7OE9av8Fs"));
                dataModelItems.add(ModelListData.onGetSetRow("How to do Carrom Ball | Bowling Technique | Cricket", "Y21ZRfz3IfY"));
                dataModelItems.add(ModelListData.onGetSetRow("Best Fast Bowling || Cricket Bats Broken By Fast Bowlers ✔ ✔", "koUEv1anTb8"));
                dataModelItems.add(ModelListData.onGetSetRow("#17 Funny Fielding Moments in Cricket - Try Not to Laugh Challenge!", "W28lB__r6x4"));
                //https://www.youtube.com/watch?v=gs2eZl9bBCw
                //https://www.youtube.com/watch?v=tSIiJ2HnFSY
                dataModelItems.add(ModelListData.onGetSetRow("Top 10 HUGE SWING Balls EVER BOWLED!!! in cricket (Amazing) || The Cricket Fella", "gs2eZl9bBCw"));
                dataModelItems.add(ModelListData.onGetSetRow("Comedy Nights With Kapil - Wasim Akram - 1st November 2014 - Full Episode (HD)", "tSIiJ2HnFSY"));
                dataModelItems.add(ModelListData.onGetSetRow("name", "video_id"));
                if (httpEventListenerHandler != null) {
                    httpEventListenerHandler.HTTPExecute(dataModelItems);
                }
            }
        });
        //|------------------------------------------------------------|
    }

    //|------------------------------------------------------------|
    public interface HTTPEventListenerHandler {
        //public void HTTPExecute(HashMap<String, String> argHTTPResponseData);
        //public void HTTPExecute(ArrayList<?> argDataModelItems);
        public void HTTPExecute(Object argDataModelItems);
    }
    //|------------------------------------------------------------|
}