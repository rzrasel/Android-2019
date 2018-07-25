package com.sm.solvelistviewprobmel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class ActYouPlayer extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private static final String YOUTUBE_API_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    private RelativeLayout sysRelayMainContainer;
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
    private SensorManager sensorManager;
    private Sensor sensor;
    private Display display;
    private PowerManager powerManager;
    private WindowManager windowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        // Get an instance of the PowerManager
        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        // Get an instance of the WindowManager
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        /*if (Build.VERSION.SDK_INT < 16) {
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
        }*/
        setContentView(R.layout.act_you_player);
        activity = this;
        context = this;
        sysRelayMainContainer = (RelativeLayout) findViewById(R.id.sysRelayMainContainer);
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
                    youTubePlayer = argYouTubePlayer;
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
        /*View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        //
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);*/
        //sysRelayMainContainer.setRotation(180);
        youTubePlayerSupportFragment.getView().setRotation(90);
    }

    @Override
    public boolean onTouchEvent(MotionEvent argEvent) {
        System.out.println("SENSOR_ANGLE_onTouchEvent: ");
        return super.onTouchEvent(argEvent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent argSensorEvent) {
            if (argSensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
                return;
            float sensorX = 0;
            float sensorY = 0;
            float angle;
            switch (display.getRotation()) {
                case Surface.ROTATION_0:
                    sensorX = argSensorEvent.values[0];
                    sensorY = argSensorEvent.values[1];
                    //sysRelayMainContainer.setRotation(270);
                    break;
                case Surface.ROTATION_90:
                    sensorX = -argSensorEvent.values[1];
                    sensorY = argSensorEvent.values[0];
                    //sysRelayMainContainer.setRotation(90);
                    break;
                case Surface.ROTATION_180:
                    sensorX = -argSensorEvent.values[0];
                    sensorY = -argSensorEvent.values[1];
                    break;
                case Surface.ROTATION_270:
                    sensorX = argSensorEvent.values[1];
                    sensorY = -argSensorEvent.values[0];
                    //sysRelayMainContainer.setRotation(270);
                    break;
            }
            /*sensorX = argSensorEvent.values[1];
            sensorY = -argSensorEvent.values[0];*/
            /*angle = Float.parseFloat(Math.atan2(sensorX, sensorY) / (Math.PI / 180) + "");
            System.out.println("SENSOR_ANGLE: " + angle);*/
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /*public class ImpSensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent argSensorEvent) {
        }

        @Override
        public void onAccuracyChanged(Sensor argSensor, int argAccuracy) {
        }
    }*/

    @Override
    public void onConfigurationChanged(Configuration argNewConfig) {
        super.onConfigurationChanged(argNewConfig);
        if (argNewConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("ORIENTATION_LANDSCAPE");

        } else if (argNewConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            System.out.println("ORIENTATION_PORTRAIT");
        }
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
//https://stackoverflow.com/questions/3663665/how-can-i-get-the-current-screen-orientation

/*
https://android.okhelp.cz/onsensorchanged-android-example/
https://www.ssaurel.com/blog/get-android-device-rotation-angles-with-accelerometer-and-geomagnetic-sensors/
https://stackoverflow.com/questions/12080170/get-android-rotation-angle-in-x-axis
https://stackoverflow.com/questions/20813386/onsensorchangedsensorevent-event-always-show-the-same-result

https://www.eecs.yorku.ca/course_archive/2017-18/W/4443/Javadoc/index.html?ca/yorku/eecs/mack/demodisplay/DemoDisplayActivity.html

https://www.eecs.yorku.ca/course_archive/2017-18/W/4443/Javadoc/index.html?ca/yorku/eecs/mack/demodisplay/DemoDisplayActivity.html

https://stackoverflow.com/questions/48240996/youtubeplayer-how-to-handle-click-listener
*/