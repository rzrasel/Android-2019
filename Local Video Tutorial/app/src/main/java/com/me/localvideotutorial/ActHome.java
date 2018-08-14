package com.me.localvideotutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ActHome extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private AdapterHomeList adapterHomeList;
    private ArrayList<ModelTutorialItem> modelTutorialItems = new ArrayList<ModelTutorialItem>();
    private ListView sysTutorialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        activity = this;
        context = this;
        ModelTutorialItem item = null;


        item = new ModelTutorialItem("1.1 Overseas employment travel documents", R.raw.overseas_employment_travel_documents_1_1);
        modelTutorialItems.add(item);
        item = null;
        item = new ModelTutorialItem("1.2 International Labour Acts", R.raw.international_labour_acts_1_2);
        modelTutorialItems.add(item);
        item = null;
        item = new ModelTutorialItem("1.3 OSH (Occupational Safety and Health)", R.raw.osh_occupational_safety_and_health_1_3);
        modelTutorialItems.add(item);
        item = null;
        item = new ModelTutorialItem("2.1 Food Habir and Social Status", R.raw.food_habir_and_social_status_2_1);
        modelTutorialItems.add(item);
        item = null;
        item = new ModelTutorialItem("2.2 Language and Traffic System", R.raw.language_and_traffic_system_2_2);
        modelTutorialItems.add(item);
        item = null;
        sysTutorialList = (ListView) findViewById(R.id.sysTutorialList);
        adapterHomeList = new AdapterHomeList(context, 0, modelTutorialItems);
        sysTutorialList.setAdapter(adapterHomeList);
        sysTutorialList.setOnItemClickListener(new OnListItemClickListener());
    }

    private class OnListItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> argParent, View view, int argPosition, long argId) {
            Intent intent = new Intent(getBaseContext(), ActLocalVideoPlayer.class);
            intent.putExtra("tutorial_video_path", modelTutorialItems.get(argPosition).getTutorialPath());
            startActivity(intent);
        }
    }
}
