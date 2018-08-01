package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class ActOnPlayAssetsVideo extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private VideoView sysVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_on_play_assets_video);
        activity = this;
        context = this;
        sysVideoView = (VideoView) findViewById(R.id.sysVideoView);
        //String assetVideoPath = "file:///android_asset/big_buck_bunny.mp4";
        /*try {
            //AssetFileDescriptor descriptor = context.getAssets().openFd("video.mp4");
            System.out.println("LOG_WRITER: " + context.getResources().getAssets());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try {
            System.out.println("LOG_WRITER: " + context.getResources().getAssets().list("").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Uri path = Uri.fromFile(new File("assets/big_buck_bunny.mp4"));
        Uri path = Uri.fromFile(new File("file:///android_asset/big_buck_bunny.mp4"));
        System.out.println("LOG_WRITER: " + path.getPath());
        //String assetVideoPath = "content://" + getPackageName() + "/big_buck_bunny.mp4";
        final String assetVideoPath = path.getPath();
        //String assetVideoPath = "//assets/" + "/big_buck_bunny.mp4";
        String rawVideoPath = "android.resource://" + getPackageName() + "/" + R.raw.big_buck_bunny;
        //sysVideoView.setVideoURI(Uri.parse(videoPath));
        //sysVideoView.setVideoPath(assetVideoPath);
        //mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
        /*sysVideoView.setVideoURI(path);
        sysVideoView.start();*/
        sysVideoView.setVideoPath(rawVideoPath);
        sysVideoView.start();
        sysVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer argMediaPlayer) {
                /*try {
                    //argMediaPlayer.setDataSource(assetVideoPath);
                    //argMediaPlayer.start();
                    System.out.println("LOG_WRITER: --------------------");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                System.out.println("LOG_WRITER: --------------------");
            }
        });
    }
}
