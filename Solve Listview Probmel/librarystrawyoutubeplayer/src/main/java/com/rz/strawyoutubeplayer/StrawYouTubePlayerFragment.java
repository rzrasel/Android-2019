package com.rz.strawyoutubeplayer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by Rz Rasel on 2018-03-04.
 */

public class StrawYouTubePlayerFragment extends YouTubePlayerSupportFragment {
    private Activity activity;
    private Context context;
    private String videoIdKey = "youtube_video_id";
    private String developerKey = "";
    private YouTubePlayer activePlayer;

    public static StrawYouTubePlayerFragment newInstance(Context argContext, String argDeveloperKey, String argYoutubeVideoId) {
        StrawYouTubePlayerFragment playerYouTubeFrag = new StrawYouTubePlayerFragment();
        playerYouTubeFrag.activity = (Activity) argContext;
        playerYouTubeFrag.context = argContext;
        playerYouTubeFrag.developerKey = argDeveloperKey;
        Bundle bundle = new Bundle();
        bundle.putString(playerYouTubeFrag.videoIdKey, argYoutubeVideoId);
        playerYouTubeFrag.setArguments(bundle);
        playerYouTubeFrag.init();
        return playerYouTubeFrag;
    }

    private void init() {
        initialize(developerKey, new OnInitialization());
    }

    private class OnInitialization implements YouTubePlayer.OnInitializedListener {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
            activePlayer = argYouTubePlayer;
            //activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            activePlayer.setFullscreenControlFlags(0);
            /*if (!argWasRestored) {
                activePlayer.loadVideo(getArguments().getString("youtube_video_id"), 0);
                //System.out.println("VIDEO: " + getArguments().getString("youtube_video_id"));
                activePlayer.play();

            }*/
            activePlayer.setPlayerStateChangeListener(new OnPlayerStateChangeListener());
            activePlayer.setPlaybackEventListener(onPlaybackEventListener);
            String videoID = getArguments().getString(videoIdKey);
            if (activePlayer != null) {
                if (argWasRestored) {
                    activePlayer.play();
                } else {
                    try {
                        activePlayer.loadVideo(videoID, 0);
                    } catch (IllegalStateException e) {
                        //youTubeView.initialize(YouTubeConstants.youtube_API_key, this);
                    }
                }
                //activePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider argProvider, YouTubeInitializationResult argYouTubeInitializationResult) {
            int RECOVERY_DIALOG_REQUEST = 1;
            if (argYouTubeInitializationResult.isUserRecoverableError()) {
                argYouTubeInitializationResult.getErrorDialog(activity, RECOVERY_DIALOG_REQUEST).show();
            } else {
                //String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
                String errorMessage = "custom error message";
                //Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                System.out.println("Error: " + errorMessage);
            }
        }
    }

    private class OnPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        @Override
        public void onLoading() {
            //Toast.makeText(YoutubeActivity.this, "Video is loading", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoaded(String argVideoId) {
        }

        @Override
        public void onAdStarted() {
            //Toast.makeText(YoutubeActivity.this, "Ad started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            //Toast.makeText(YoutubeActivity.this, "Video started", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason argErrorReason) {
            System.out.println("Error: " + argErrorReason);
        }
    }

    private YouTubePlayer.PlaybackEventListener onPlaybackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            //Toast.makeText(YoutubeActivity.this, "Video is playing", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            //Toast.makeText(YoutubeActivity.this, "Video is paused", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            //Toast.makeText(YoutubeActivity.this, "Video is stopped", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean argIsBuffering) {
        }

        @Override
        public void onSeekTo(int argEndPositionMillis) {
        }
    };

    private static final int parseInt(String intString, int defaultValue) {
        try {
            return intString != null ? Integer.valueOf(intString) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String formatTime(int millis) {
        int seconds = millis / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        return (hours == 0 ? "" : hours + ":") + String.format("%02d:%02d", minutes % 60, seconds % 60);
    }
}
