package com.sen.lib.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Editor: sgc
 * Date: 2015/03/19
 */
public class InvertedDrawable extends Drawable {

    private int mWidth;
    private int mHeight;

    private Bitmap mBitmap;

    public InvertedDrawable(Bitmap mBitmap) {

        if (mBitmap == null) {
            throw new NullPointerException("Bitmap can not be null!");
        }

        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        this.mBitmap = mBitmap;

    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight*3/2;
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mBitmap != null) {

            Paint paint = new Paint();
            canvas.drawBitmap(mBitmap, 0, 0, paint);

            Matrix matrix = new Matrix();
            matrix.preScale(1, -1);
            Bitmap composeBitmap = Bitmap.createBitmap(mBitmap, 0, mBitmap.getHeight()/2, mBitmap.getWidth(), mBitmap.getHeight()/2, matrix, false);
            Canvas bitmapCanvas = new Canvas(composeBitmap);
            Paint composePaint = new Paint();
            Rect mRect = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
            LinearGradient duffixGradient = new LinearGradient(0, -mHeight/4, 0, mHeight/2, 0xFFFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP);
            composePaint.setShader(duffixGradient);
            composePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            bitmapCanvas.drawRect(mRect, composePaint);

            canvas.translate(0, mRect.height()+2);
            canvas.drawBitmap(composeBitmap, 0, 0, paint);

            if (!composeBitmap.isRecycled()) {
                composeBitmap.recycle();
            }
            bitmapCanvas = null;

        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
