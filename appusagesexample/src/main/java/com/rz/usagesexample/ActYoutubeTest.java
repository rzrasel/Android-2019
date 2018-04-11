package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.librarycore.apppackage.APPStaticPackageInfo;
import com.rz.librarycore.storage.SharePrefPrivateHandler;
import com.rz.strawyoutubeplayer.StrawYouTubePlayerFragment;

public class ActYoutubeTest extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private String DEVELOPER_API_KEY = "";
    private String youtubeVideoId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_youtube_test);
        activity = this;
        context = this;
        SharePrefPrivateHandler sharePrefHandler = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        //sharePrefHandler.clearAll();
        sharePrefHandler.printAllKeyValue();
        DEVELOPER_API_KEY = getResources().getString(R.string.app_google_youtube_key);
        youtubeVideoId = "7FIaXN9C-no";
        StrawYouTubePlayerFragment youTubePlayerFragment = StrawYouTubePlayerFragment.newInstance(context, DEVELOPER_API_KEY, youtubeVideoId);
        getSupportFragmentManager().beginTransaction().replace(R.id.sysYoutubePlayerFragment, youTubePlayerFragment).commit();
    }
}
/*
@Override
public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean success) {
    player.setFullscreenControlFlags(0);
}
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
MAC(OFFICE): A8:31:C1:5E:DC:30:53:D9:62:37:35:A5:52:13:4D:AC:BE:7A:D9:9F
*/