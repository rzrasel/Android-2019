package com.sm.solvelistviewprobmel;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class CastYouTubePlayerSupportFragment extends YouTubePlayerSupportFragment {
    private String currentVideoID = "video_id";
    private YouTubePlayer activePlayer;
    public static CastYouTubePlayerSupportFragment newInstance(String argYoutubeVideoId) {
        CastYouTubePlayerSupportFragment playerYouTubeFrag = new CastYouTubePlayerSupportFragment();

        Bundle bundle = new Bundle();
        bundle.putString("youtube_video_id", argYoutubeVideoId);

        playerYouTubeFrag.setArguments(bundle);
        playerYouTubeFrag.init();

        return playerYouTubeFrag;
    }

    private void init() {
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
    public interface DeveloperKey {
        public static String DEVELOPER_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    }
}
//http://android-er.blogspot.com/2013/06/handle-player-state-changes-and.html