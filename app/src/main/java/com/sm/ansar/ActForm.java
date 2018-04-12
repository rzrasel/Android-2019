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
import android.widget.TextView;

public class ActForm extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private Toolbar sysToolBar;
    private ViewPager viewPager;
    private SeekBar seekBar;
    private TextView sysTvNumOfPages;
    private int totalPages = 4;
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
        sysTvNumOfPages = (TextView) findViewById(R.id.sysTvNumOfPages);
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
        onSetNumberOfPage();
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int argPosition, float argPositionOffset, int argPositionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int argPosition) {
                currentPagePosition = argPosition;
                seekBar.setProgress(currentPagePosition);
                onSetNumberOfPage();
            }

            @Override
            public void onPageScrollStateChanged(int argState) {
            }
        });
        //|------------------------------------------------------------|
    }
    //|------------------------------------------------------------|

    public class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar argSeekBar, int argProgress, boolean argFromUser) {
            currentPagePosition = argProgress;
            viewPager.setCurrentItem(currentPagePosition, true);
            onSetNumberOfPage();
        }
    }

    //|------------------------------------------------------------|
    private void onSetNumberOfPage() {
        sysTvNumOfPages.setText((currentPagePosition + 1) + "/" + totalPages);
    }
    //|------------------------------------------------------------|
}
