package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rz.librarycore.http.HTTPMethod;
import com.rz.librarycore.http.OnFeedHTTPEventListenerHandler;
import com.rz.librarycore.http.PowerFeedHTTPAsyncTask;
import com.rz.librarycore.log.LogWriter;
import com.rz.wareadapter.SparkedArrayAdapter;
import com.rz.wareadapter.SparkedModelRowScope;
import com.rz.wareadapter.SparkedModelRowViewFields;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class ActDataList extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ListView sysLstView;
    private SparkedArrayAdapter adapterListAdapter;
    private ArrayList<SparkedModelRowViewFields> rowViewFieldListItems = new ArrayList<SparkedModelRowViewFields>();
    private ArrayList<SparkedModelRowScope> modelListDataItems = new ArrayList<SparkedModelRowScope>();
    private String dataURL = "http://rzrasel.net/hmvc-temp/api-app-video/gopal-bhar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_data_list);
        activity = this;
        context = this;
        sysLstView = (ListView) findViewById(R.id.sysLstView);

        onSetListItems();
        adapterListAdapter = new SparkedArrayAdapter(context, R.layout.layout_list_row, modelListDataItems);
        //rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle"));
        //rowViewFieldListItems.add(adapterListAdapter.onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerDescription"));
        //adapterListAdapter.onSetRowViewFieldList(rowViewFieldListItems);
        onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle");
        onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW, "sysDrawerDescription");
        onSetAdapter();
        sysLstView.setAdapter(adapterListAdapter);

        PowerFeedHTTPAsyncTask powerFeedHTTPAsyncTask = new PowerFeedHTTPAsyncTask(new OnHTTPEventListenerHandler());
        powerFeedHTTPAsyncTask.setHTTPMethod(HTTPMethod.POST)
                .onExecute(context, dataURL);
    }

    public void onSetListItems() {
        HashMap<String, String> eachRowDataItems = null;
        eachRowDataItems = new HashMap();
        eachRowDataItems.put("sysDrawerTitle", "Title-01");
        eachRowDataItems.put("sysDrawerDescription", "Description-01");
        //spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FirstFragment.class);
        modelListDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.NONE));
        eachRowDataItems = new HashMap();
        eachRowDataItems.put("sysDrawerTitle", "Title-02");
        eachRowDataItems.put("sysDrawerDescription", "Description-02");
        //spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, SecondFragment.class);
        modelListDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.URL));
        //modelDrawerListItems = spinalRowDrawerDraw.spinalDrawerMenu.onGetDataList();
    }

    private void onSetAdapter() {
        adapterListAdapter.onSetRowViewFieldList(rowViewFieldListItems)
                .onSetRowViewFieldListenerHandler(new SparkedArrayAdapter.OnFieldListenerHandler() {
                    @Override
                    public void onSetFieldValue(ArrayList<SparkedModelRowViewFields> argRowViewFieldList, Object argObject) {
                        SparkedModelRowScope itemScope = (SparkedModelRowScope) argObject;
                        HashMap<String, String> hashMapRowIdValueItem = itemScope.getHashMapRowIdValueItems();
                        for (SparkedModelRowViewFields itemField : argRowViewFieldList) {
                            Object object = itemField.getFieldObject();
                            String fieldResourceId = itemField.getFieldResourceId();
                            if (object instanceof TextView) {
                                TextView rowField = null;
                                rowField = (TextView) itemField.getFieldObject();
                                if (hashMapRowIdValueItem.containsKey(fieldResourceId)) {
                                    rowField.setText(hashMapRowIdValueItem.get(fieldResourceId));
                                }
                                System.out.println(itemField.getFieldResourceId());
                            }
                            System.out.println("------" + fieldResourceId);
                        }
                    }
                });
    }

    private void onSetRowViewField(SparkedArrayAdapter.FIELD_TYPE argFieldType, String argFieldResourceId) {
        if (argFieldType == SparkedArrayAdapter.FIELD_TYPE.TEXT_VIEW) {
            TextView textView = new TextView(context);
            rowViewFieldListItems.add(SparkedModelRowViewFields.onGetSetModelRow(textView, argFieldResourceId));
        } else if (argFieldType == SparkedArrayAdapter.FIELD_TYPE.IMAGE_VIEW) {
            ImageView imageView = new ImageView(context);
            rowViewFieldListItems.add(SparkedModelRowViewFields.onGetSetModelRow(imageView, argFieldResourceId));
        }
    }

    public class OnHTTPEventListenerHandler implements OnFeedHTTPEventListenerHandler {
        @Override
        public void onPreExecute() {
            //
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
                String result = strArray[0];
                /*System.out.println("RETURNED_VALUE********: " + Arrays.toString(strArray));
                System.out.println("RETURNED_VALUE********: " + strArray[0]);*/
                //System.out.println(obj);
                try {
                    //JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = new JSONArray(result);
                    LogWriter.Log("LENGTH_JSON: " + jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String jsonTitle = jsonObject.getString("title");
                        String jsonDetails = jsonObject.getString("details");
                        LogWriter.Log("TITLE_JSON: " + jsonTitle);
                        LogWriter.Log("DETAILS_JSON: " + jsonDetails);
                        LogWriter.Log("EPISOD_JSON: " + jsonObject.getString("episod"));
                        LogWriter.Log("VIDEO_ID_JSON: " + jsonObject.getString("video_id"));
                        LogWriter.Log("IMAGE_URL_JSON: " + jsonObject.getString("image_url"));
                        onSetOnlineListItems(jsonTitle, jsonDetails);
                    }
                    adapterListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    //e.printStackTrace();
                    LogWriter.Log("ERROR_JSON: " + e.getMessage());
                }
            }
            /*Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LogWriter.Log("onPostExecute------: " + format.format(new Date()));
            LogWriter.Log("onPostExecute------: " + argResult + "");*/
            //runInfinity = false;
        }

        @Override
        public void onProgressUpdate(Integer... argProgressValue) {
        }

        @Override
        public void onCancelled() {
        }
    }

    public void onSetOnlineListItems(String argTitle, String argDetails) {
        HashMap<String, String> eachRowDataItems = null;
        eachRowDataItems = new HashMap();
        eachRowDataItems.put("sysDrawerTitle", argTitle);
        eachRowDataItems.put("sysDrawerDescription", argDetails);
        //spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FirstFragment.class);
        modelListDataItems.add(SparkedModelRowScope.onGetSetRow(eachRowDataItems, (String) null, SparkedModelRowScope.LISTENER_TYPE.NONE));
    }
}
