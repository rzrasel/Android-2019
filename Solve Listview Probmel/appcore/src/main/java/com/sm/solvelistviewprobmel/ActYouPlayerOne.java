package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class ActYouPlayerOne extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    private CastYouTubePlayerSupportFragment castYouTubePlayerSupportFragment;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_you_player_one);
        activity = this;
        context = this;
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);
        castYouTubePlayerSupportFragment = CastYouTubePlayerSupportFragment.newInstance("ePz-YEPXl6Y");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.sysFragmentYoutubePlayer, castYouTubePlayerSupportFragment, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent argMotionEvent) {
        System.out.println("argMotionEvent");
        return super.onTouchEvent(argMotionEvent);
    }

    @Override
    public void onBackPressed() {
        System.out.println("COUNT_BACK: " + getFragmentManager().getBackStackEntryCount());
        /*if (getFragmentManager().getBackStackEntryCount() > -1) {
            getFragmentManager().popBackStack();
            activity.finish();
        } else {
            super.onBackPressed();
        }*/
        activity.finish();
        return;
    }
}
