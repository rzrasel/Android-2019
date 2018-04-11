package com.sm.ansar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;

public class ActForm extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private Toolbar sysToolBar;
    private ViewPager viewPager;
    private SeekBar seekBar;
    private int totalPages = 6;
    private int currentPagePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_form);
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
        this.viewPager = (ViewPager) findViewById(R.id.resourceViewPager);
        this.viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.card_fill_form_view_pager_page_margin));
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(totalPages - 1);
        seekBar.setProgress(currentPagePosition);
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
        FragmentManager fragmentManager = getSupportFragmentManager();
        final AdapterViewPager adapter = new AdapterViewPager(fragmentManager, totalPages);
        this.viewPager.setAdapter(adapter);
        this.viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(currentPagePosition);
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                seekBar.setProgress(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //|------------------------------------------------------------|
    }

    public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            viewPager.setCurrentItem(progress, true);
        }
    }
}
