package com.rz.strawyoutubeplayer;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Rz Rasel on 2018-03-05.
 */

public class StrawYouTubePlayer extends FrameLayout {
    private Activity activity;
    private Context context;
    public StrawYouTubePlayer(Context argContext) {
        super(argContext);
        init(argContext, null);
    }

    public StrawYouTubePlayer(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        init(argContext, argAttrs);
    }

    public StrawYouTubePlayer(Context argContext, AttributeSet argAttrs, int argDefStyleAttr) {
        super(argContext, argAttrs, argDefStyleAttr);
        init(argContext, argAttrs);
    }

    private void init(Context argContext, AttributeSet argAttrs) {
        context = argContext;
        activity = (Activity) context;
    }
}
