package com.sm.ansar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActDashboard extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private Toolbar sysToolBar;
    private AdapterDashboard adapterDashboard;
    private ArrayList<ModelDashboard> modelDashboardItems = new ArrayList<ModelDashboard>();
    private GridView sysGridDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dashboard);
        activity = this;
        context = this;
        //|------------------------------------------------------------|
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        sysToolBar.setTitleTextColor(Color.parseColor("#003a37"));
        setSupportActionBar(sysToolBar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        modelDashboardItems.add(ModelDashboard.onGetSetModelRow(R.drawable.new_entry_icon, "Add New Entry", 10));
        modelDashboardItems.add(ModelDashboard.onGetSetModelRow(R.drawable.list_view_icon, "List View", 3));
        modelDashboardItems.add(ModelDashboard.onGetSetModelRow(R.drawable.save_icon, "Save", 5));
        modelDashboardItems.add(ModelDashboard.onGetSetModelRow(R.drawable.submit_icon, "Submit", 6));
        sysGridDashboard = (GridView) findViewById(R.id.sysGridDashboard);
        adapterDashboard = new AdapterDashboard(context, 0, modelDashboardItems);
        sysGridDashboard.setAdapter(adapterDashboard);
        //|------------------------------------------------------------|
        sysGridDashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> argAdapterView, View argView, int argPosition, long argId) {
                //Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ActForm.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        //|------------------------------------------------------------|
    }
}
//https://medium.com/@andkulikov/animate-all-the-things-transitions-in-android-914af5477d50