package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.library.LogWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        new HTTPTestRequest(context).HTTPRequestExecute(new HTTPTestRequest.HTTPEventListenerHandler() {
            @Override
            public void HTTPExecute(Object argDataModelItems) {
                LogWriter.Log("PRINT_HTTPTestRequest_ActSplash: " + argDataModelItems.toString());
            }
        });
        startActivity(new Intent(context, ActListViewOne.class));
        finish();
    }
}
