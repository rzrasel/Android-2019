package com.sm.solvelistviewprobmel;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Formatter;

public class StrawYouTubeMediaController extends FrameLayout {
    private Context context;
    public static StrawYouTubeMediaController youTubeMediaController;
    private RelativeLayout sysReLayMediaContainer;
    private Button sysBtnPlayPause;
    private SeekBar sysSeekBarVideo;
    private SeekBar sysSeekBarVolume;
    private TextView sysTextViewCurrent;
    private TextView sysTextViewDuration;
    private Formatter formatter;
    private StringBuilder formattedStringBuilder;
    private static final int defaultTimeout = 3000;

    public StrawYouTubeMediaController(Context argContext, AttributeSet argAttributeSet) {
        super(argContext, argAttributeSet);
        context = argContext;
        /*TypedArray typedArray = context.obtainStyledAttributes(argAttributeSet, R.styleable.StrawYouTubeMediaController);
        //mScalable = typedArray.getBoolean(R.styleable.StrawYouTubeMediaController_SYTMC_scalable, false);
        typedArray.recycle();*/
        init(context);
    }

    public StrawYouTubeMediaController(Context argContext) {
        super(argContext);
        init(context);
    }

    private void init(Context argContext) {
        context = argContext;
        youTubeMediaController = this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRoot = inflater.inflate(R.layout.straw_youtube_media_controller, this);
        //viewRoot.setOnTouchListener(mTouchListener);
        initControllerView(viewRoot);
    }

    private void initControllerView(View argView) {
        sysReLayMediaContainer = (RelativeLayout) argView.findViewById(R.id.sysReLayMediaContainer);
        sysBtnPlayPause = (Button) argView.findViewById(R.id.sysBtnPlayPause);
        sysSeekBarVideo = (SeekBar) argView.findViewById(R.id.sysSeekBarVideo);
        sysSeekBarVolume = (SeekBar) argView.findViewById(R.id.sysSeekBarVolume);
        sysTextViewCurrent = (TextView) argView.findViewById(R.id.sysTextViewCurrent);
        sysTextViewDuration = (TextView) argView.findViewById(R.id.sysTextViewDuration);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message argMessage) {
        }
    };

    public void onSetDurationTime(int argTimeMillis) {
        //System.out.println("LOG_WRITER: " + argTimeMillis);
        //sysTextViewDuration.setText(stringForTime(argTimeMillis) + "");
        System.out.println("LOG_WRITER: " + stringForTime(argTimeMillis));
    }

    private String stringForTime(int argTimeMillis) {
        int totalSeconds = argTimeMillis / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        //formattedStringBuilder.setLength(0);
        //System.out.println("LOG_WRITER: " + argTimeMillis);
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return formatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    public interface OnEventHandler {
        public void onPlayPause(boolean argIsPlay);
    }

    public enum OnEventProperty {
        STATE_PLAYING(1),
        STATE_PAUSE(2),
        STATE_COMPLETE(5);

        private int value;

        OnEventProperty(int argValue) {
            value = argValue;
        }

        public int getValue() {
            return this.value;
        }
    }
}
