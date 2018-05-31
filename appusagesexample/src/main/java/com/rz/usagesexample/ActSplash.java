package com.rz.usagesexample;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActSplash extends AppCompatActivity {
    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        onRedirect(ActDashboard.class);
    }

    private void onRedirect(final Class argClass) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), argClass);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable, 1500);
    }
}
/*
https://github.com/myinnos/AppIconNameChanger
https://gist.github.com/z8888q/7280681

https://github.com/leolin310148/ShortcutBadger
https://www.youtube.com/watch?v=STBfTpa_4eI
https://stackoverflow.com/questions/6011786/add-new-item-count-to-icon-on-button-android
*/
