package com.sen.lib.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.sen.lib.R;

/**
 * Editor: sgc
 * Date: 2015/03/19
 */
public class EdgeShadowDrawable extends Drawable{

    public final static int LEFT = 0x01;
    public final static int TOP = 0x02;
    public final static int RIGHT = 0x03;
    public final static int BOTTOM = 0x04;

    private boolean isInit;

    private int mDirction = LEFT;
    private int mWidth;
    private int mHeight;
    private int mStartShadowColor;
    private int mEndShadowColor;

    private Paint mPaint;
    private Rect mRect;

    public EdgeShadowDrawable(Resources resources, int mDirction) {
        this.mDirction = mDirction;
        if (resources == null) {
            throw new NullPointerException("resources is null");
        }
        mStartShadowColor = resources.getColor(R.color.cardview_shadow_start_color);
        mEndShadowColor = resources.getColor(R.color.cardview_shadow_end_color);
    }

    private void init(int width, int height, int startShadowColor, int endShadowColor) {
        mPaint = new Paint();
        mRect = new Rect(0,0,width,height);
        LinearGradient linearGradient = null;
        switch (mDirction) {

            case LEFT:
                linearGradient = new LinearGradient(width*2, 0, 0, 0,
                        new int[]{startShadowColor, startShadowColor, endShadowColor},
                        new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
                 break;

            case TOP:
                linearGradient = new LinearGradient(0, height*2, 0, 0,
                        new int[]{startShadowColor, startShadowColor, endShadowColor},
                        new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
                 break;

            case RIGHT:
                linearGradient = new LinearGradient(-width, 0, width, 0,
                        new int[]{startShadowColor, startShadowColor, endShadowColor},
                        new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
                 break;

            case BOTTOM:
                linearGradient = new LinearGradient(0, -height, 0, height,
                        new int[]{startShadowColor, startShadowColor, endShadowColor},
                        new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
                 break;

        }
        if (linearGradient != null) {
            mPaint.setShader(linearGradient);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (!isInit) {
            isInit = true;
            mWidth = Math.abs(bounds.right-bounds.left);
            mHeight = Math.abs(bounds.bottom-bounds.top);
            init(mWidth, mHeight, mStartShadowColor, mEndShadowColor);
        }
    }

    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mPaint != null && mRect != null) {
            canvas.drawRect(mRect, mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        if (mPaint != null) {
            mPaint.setAlpha(alpha);
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (mPaint != null) {
            mPaint.setColorFilter(cf);
        }
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
