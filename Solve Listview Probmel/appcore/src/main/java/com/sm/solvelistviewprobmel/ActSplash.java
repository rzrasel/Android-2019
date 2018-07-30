package com.sm.solvelistviewprobmel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.library.LogWriter;
import com.library.iConn.NetConnDetect;

import java.util.ArrayList;
import java.util.HashMap;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private int THREAD_TASK_CHECK = 1;
    private int threadCounter = 0;
    private NetConnDetect netConnDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        netConnDetect = NetConnDetect.getInstance();
        new HTTPTestRequest(context).HTTPRequestExecute(new HTTPTestRequest.HTTPEventListenerHandler() {
            @Override
            public void HTTPExecute(Object argDataModelItems) {
                LogWriter.Log("PRINT_HTTPTestRequest_ActSplash: " + argDataModelItems.toString());
            }
        });
        //startActivity(new Intent(context, ActListViewOne.class));
        startActivity(new Intent(context, ActYouPlayerOne.class));
        //startActivity(new Intent(context, ActYouTubePlayerView.class));
        finish();
        threadBackgroundNetCheck.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        handlerBackgroundNetCheck.removeCallbacks(threadBackgroundNetCheck);
        super.onDestroy();
    }

    Thread threadBackgroundNetCheck = new Thread(new Runnable() {
        @Override
        public void run() {
            threadCounter++;
            Message messageObj = handlerBackgroundNetCheck.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("thread_message", "Test message - " + threadCounter);
            messageObj.what = THREAD_TASK_CHECK;
            messageObj.setData(bundle);
            handlerBackgroundNetCheck.sendMessage(messageObj);
        }
    });

    Handler handlerBackgroundNetCheck = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message argMessage) {
            String handlerResponse = argMessage.getData().getString("thread_message");
            if (handlerResponse != null) {
                if (argMessage.what == THREAD_TASK_CHECK) {
                    System.out.println("LOG_WRITE_HANDLER_WHAT: " + handlerResponse);
                }
                System.out.println("LOG_WRITE_HANDLER_MESSAGE: " + handlerResponse);
                if (!netConnDetect.isConnected(context)) {
                    System.out.println("LOG_WRITE_INTERNET_CONNECTION_ERROR");
                } else {
                    System.out.println("LOG_WRITE_INTERNET_CONNECTED");
                }
                handlerBackgroundNetCheck.postDelayed(threadBackgroundNetCheck, 1000);
            } else {
                System.out.println("LOG_WRITE_HANDLER_MESSAGE_NULL");
            }
            return false;
        }
    });
    // Define the Handler that receives messages from the thread and update the progress
    Handler handlerBackgroundNetCheck_M = new Handler() {
        public void handleMessage(Message argMessage) {
            String handlerResponse = argMessage.getData().getString("thread_message");
            if (handlerResponse != null) {
                if (argMessage.what == THREAD_TASK_CHECK) {
                    System.out.println("LOG_WRITE_HANDLER_WHAT: " + handlerResponse);
                }
                System.out.println("LOG_WRITE_HANDLER_MESSAGE: " + handlerResponse);
                if (!netConnDetect.isConnected(context)) {
                    System.out.println("LOG_WRITE_INTERNET_CONNECTION_ERROR");
                } else {
                    System.out.println("LOG_WRITE_INTERNET_CONNECTED");
                }
                handlerBackgroundNetCheck.postDelayed(threadBackgroundNetCheck, 1000);
            } else {
                System.out.println("LOG_WRITE_HANDLER_MESSAGE_NULL");
            }

        }
    };
}
//https://androidexample.com/Thread_With_Handlers_-_Android_Example/index.php?view=article_discription&aid=58
//https://www.dev2qa.com/android-thread-message-looper-handler-example/