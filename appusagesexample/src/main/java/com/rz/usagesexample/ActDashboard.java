package com.rz.usagesexample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rz.varnishview.spinallistdrawer.SpinalRowDrawerDraw;
import com.rz.varnishview.spinallistdrawer.adapter.SharkArrayAdapter;
import com.rz.varnishview.spinallistdrawer.adapter.SharkModelRowScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ActDashboard extends AppCompatActivity {
    private Activity activity;
    private Context context;
    /*private Toolbar sysToolBar;
    private DrawerLayout sysDrawerLayout;
    private RelativeLayout sysIdDrawerContainer;
    private ListView sysDrawerList;*/
    private SpinalRowDrawerDraw spinalRowDrawerDraw;
    private Toolbar sysToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dashboard);
        activity = this;
        context = this;
        //|------------------------------------------------------------|
        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        //sysToolBar.setTitleTextColor(Color.parseColor("#003a37"));
        setSupportActionBar(sysToolBar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));*/
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

    @Override
    public boolean onOptionsItemSelected(MenuItem argMenuItem) {
        switch (argMenuItem.getItemId()) {
            case android.R.id.home:
                spinalRowDrawerDraw.onOptionsItemSelected(argMenuItem);
                return true;
            default:
                return super.onOptionsItemSelected(argMenuItem);
        }
        //System.out.println("----------------------");
        //return super.onOptionsItemSelected(argMenuItem);
    }

    //|------------------------------------------------------------|
    @Override
    public void onConfigurationChanged(Configuration argNewConfig) {
        super.onConfigurationChanged(argNewConfig);
        spinalRowDrawerDraw.onConfigurationChanged(argNewConfig);
    }
    //|------------------------------------------------------------|

    @Override
    public void onBackPressed() {
        if (!spinalRowDrawerDraw.onSetBackPressed()) {
            super.onBackPressed();
        }
    }

    //|------------------------------------------------------------|
    public class OnDrawerSetup {
        private Activity activity;
        private Context context;
        private ArrayList<SharkModelRowScope> modelDrawerListItems = new ArrayList<SharkModelRowScope>();

        public OnDrawerSetup(Activity argActivity, Context argContext) {
            this.activity = argActivity;
            this.context = argContext;
            spinalRowDrawerDraw = new SpinalRowDrawerDraw(activity, context);
        }

        public OnDrawerSetup onSetToolbar(Toolbar argToolbar) {
            spinalRowDrawerDraw.spinalToolBar.onHideActionBar()
                    .initToolBar(argToolbar)
                    .onSetActionBar()
                    .onSetTitleText("Dashboard")
                    .onSetTitleTextColor("#E0C0F9")
                    //.onSetSubTitleText("Sub Title Spinal Row Drawer")
                    //.onSetSubTitleTextColor("#ffffff")
                    //.onSetTitleFont("fonts/encode_sans_extrabold.ttf")
                    //.onSetSubTitleFont("fonts/alex-brush-regular.ttf")
                    .onShowHomeButton()
                    .onSetStatusBarDark(true);
                    /*sysToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View argView) {
                            System.out.println("DEBUG_ID: ");
                        }
                    });*/
            return this;
        }

        public OnDrawerSetup onSetDrawerMenuFields() {
            //ArrayList<SharkModelRowViewFields> rowViewFieldListItems = new ArrayList<SharkModelRowViewFields>();
            spinalRowDrawerDraw.spinalDrawerMenu.onSetRowViewField(SpinalRowDrawerDraw.FIELD_TYPE.IMAGE_VIEW, "sysImgViewDrawerIcon");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetRowViewField(SpinalRowDrawerDraw.FIELD_TYPE.TEXT_VIEW, "sysDrawerTitle");
            return this;
        }

        public OnDrawerSetup onSetDrawerMenuItems() {
            HashMap<String, String> eachRowDataItems = null;
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.mipmap.ic_launcher + "");
            eachRowDataItems.put("sysDrawerTitle", "Dashboard");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragTest.class);
            //|------------------
            /*eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_profile_male + "");
            eachRowDataItems.put("sysDrawerTitle", "Profile");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_settings + "");
            //https://www.flaticon.com/free-icon/settings_295968#term=settings&page=1&position=65
            //https://www.flaticon.com/search?word=profile
            eachRowDataItems.put("sysDrawerTitle", "Settings");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragUserRegistration.class);
            modelDrawerListItems = spinalRowDrawerDraw.spinalDrawerMenu.onGetDataList();
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_report + "");
            eachRowDataItems.put("sysDrawerTitle", "Report");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);
            //|------------------
            eachRowDataItems = new HashMap();
            eachRowDataItems.put("sysImgViewDrawerIcon", R.drawable.img_menu_rules + "");
            eachRowDataItems.put("sysDrawerTitle", "Rules");
            spinalRowDrawerDraw.spinalDrawerMenu.onSetItemData(eachRowDataItems, FragDashboard.class);*/
            //|------------------
            return this;
        }

        public OnDrawerSetup onSetDrawerAdapter(boolean argIsPushActivity, int argGravity) {
            SharkArrayAdapter adapterDrawerListAdapter;
            ListView sysDrawerList = (ListView) findViewById(R.id.sysDrawerList);
            adapterDrawerListAdapter = new SharkArrayAdapter(context, R.layout.lay_spinal_drawer_row, modelDrawerListItems);
            spinalRowDrawerDraw.spinalDrawerMenu.onSetAdapterListener(adapterDrawerListAdapter)
                    .onSetDrawerLayout((DrawerLayout) findViewById(R.id.sysDrawerLayout), (RelativeLayout) findViewById(R.id.sysIdDrawerContainer), R.id.sysFrameContainer)
                    .onSetDefaultDrawerLayout(0);
            sysDrawerList.setAdapter(adapterDrawerListAdapter);
            spinalRowDrawerDraw.spinalDrawerMenu.onSetDrawerItemClickListener(sysDrawerList);
            if (argIsPushActivity) {
                spinalRowDrawerDraw.onSetFrameLayoutParent((LinearLayout) findViewById(R.id.idLinLayMainContainerView))
                        .onSetGravity(argGravity)
                        .onConfigureDrawer();
            } else {
                spinalRowDrawerDraw.onSetGravity(argGravity)
                        .onConfigureDrawer();
            }
            return this;
        }
    }

    //|------------------------------------------------------------|
}
