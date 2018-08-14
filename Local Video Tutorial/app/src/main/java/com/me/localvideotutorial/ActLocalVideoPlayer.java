package com.me.localvideotutorial;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import java.io.File;

public class ActLocalVideoPlayer extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    private VideoView sysVideoView;
    private int videoPath = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_local_video_player);
        activity = this;
        context = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            videoPath = extras.getInt("tutorial_video_path");
        } else {
            finish();
        }
        sysVideoView = (VideoView) findViewById(R.id.sysVideoView);
        //Uri path = Uri.fromFile(new File("file:///android_asset/" + videoPath));
        //sysVideoView.setVideoPath(path.toString());
        String path = "android.resource://" + context.getPackageName() + "/" + videoPath;
        sysVideoView.setVideoURI(Uri.parse(path));
        sysVideoView.start();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);
    }
}