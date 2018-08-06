package com.sm.solvelistviewprobmel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

import com.google.android.youtube.player.YouTubePlayer;

import java.util.Formatter;
import java.util.Locale;

public class StrawYouTubeMediaController extends FrameLayout {
    private Context context;
    private YouTubePlayer activePlayer;
    public static StrawYouTubeMediaController youTubeMediaController;
    //private OnEventHandler onEventHandler;
    //private String playerStatus;
    private EnumEventProperty onEventProperty = EnumEventProperty.STATE_PLAYING;
    private boolean isPlay = true;
    private RelativeLayout sysReLayMediaContainer;
    private Button sysBtnPlayPause;
    private SeekBar sysSeekBarVideo;
    private SeekBar sysSeekBarVolume;
    private TextView sysTextViewCurrent;
    private TextView sysTextViewDuration;
    private Formatter formatter;
    private StringBuilder formattedStringBuilder;
    private static final int defaultTimeout = 3000;
    private boolean isDragging;

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
        sysTextViewDuration = (TextView) argView.findViewById(R.id.sysTextViewDuration);
        sysTextViewCurrent = (TextView) argView.findViewById(R.id.sysTextViewCurrent);
        sysSeekBarVideo.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sysSeekBarVideo.setMax(1000);
        formattedStringBuilder = new StringBuilder();
        formatter = new Formatter(formattedStringBuilder, Locale.getDefault());
        sysBtnPlayPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View argView) {
                /*Drawable drawable = context.getResources().getDrawable(R.drawable.youtube_btn_pause);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();*/
                /*if (buttonStatus.equalsIgnoreCase("play")) {
                    sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_pause);
                    buttonStatus = "pause";
                    isPlay = false;
                } else if (buttonStatus.equalsIgnoreCase("pause")) {
                    sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_play);
                    buttonStatus = "play";
                    isPlay = true;
                }*/
                /*if (onEventProperty == OnEventProperty.STATE_PLAYING) {
                    sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_pause);
                    onEventProperty = OnEventProperty.STATE_PAUSE;
                    isPlay = false;
                } else if (onEventProperty == OnEventProperty.STATE_PAUSE) {
                    sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_play);
                    onEventProperty = OnEventProperty.STATE_PLAYING;
                    isPlay = true;
                }*/
                //onChangeBtnDrawable(onEventProperty);
                /*if (onEventHandler != null) {
                    onEventHandler.onPlayPause(isPlay);
                }*/
                onSetPlayerPlayPause();
            }
        });
    }

    private Handler handlerMediaController = new Handler() {
        @Override
        public void handleMessage(Message argMessage) {
        }
    };

    /*public void setOnEventListener(OnEventHandler argOnEventHandler) {
        onEventHandler = argOnEventHandler;
    }*/
    public void onSetYouTubePlayer(YouTubePlayer argActivePlayer) {
        activePlayer = argActivePlayer;
        onSetProgress();
    }

    public void onSetDurationTime(int argTimeMillis) {
        //System.out.println("LOG_WRITER: " + argTimeMillis);
        sysTextViewDuration.setText(stringForTime(argTimeMillis) + "");
        //System.out.println("LOG_WRITER: " + stringForTime(argTimeMillis));
    }

    public void onSetCurrentTime(int argTimeMillis) {
        //System.out.println("LOG_WRITER: " + argTimeMillis);
        sysTextViewCurrent.setText(stringForTime(argTimeMillis) + "");
        //System.out.println("LOG_WRITER: " + stringForTime(argTimeMillis));
    }

    /*public void onSetPlayerStatus(OnEventProperty argOnEventProperty) {
        onEventProperty = argOnEventProperty;
        isPlay = !isPlay;
        onChangeBtnDrawable();
    }*/

    private void onSetPlayerPlayPause() {
        if (onEventProperty == EnumEventProperty.STATE_PLAYING) {
            sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_play);
            onEventProperty = EnumEventProperty.STATE_PAUSE;
            activePlayer.pause();
        } else if (onEventProperty == EnumEventProperty.STATE_PAUSE) {
            sysBtnPlayPause.setBackgroundResource(R.drawable.youtube_btn_pause);
            onEventProperty = EnumEventProperty.STATE_PLAYING;
            activePlayer.play();
        }
    }

    private String stringForTime(int argTimeMillis) {
        int totalSeconds = argTimeMillis / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        formattedStringBuilder.setLength(0);
        //System.out.println("LOG_WRITER: " + argTimeMillis);
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return formatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /*public interface OnEventHandler {
        public void onPlayPause(boolean argIsPlay);
    }*/
    private int onSetProgress() {
        if (activePlayer == null || isDragging) {
            return 0;
        }
        int position = activePlayer.getCurrentTimeMillis();
        int duration = activePlayer.getDurationMillis();
        if (sysSeekBarVideo != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * position / duration;
                sysSeekBarVideo.setProgress((int) pos);
            }
            /*int percent = mPlayer.getBufferPercentage();
            sysSeekBarVideo.setSecondaryProgress(percent * 10);*/
        }

        if (sysTextViewDuration != null)
            sysTextViewDuration.setText(stringForTime(duration));
        if (sysTextViewCurrent != null)
            sysTextViewCurrent.setText(stringForTime(position));
        return position;
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int newPosition = 0;
        boolean change = false;

        @Override
        public void onStartTrackingTouch(SeekBar argSeekBar) {
            if (activePlayer == null) {
                return;
            }
        }

        @Override
        public void onProgressChanged(SeekBar argSeekBar, int argProgress, boolean argFromUser) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar argSeekBar) {
        }
    };

    public enum EnumHandlerProperty {
        SHOW_PROGRESS(1),
        SHOW_COMPLETE(2);
        private int value;

        EnumHandlerProperty(int argValue) {
            value = argValue;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum EnumEventProperty {
        STATE_PLAYING(1),
        STATE_PAUSE(2),
        STATE_COMPLETE(5);

        private int value;

        EnumEventProperty(int argValue) {
            value = argValue;
        }

        public int getValue() {
            return this.value;
        }
    }
}
//https://github.com/linsea/UniversalVideoView/blob/master/universalvideoview/src/main/java/com/universalvideoview/UniversalMediaController.java