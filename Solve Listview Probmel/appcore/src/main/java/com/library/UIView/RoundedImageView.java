package com.library.UIView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by Rz Rasel on 16-Aug-16.
 */
public class RoundedImageView extends android.support.v7.widget.AppCompatImageView {

    public RoundedImageView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap getBitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = getBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int width = getWidth(), height = getHeight();
        if (width > height) {
            width = height;
        }

        Bitmap roundBitmap = getRoundedCroppedBitmap(bitmap, width);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
        /*Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(roundBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, roundBitmap.getWidth(), roundBitmap.getHeight())), 100, 100, mpaint);*/

    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius, false);
        else
            finalBitmap = bitmap;
        int newWidth = finalBitmap.getWidth();
        int newHeight = finalBitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        //canvas.drawColor(Color.RED);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }
}
//https://github.com/lopspower/CircularImageView
//https://stackoverflow.com/questions/17655264/how-to-add-a-shadow-and-a-border-on-circular-imageview-android
//https://www.viralandroid.com/2016/04/circular-android-imageview-with-border-color.html