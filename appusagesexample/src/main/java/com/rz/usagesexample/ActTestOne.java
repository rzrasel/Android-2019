package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.strawyoutubeplayer.StrawYouTubePlayerFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActTestOne extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private String DEVELOPER_API_KEY = "";
    private String youtubeVideoId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_one);
        //
        activity = this;
        context = this;
        //LibYouTubePlayerView sysLibFragment = (LibYouTubePlayerView) findViewById(R.id.sysLibFragment);
        //sysLibFragment.initView(activity);
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("https://www.youtube.com/watch?v=yTVn6WcVDHY");
        //LibYouTubePlayerView myFragment = LibYouTubePlayerView.newInstance("7FIaXN9C-no");
        DEVELOPER_API_KEY = getResources().getString(R.string.app_google_youtube_key);
        youtubeVideoId = "7FIaXN9C-no";
        StrawYouTubePlayerFragment youTubePlayerFragment = StrawYouTubePlayerFragment.newInstance(context, DEVELOPER_API_KEY, youtubeVideoId);
        getSupportFragmentManager().beginTransaction().replace(R.id.sysYoutubePlayerFragment, youTubePlayerFragment).commit();
    }

    public class YouTubeUrlParser {

        private YouTubeUrlParser() {
        }

        // (?:youtube(?:-nocookie)?\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})
        final static String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";

        public String getVideoId(@NonNull String videoUrl) {
            Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(videoUrl);

            if (matcher.find())
                return matcher.group(1);
            return null;
        }

        public String getVideoUrl(@NonNull String videoId) {
            return "http://youtu.be/" + videoId;
        }
    }
}
