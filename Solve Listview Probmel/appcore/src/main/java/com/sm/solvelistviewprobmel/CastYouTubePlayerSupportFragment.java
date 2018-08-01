package com.sm.solvelistviewprobmel;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class CastYouTubePlayerSupportFragment extends YouTubePlayerSupportFragment {
    private String currentVideoID = "video_id";
    private YouTubePlayer activePlayer;
    //private StrawYouTubeMediaController youTubeMediaController;

    public static CastYouTubePlayerSupportFragment newInstance(String argYoutubeVideoId) {
        CastYouTubePlayerSupportFragment playerYouTubeFrag = new CastYouTubePlayerSupportFragment();

        Bundle bundle = new Bundle();
        bundle.putString("youtube_video_id", argYoutubeVideoId);

        playerYouTubeFrag.setArguments(bundle);
        playerYouTubeFrag.init();

        return playerYouTubeFrag;
    }

    private void init() {
        //youTubeMediaController = new StrawYouTubeMediaController(getContext());
        initialize(DeveloperKey.DEVELOPER_KEY, new OnYoutubeInitialization());
        /*getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View argView, MotionEvent argMotionEvent) {
                System.out.println("setOnTouchListener_argMotionEvent");
                return false;
            }
        });*/
    }

    private class OnYoutubeInitialization implements YouTubePlayer.OnInitializedListener {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
            activePlayer = argYouTubePlayer;
            //activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            /*if (!argWasRestored) {
                activePlayer.loadVideo(getArguments().getString("youtube_video_id"), 0);
                //System.out.println("VIDEO: " + getArguments().getString("youtube_video_id"));
                activePlayer.play();

            }*/
            activePlayer.setPlayerStateChangeListener(new OnPlayerStateChangeListener());
            activePlayer.setPlaybackEventListener(onPlaybackEventListener);

            String videoID = getArguments().getString("youtube_video_id");
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
            //
        }
    }

    private class OnPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        @Override
        public void onLoading() {
            //Toast.makeText(YoutubeActivity.this, "Video is loading", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoaded(String argVideoId) {
            StrawYouTubeMediaController.youTubeMediaController.onSetDurationTime(activePlayer.getDurationMillis());
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

    public class OnYouTubeMediaController implements StrawYouTubeMediaController.OnEventHandler {
        @Override
        public void onPlayPause(boolean argIsPlay) {
            if (activePlayer != null) {
                if (argIsPlay && !activePlayer.isPlaying()) {
                    activePlayer.play();
                } else {
                    activePlayer.pause();
                }
            }
        }
    }

    public interface DeveloperKey {
        public static String DEVELOPER_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    }
}
//http://android-er.blogspot.com/2013/06/handle-player-state-changes-and.html
//https://www.stacktips.com/tutorials/android/youtube-android-player-api-example