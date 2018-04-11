package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.librarycore.apppackage.APPStaticPackageInfo;
import com.rz.librarycore.log.SecureKeyManager;
import com.rz.librarycore.storage.SharePrefPrivateHandler;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        SharePrefPrivateHandler sharePrefHandler = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        //sharePrefHandler.clearAll();
        SecureKeyManager secureKeyManager = new SecureKeyManager(activity, context);
        SecureKeyManager.onSetAppIsRunFirstTime(context);
        /*startActivity(new Intent(context, ActYoutubeTest.class));
        startService(new Intent(getBaseContext(), OnServiceTest.class));*/
        startActivity(new Intent(context, ActDataList.class));
        finish();
    }
}
//https://github.com/youtube/yt-android-player/blob/master/src/com/examples/youtubeapidemo/PlayerControlsDemoActivity.java
//http://addcomponent.com/android-native-plugin-unity/
//https://stackoverflow.com/questions/24564470/unity3d-and-android-studio-integration
/*
How to make Unity3D android plugin using Android Studio
https://www.youtube.com/watch?v=0ahGeTNUPLM
https://www.youtube.com/watch?v=4LBMT7wILBI

Google Login in Unity for iOS & Android
https://www.youtube.com/watch?v=mmLheAYQoO8


Unity Google Play Service Real Time Multiplayer
https://www.youtube.com/watch?v=joOkQK5EjHw
https://www.youtube.com/watch?v=fM87eDzhalc

https://www.youtube.com/watch?v=XmHXl-UFTqM

https://support.gamesparks.net/support/discussions/topics/1000063337

Sign in with Google
https://codelabs.developers.google.com/codelabs/playservices_unity/index.html?index=..%2F..%2Findex#0
https://forum.unity.com/threads/how-to-add-the-official-google-play-game-services-plugin-to-unity.243683/
https://www.youtube.com/watch?v=5Ae8GeRmdH0
*/