package com.vine.vinemars.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by chengfei on 14/11/16.
 */
public class ImageUtils {

    public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
// canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap createGhostIcon(Drawable src, int color, boolean invert) {
        int width = src.getIntrinsicWidth();
        int height = src.getIntrinsicHeight();
        if (width <= 0 || height <= 0) {
            throw new UnsupportedOperationException("Source drawable needs an intrinsic size.");
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint colorToAlphaPaint = new Paint();
        int invMul = invert ? -1 : 1;
        colorToAlphaPaint.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0, 0, 0, 0, Color.red(color),
                0, 0, 0, 0, Color.green(color),
                0, 0, 0, 0, Color.blue(color),
                invMul * 0.213f, invMul * 0.715f, invMul * 0.072f, 0, invert ? 255 : 0,
        })));
        canvas.saveLayer(0, 0, width, height, colorToAlphaPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(invert ? Color.WHITE : Color.BLACK);
        src.setBounds(0, 0, width, height);
        src.draw(canvas);
        canvas.restore();
        return bitmap;
    }
}
