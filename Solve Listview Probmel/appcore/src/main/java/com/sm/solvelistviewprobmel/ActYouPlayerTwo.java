package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class ActYouPlayerTwo extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
    private CastYouTubePlayerSupportFragment castYouTubePlayerSupportFragment;
    //private StrawYouTubePlayerFragment strawYouTubePlayerFragment;
    private final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    private String DEVELOPER_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    private AudioManager audioManager;
    private RelativeLayout idViewMediaContainer;
    private Button sysBtnVolumeSettings;
    private SeekBar sysSeekBarVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_you_player_two);
        activity = this;
        context = this;
        /*getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/
        activity.setRequestedOrientation(LANDSCAPE_ORIENTATION);
        castYouTubePlayerSupportFragment = CastYouTubePlayerSupportFragment.newInstance("ePz-YEPXl6Y");
        //strawYouTubePlayerFragment = StrawYouTubePlayerFragment.newInstance(context, DEVELOPER_KEY, "ePz-YEPXl6Y");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.sysFragmentYoutubePlayer, castYouTubePlayerSupportFragment, TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
        idViewMediaContainer = (RelativeLayout) findViewById(R.id.idViewMediaContainer);
        sysBtnVolumeSettings = (Button) findViewById(R.id.sysBtnVolumeSettings);
        sysSeekBarVolume = (SeekBar) findViewById(R.id.sysSeekBarVolume);
        sysBtnVolumeSettings.setTag(0);
        sysBtnVolumeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argView) {
                int tagVal = (int) argView.getTag();
                if (tagVal == 0) {
                    idViewMediaContainer.setVisibility(View.VISIBLE);
                    sysBtnVolumeSettings.setTag(1);
                } else {
                    idViewMediaContainer.setVisibility(View.GONE);
                    sysBtnVolumeSettings.setTag(0);
                }
            }
        });
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        System.out.println("STREAM_MUSIC: " + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        sysSeekBarVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        //sysSeekBarVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        sysSeekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar argSeekBar, int argProgress, boolean argFromUser) {
                //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, argProgress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar argSeekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar argSeekBar) {
            }
        });
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
