package com.rz.strawyoutubeplayer;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * Created by Rz Rasel on 2018-02-28.
 */
//
/*public class LibYouTubePlayerView extends FrameLayout {
    private Activity activity;
    private Context context;
    private View mControlsFrame;
    private LinearLayout videoContainer;
    private LinearLayout mediaController;

    public LibYouTubePlayerView(Context argContext) {
        super(argContext);
        init(argContext, null);
    }

    public LibYouTubePlayerView(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        init(argContext, argAttrs);
    }

    public LibYouTubePlayerView(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        init(argContext, argAttrs);
    }

    private void init(Context argContext, AttributeSet argAttrs) {
        context = argContext;
        /=*videoContainer = new LinearLayout(context);
        mediaController = new LinearLayout(context);
        videoContainer.setBackgroundColor(Color.CYAN);
        mediaController.setBackgroundColor(Color.RED);
        videoContainer.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 250);
        videoContainer.setLayoutParams(layoutParams);
        mediaController.setLayoutParams(layoutParams);
        TextView textView = new TextView(context);
        textView.setText("Dljfdjfls DLjffj");
        super.addView(videoContainer);
        super.addView(mediaController);*=/
        //LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /=*LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        mControlsFrame = layoutInflater.inflate(R.layout.lib_video_container, this, false);
        FrameLayout.LayoutParams controlsLp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mControlsFrame, controlsLp);*=/
        //FrameLayout frameLayout = (FrameLayout) mControlsFrame.findViewById(R.id.sysFragmentYoutubePlayer);
        //frameLayout.beg
    }

    public void initView(Activity argActivity) {
        activity = argActivity;
        //activity.getFragmentManager().beginTransaction().replace(mControlsFrame.getId(), new YouTubePlayerFragment()).commit();
    }
}*/
public class LibYouTubePlayerView extends YouTubePlayerSupportFragment {
    private String currentVideoID = "video_id";
    private YouTubePlayer activePlayer;

    public static LibYouTubePlayerView newInstance(String argYoutubeVideoId) {
        LibYouTubePlayerView playerYouTubeFrag = new LibYouTubePlayerView();

        Bundle bundle = new Bundle();
        bundle.putString("youtube_video_id", argYoutubeVideoId);

        playerYouTubeFrag.setArguments(bundle);
        playerYouTubeFrag.init();

        return playerYouTubeFrag;
    }

    private void init() {
        initialize(DeveloperKey.DEVELOPER_KEY, new OnYoutubeInitialization());
    }

    private class OnYoutubeInitialization implements YouTubePlayer.OnInitializedListener {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider argProvider, YouTubePlayer argYouTubePlayer, boolean argWasRestored) {
            activePlayer = argYouTubePlayer;
            //activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
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
        public void onInitializationFailure(YouTubePlayer.Provider
                                                    provider, YouTubeInitializationResult youTubeInitializationResult) {
            //
        }
    }

    //@Override
    public void onYouTubeVideoPaused() {
        activePlayer.pause();
    }

    public interface DeveloperKey {
        public static String DEVELOPER_KEY = "AIzaSyAqqIRxuAOuN1fsHCoc41-Lo0-XKiB8asc";
    }
}
/*public class LibYouTubePlayerView extends YouTubePlayerFragment {
    private Activity activity;
    private Context context;
    //private YouTubePlayerFragment youTubePlayerFragment;
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private String youtubeDeveloperKey;

    /=*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_you_tube_player_view);
        activity = this;
        context = this;
        youtubeDeveloperKey = getResources().getString(R.string.app_google_youtube_key);
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.sysYoutubePlayerFragment);
        youTubePlayerFragment.initialize(youtubeDeveloperKey, new OnYoutubeInitialized());
    }*=/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentYoutubeView = inflater.inflate(R.layout.lib_you_tube_player_view, container, false);
        youtubeDeveloperKey = getResources().getString(R.string.app_google_youtube_key);
        youTubePlayerSupportFragment = new YouTubePlayerSupportFragment();
        youTubePlayerSupportFragment.initialize(youtubeDeveloperKey, new OnYoutubeInitialized());
        /=*FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sysYoutubePlayerFragment, youTubePlayerSupportFragment);
        fragmentTransaction.commit();*=/
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sysYoutubePlayerFragment, youTubePlayerSupportFragment);
        fragmentTransaction.commit();
        return fragmentYoutubeView;
    }

    private class OnYoutubeInitialized implements YouTubePlayer.OnInitializedListener {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            //
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            //
        }
    }
}*/
/*
https://github.com/lipangit/JiaoZiVideoPlayer/blob/develop/jiaozivideoplayer/src/main/java/cn/jzvd/JZVideoPlayer.java
https://github.com/afollestad/easy-video-player

https://github.com/miaoyongjun/MVideo
*/
