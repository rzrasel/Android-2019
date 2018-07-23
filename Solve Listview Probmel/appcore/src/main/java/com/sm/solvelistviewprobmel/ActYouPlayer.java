package com.sm.solvelistviewprobmel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class ActYouPlayer extends AppCompatActivity {
    private Activity activity;
    private Context context;
    public static final String YOUTUBE_API_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private String videoId;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.hide();
            }
        }
        setContentView(R.layout.act_you_player);
        activity = this;
        context = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            videoId = bundle.getString("video_id");
            System.out.println("VIDEO_ID: " + videoId);
        } else {
            activity.finish();
        }
        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.sysFragmentYoutubePlayer, youTubePlayerSupportFragment, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
        youTubePlayerSupportFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
                if (!argWasRestored) {
                    argYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    argYouTubePlayer.loadVideo(videoId);
                    argYouTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider argProvider, YouTubeInitializationResult argYouTubeInitializationResult) {
            }
        });
        /*youTubePlayerView = (YouTubePlayerView) findViewById(R.id.sysYoutubePlayerView);
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
                if (!argWasRestored) {
                    argYouTubePlayer.cueVideo(videoId);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider argProvider, YouTubeInitializationResult argYouTubeInitializationResult) {
            }
        });*/
        //
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        //decorView.setSystemUiVisibility(uiOptions);
        //
        //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);
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
//https://stackoverflow.com/questions/4936553/android-how-can-you-align-a-button-at-the-bottom-and-listview-above/4936732
//http://stacktips.com/tutorials/android/youtubeplayerview-example-in-android-using-youtube-api