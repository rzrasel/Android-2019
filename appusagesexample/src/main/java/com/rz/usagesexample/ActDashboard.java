package com.rz.usagesexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ActDashboard extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private Toolbar sysToolBar;
    private DrawerLayout sysDrawerLayout;
    private RelativeLayout sysIdDrawerContainer;
    private ListView sysDrawerList;

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
        //sysToolBar.setTitleTextColor(Color.parseColor("#003a37"));
        setSupportActionBar(sysToolBar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        /*sysDrawerLayout = (DrawerLayout) findViewById(R.id.sysDrawerLayout);
        sysIdDrawerContainer = (RelativeLayout) findViewById(R.id.sysIdDrawerContainer);
        //|------------------------------------------------------------|
        LinearLayout idLinLayMainContainerView = (LinearLayout) findViewById(R.id.idLinLayMainContainerView);
        //new DrawerListViewSetUp().onSetModelItems().onSetAdapter();*/
        //|------------------------------------------------------------|
        new OnDrawerSetup(activity, context)
                .onSetToolbar(sysToolBar)
                .onSetDrawerMenuFields()
                .onSetDrawerMenuItems()
                .onSetDrawerAdapter(true, Gravity.LEFT);
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|

    }
}
