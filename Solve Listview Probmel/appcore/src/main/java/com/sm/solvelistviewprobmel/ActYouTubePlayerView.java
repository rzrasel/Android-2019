package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ActYouTubePlayerView extends YouTubeBaseActivity implements GestureDetector.OnGestureListener {
    private Activity activity;
    private Context context;
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

    private YouTubePlayerView sysYouTubePlayerView;
    private static final String YOUTUBE_API_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    private GestureDetector mDetector;
    private GestureDetector detector;
    private TextView idTvControlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        detector = new GestureDetector(this);
        setContentView(R.layout.act_you_tube_player_view);
        activity = this;
        context = this;
        idTvControlView = (TextView) findViewById(R.id.idTvControlView);
        /*getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
        //activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);
        sysYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.sysYouTubePlayerView);
        sysYouTubePlayerView.initialize(YOUTUBE_API_KEY, onInitializedListener);
        mDetector = new GestureDetector(context, new MyGestureListener());
        sysYouTubePlayerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View argView, MotionEvent argMotionEvent) {
                //System.out.println("setOnTouchListener_argMotionEvent");
                return mDetector.onTouchEvent(argMotionEvent);
            }
        });
    }

    private YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
            if (!argWasRestored) {

                // loadVideo() will auto play video
                // Use cueVideo() method, if you don't want to play it automatically
                argYouTubePlayer.setShowFullscreenButton(true);
                argYouTubePlayer.loadVideo("ePz-YEPXl6Y");
                // Hiding player controls
                argYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                argYouTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String s) {
                        sysYouTubePlayerView.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View argView, MotionEvent argMotionEvent) {
                                //System.out.println("setOnTouchListener_argMotionEvent");
                                return mDetector.onTouchEvent(argMotionEvent);
                            }
                        });
                    }

                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onVideoStarted() {
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {
                    }
                });
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider argProvider, YouTubeInitializationResult argYouTubeInitializationResult) {
        }
    };

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //https://stackoverflow.com/questions/45054908/how-to-add-a-gesture-detector-to-a-view-in-android
        @Override
        public boolean onDown(MotionEvent argMotionEvent) {
            Log.d("TAG", "onDown: ");
            idTvControlView.setVisibility(View.VISIBLE);
            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent argMotionEvent) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent argMotionEvent) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent argMotionEvent) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent argMotionEvent) {
            Log.i("TAG", "onSingleTapUp: ");
            idTvControlView.setVisibility(View.GONE);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent argMotionEvent1, MotionEvent argMotionEvent2, float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            Log.d("TAG", "onFling: ");
            return true;
        }
    }

    public void onClick(View view) {
        System.out.println("onClick");
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent argMotionEvent) {
        System.out.println("argMotionEvent");
        return super.onTouchEvent(argMotionEvent);
    }*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "OnDown Gesture", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Toast.makeText(getApplicationContext(), "Fling Gesture", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "Long Press Gesture", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Toast.makeText(getApplicationContext(), "Scroll Gesture", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "Show Press gesture", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(getApplicationContext(), "Single Tap Gesture", Toast.LENGTH_LONG).show();
        return true;
    }
}
//https://www.androidhive.info/2014/12/how-to-play-youtube-video-in-android-app/
//https://stackoverflow.com/questions/17031817/swipe-gesture-are-not-working-in-youtubeplayerview-in-full-screen-mode
//https://readyandroid.wordpress.com/full-screen-preview-imagevideoyoutube-video-android-sample/
//Swipe Gesture don't work on youtubePlayerview in android