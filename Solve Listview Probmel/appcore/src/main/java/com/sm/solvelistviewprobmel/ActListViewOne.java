package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.library.LogWriter;

import java.util.ArrayList;

public class ActListViewOne extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private AdapterListView adapterListView;
    private ArrayList<ModelListData> dataModelItems;
    private ListView sysDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_view_one);
        activity = this;
        context = this;
        sysDrawerList = (ListView) findViewById(R.id.sysDrawerList);
        dataModelItems = new ArrayList<>();
        dataModelItems.add(ModelListData.onGetSetRow("List Row 01"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 02"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 03"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 04"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 05"));

        dataModelItems.add(ModelListData.onGetSetRow("List Row 06"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 07"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 08"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 09"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 10"));

        dataModelItems.add(ModelListData.onGetSetRow("List Row 11"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 12"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 13"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 14"));
        dataModelItems.add(ModelListData.onGetSetRow("List Row 15"));
        adapterListView = new AdapterListView(context, 0, dataModelItems);
        sysDrawerList.setAdapter(adapterListView);
        new HTTPTestRequest(context).HTTPRequestExecute(new HTTPTestRequest.HTTPEventListenerHandler() {
            @Override
            public void HTTPExecute(Object argDataModelItems) {
                LogWriter.Log("PRINT_HTTPTestRequest_ActListViewOne: " + argDataModelItems.toString());
                //if (argDataModelItems instanceof ArrayList<?>) {
                if (argDataModelItems instanceof ArrayList<?>) {
                    dataModelItems.clear();
                    dataModelItems.addAll((ArrayList<ModelListData>) argDataModelItems);
                    adapterListView.notifyDataSetChanged();
                    LogWriter.Log("PRINT_TEST_ActListViewOne: " + dataModelItems.toString());
                }
                /*dataModelItems.clear();
                dataModelItems.addAll((ArrayList<ModelListData>) argDataModelItems);
                adapterListView.notifyDataSetChanged();
                LogWriter.Log("PRINT_TEST_ActListViewOne: " + dataModelItems.toString());*/
            }
        });
        sysDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> argAdapterView, View argView, int argPosition, long argId) {
                System.out.println("POSITION: " + argPosition);
                System.out.println("VALUE: " + dataModelItems.get(argPosition).getTitle());
                String videoId = dataModelItems.get(argPosition).getVideoId();
                Intent intentRedirectWindow = new Intent(context, ActYouPlayer.class);
                Bundle bundle = new Bundle();
                bundle.putString("video_id", videoId);
                intentRedirectWindow.putExtras(bundle);
                startActivity(intentRedirectWindow);
            }
        });
    }
}
