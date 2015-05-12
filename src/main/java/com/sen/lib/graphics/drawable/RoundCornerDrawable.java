package com.sen.lib.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.sen.lib.R;

/**
 * Editor: sgc
 * Date: 2015/03/16
 */
public class RoundCornerDrawable extends Drawable {

    private Paint mPaint;

    private BitmapShader mBitmapShader;

    private RectF mRectFSource;
    private RectF mRectFTarget;
    private Path mPath;
    private Path mShadowPath;

    private float mLeftTopCorner;
    private float mLeftBottomCorner;
    private float mRightTopCorner;
    private float mRightBottomCorner;

    private float offset = 1.0f;

    private float mShadowSize;

    private boolean mIsHasShadow;
    private boolean mIsColor;

    private int mShadowStartColor;
    private int mShadowEndColor;

    private int mWidth;
    private int mHeight;

    private int mColor;

    public RoundCornerDrawable(Resources resources, Bitmap bitmap, float corner, float shadowSize) {
        if (resources != null) {
            initBitmap(resources, bitmap, corner, corner, corner, corner, shadowSize, true);
        } else {
            initBitmap(resources, bitmap, corner, corner, corner, corner, shadowSize, false);
        }
    }

    public RoundCornerDrawable(Resources resources, Bitmap bitmap, float leftTopCorner,
                               float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize) {

        if (resources != null) {
            initBitmap(resources, bitmap, leftTopCorner, leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, true);
        } else {
            initBitmap(resources, bitmap, leftTopCorner, leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, false);
        }
    }

    public RoundCornerDrawable(Resources resources, int color, float corner, float shadowSize) {
        if (resources != null) {
            initColor(resources, color, corner, corner, corner, corner, shadowSize, true);
        } else {
            initColor(resources, color, corner, corner, corner, corner, shadowSize, false);
        }
    }

    public RoundCornerDrawable(Resources resources, int color, float leftTopCorner,
                               float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize) {
        if (resources != null) {
            initColor(resources, color, leftTopCorner, leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, true);
        } else {
            initColor(resources, color, leftTopCorner, leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, false);
        }
    }

    private void initBitmap(Resources resources, Bitmap bitmap, float leftTopCorner,
          float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize, boolean isHasShadow) {

        if (bitmap == null || (isHasShadow && resources == null)) {
            throw new IllegalArgumentException("The bitmap or resources should not be null!");
        }
        initConfig();
        initShadowColor(resources, isHasShadow);
        initSize(bitmap.getWidth(), bitmap.getHeight(), leftTopCorner,
                leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, isHasShadow);

        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);


    }

    private void initColor(Resources resources, int color, float leftTopCorner,
                           float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize, boolean isHasShadow) {
        initConfig();
        this.mIsColor = true;
        this.mColor = color;
        this.mLeftTopCorner = leftTopCorner;
        this.mLeftBottomCorner = leftBottomCorner;
        this.mRightTopCorner = rightTopCorner;
        this.mRightBottomCorner = rightBottomCorner;
        if (resources != null && isHasShadow) {
            this.mShadowSize = shadowSize;
            this.mIsHasShadow = isHasShadow;
            initShadowColor(resources, isHasShadow);
        }
        mPaint.setColor(this.mColor);
    }

    /*private void init(int contentWidth, int contentHeight, float leftTopCorner,
                      float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize, boolean isHasShadow) {

        initSize(contentWidth, contentHeight, leftTopCorner,
                leftBottomCorner, rightTopCorner, rightBottomCorner, shadowSize, isHasShadow);
    }*/

    private void initConfig() {
        mPath = new Path();
        mShadowPath = new Path();
        mRectFTarget = new RectF();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    private void initShadowColor(Resources resources, boolean isHasShadow) {
        if (isHasShadow && resources != null) {
            this.mShadowStartColor = resources.getColor(R.color.cardview_shadow_start_color);
            this.mShadowEndColor = resources.getColor(R.color.cardview_shadow_end_color);
        }
    }

    private void initSize(int contentWidth, int contentHeight, float leftTopCorner,
                          float leftBottomCorner, float rightTopCorner, float rightBottomCorner, float shadowSize, boolean isHasShadow) {

        if ((leftTopCorner + rightTopCorner) >= contentWidth) {
            throw new IllegalArgumentException("it's must width > (leftTopCorner+rightTopCorner) ");
        } else if ((leftBottomCorner + rightBottomCorner) >= contentWidth) {
            throw new IllegalArgumentException("it's must width > (leftBottomCorner+rightBottomCorner) ");
        } else if ((leftTopCorner + leftBottomCorner) >= contentHeight) {
            throw new IllegalArgumentException("it's must height > (leftTopCorner+leftBottomCorner) ");
        } else if ((rightTopCorner + rightBottomCorner) >= contentHeight) {
            throw new IllegalArgumentException("it's must height > (rightTopCorner+rightBottomCorner) ");
        }

        this.mLeftTopCorner = leftTopCorner * 2;
        this.mLeftBottomCorner = leftBottomCorner * 2;
        this.mRightTopCorner = rightTopCorner * 2;
        this.mRightBottomCorner = rightBottomCorner * 2;

        if (isHasShadow && 0 >= shadowSize) {
            shadowSize = 1;
        }
        shadowSize *= 2;
        this.mShadowSize = shadowSize;
        this.mIsHasShadow = isHasShadow;

        mRectFSource = new RectF(0, 0, contentWidth, contentHeight);

        mWidth = (int) (contentWidth + this.mShadowSize);
        mHeight = (int) (contentHeight + this.mShadowSize);
//        setBounds(0, 0, mWidth, mHeight);
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
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (mIsColor) {
            mIsColor = false;
            initSize((int) (bounds.width() - this.mShadowSize), (int) (bounds.height() - this.mShadowSize),
                    this.mLeftTopCorner, this.mLeftBottomCorner, this.mRightTopCorner, this.mRightBottomCorner, this.mShadowSize, this.mIsHasShadow);

        }
    }

    @Override
    public void draw(Canvas canvas) {


        drawContent(canvas);

    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawContent(canvas);
        return bitmap;
    }


    private void drawContent(Canvas canvas) {
        if (mIsHasShadow) {
            drawShadow(canvas, mLeftTopCorner,mLeftBottomCorner,
                    mRightTopCorner, mRightBottomCorner, mShadowSize, mShadowStartColor, mShadowEndColor);
            canvas.translate(mShadowSize / 2, mShadowSize / 2);
        }
        drawBitmap(canvas, mLeftTopCorner, mLeftBottomCorner, mRightTopCorner, mRightBottomCorner);
    }

    private void drawShadow(Canvas canvas, float leftTopCorner, float leftBottomCorner,
          float rightTopCorner, float rightBottomCorner, float shadowSize, int shadowStartColor, int shadowEndColor) {

        float startRatio;
        Shader gradientShader;
        RectF outerRectF = new RectF();
        RectF innerRectF = new RectF();
        RectF roundRectF = new RectF();
        Paint shadowPaint = new Paint();
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setDither(true);

        /**
         * cornor
         */
        //leftTopCorner
        startRatio = leftTopCorner / (leftTopCorner + shadowSize);
        gradientShader = new RadialGradient(leftTopCorner / 2, leftTopCorner / 2, (leftTopCorner + shadowSize) / 2,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, startRatio, 1f}
                , Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        innerRectF.set(0, 0, leftTopCorner, leftTopCorner);
        outerRectF.set(innerRectF);
        outerRectF.inset(-shadowSize / 2, -shadowSize / 2);
        mShadowPath.setFillType(Path.FillType.EVEN_ODD);
        mShadowPath.arcTo(outerRectF, 180f, 90f, false);
        mShadowPath.arcTo(innerRectF, 270f, -90f, false);

        canvas.save();
        canvas.translate(mRectFSource.left + shadowSize / 2, mRectFSource.top + shadowSize / 2);
        canvas.drawPath(mShadowPath, shadowPaint);
        canvas.restore();

        //rightTopCorner
        mShadowPath.reset();
        startRatio = rightTopCorner / (rightTopCorner + shadowSize);
        gradientShader = new RadialGradient(rightTopCorner / 2, rightTopCorner / 2, (rightTopCorner + shadowSize) / 2,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, startRatio, 1f}
                , Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        innerRectF.set(0, 0, rightTopCorner, rightTopCorner);
        outerRectF.set(innerRectF);
        outerRectF.inset(-shadowSize / 2, -shadowSize / 2);
        mShadowPath.setFillType(Path.FillType.EVEN_ODD);
        mShadowPath.arcTo(outerRectF, 270, 90f, false);
        mShadowPath.arcTo(innerRectF, 0f, -90f, false);

        canvas.save();
        canvas.translate(mRectFSource.right - rightTopCorner + shadowSize / 2, mRectFSource.top + shadowSize / 2);
        canvas.drawPath(mShadowPath, shadowPaint);
        canvas.restore();

        //leftBottomCorner
        mShadowPath.reset();
        startRatio = leftBottomCorner / (leftBottomCorner + shadowSize);
        gradientShader = new RadialGradient(leftBottomCorner / 2, leftBottomCorner / 2, (leftBottomCorner + shadowSize) / 2,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, startRatio, 1f}
                , Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        innerRectF.set(0, 0, leftBottomCorner, leftBottomCorner);
        outerRectF.set(innerRectF);
        outerRectF.inset(-shadowSize / 2, -shadowSize / 2);
        mShadowPath.setFillType(Path.FillType.EVEN_ODD);
        mShadowPath.arcTo(outerRectF, 90, 90f, false);
        mShadowPath.arcTo(innerRectF, 180f, -90f, false);

        canvas.save();
        canvas.translate(mRectFSource.left + shadowSize / 2, mRectFSource.bottom - leftBottomCorner + shadowSize / 2);
        canvas.drawPath(mShadowPath, shadowPaint);
        canvas.restore();

        //rightBottomCorner
        mShadowPath.reset();
        startRatio = rightBottomCorner / (rightBottomCorner + shadowSize);
        gradientShader = new RadialGradient(rightBottomCorner / 2, rightBottomCorner / 2, (rightBottomCorner + shadowSize) / 2,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, startRatio, 1f}
                , Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        innerRectF.set(0, 0, rightBottomCorner, rightBottomCorner);
        outerRectF.set(innerRectF);
        outerRectF.inset(-shadowSize / 2, -shadowSize / 2);
        mShadowPath.setFillType(Path.FillType.EVEN_ODD);
        mShadowPath.arcTo(outerRectF, 0, 90f, false);
        mShadowPath.arcTo(innerRectF, 90f, -90f, false);

        canvas.save();
        canvas.translate(mRectFSource.right - rightBottomCorner + shadowSize / 2,
                mRectFSource.bottom - rightBottomCorner + shadowSize / 2);
        canvas.drawPath(mShadowPath, shadowPaint);
        canvas.restore();

        /**
         * edge
         */
        //horizontal top
        mShadowPath.reset();
        gradientShader = new LinearGradient(0, shadowSize, 0, 0,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        roundRectF.set(0, 0, mRectFSource.width() - (leftTopCorner + rightTopCorner) / 2, shadowSize / 2);

        canvas.save();
        canvas.translate(mRectFSource.left + leftTopCorner / 2 + shadowSize / 2, 0);
        canvas.drawRect(roundRectF, shadowPaint);
        canvas.restore();

        //horizontal bottom
        gradientShader = new LinearGradient(0, -shadowSize / 2, 0, shadowSize / 2,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        roundRectF.set(0, 0, mRectFSource.width() - (leftBottomCorner + rightBottomCorner) / 2, shadowSize / 2);

        canvas.save();
        canvas.translate(mRectFSource.left + leftBottomCorner / 2 + shadowSize / 2, mRectFSource.bottom + shadowSize / 2);
        canvas.drawRect(roundRectF, shadowPaint);
        canvas.restore();

        //vertical left
        gradientShader = new LinearGradient(shadowSize, 0, 0, 0,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        roundRectF.set(0, 0, shadowSize / 2, mRectFSource.height() - (leftTopCorner + leftBottomCorner) / 2);

        canvas.save();
        canvas.translate(0, mRectFSource.top + leftTopCorner / 2 + shadowSize / 2);
        canvas.drawRect(roundRectF, shadowPaint);
        canvas.restore();

        //vertical right
        gradientShader = new LinearGradient(-shadowSize / 2, 0, shadowSize / 2, 0,
                new int[]{shadowStartColor, shadowStartColor, shadowEndColor},
                new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP);
        shadowPaint.setShader(gradientShader);

        roundRectF.set(0, 0, shadowSize / 2, mRectFSource.height() - (rightTopCorner + rightBottomCorner) / 2);

        canvas.save();
        canvas.translate(mRectFSource.right + shadowSize / 2, mRectFSource.top + rightTopCorner / 2 + shadowSize / 2);
        canvas.drawRect(roundRectF, shadowPaint);
        canvas.restore();
    }

    private void drawBitmap(Canvas canvas, float leftTopCorner, float leftBottomCorner, float rightTopCorner, float rightBottomCorner) {
        /**
         * corner
         */
        mRectFTarget.set(mRectFSource.left, mRectFSource.top, mRectFSource.left + leftTopCorner, mRectFSource.top + leftTopCorner);
        canvas.drawArc(mRectFTarget, 180, 90, true, mPaint);
        mRectFTarget.set(mRectFSource.right - rightTopCorner, mRectFSource.top, mRectFSource.right, mRectFSource.top + rightTopCorner);
        canvas.drawArc(mRectFTarget, 270, 90, true, mPaint);
        mRectFTarget.set(mRectFSource.left, mRectFSource.bottom - leftBottomCorner, mRectFSource.left + leftBottomCorner, mRectFSource.bottom);
        canvas.drawArc(mRectFTarget, 90, 90, true, mPaint);
        mRectFTarget.set(mRectFSource.right - rightBottomCorner, mRectFSource.bottom - rightBottomCorner, mRectFSource.right, mRectFSource.bottom);
        canvas.drawArc(mRectFTarget, 0, 90, true, mPaint);

        /**
         * path
         */
        //left
        mPath.reset();
        mPath.moveTo(mRectFSource.left, mRectFSource.top + leftTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.left + leftTopCorner / 2 + offset, mRectFSource.top + leftTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.left + leftBottomCorner / 2 + offset, mRectFSource.bottom - leftBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left, mRectFSource.bottom - leftBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left, mRectFSource.top + leftTopCorner / 2 - offset);
        canvas.drawPath(mPath, mPaint);
        //top
        mPath.reset();
        mPath.moveTo(mRectFSource.left + leftTopCorner / 2 - offset, mRectFSource.top);
        mPath.lineTo(mRectFSource.right - rightTopCorner / 2 + offset, mRectFSource.top);
        mPath.lineTo(mRectFSource.right - rightTopCorner / 2 + offset, mRectFSource.top + rightTopCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left + leftTopCorner / 2 - offset, mRectFSource.top + leftTopCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left + leftTopCorner / 2 - offset, mRectFSource.top);
        canvas.drawPath(mPath, mPaint);
        //right
        mPath.reset();
        mPath.moveTo(mRectFSource.right - rightTopCorner / 2 - offset, mRectFSource.top + rightTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right, mRectFSource.top + rightTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right, mRectFSource.bottom - rightBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.right - rightBottomCorner / 2 - offset, mRectFSource.bottom - rightBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.right - rightTopCorner / 2 - offset, mRectFSource.top + rightTopCorner / 2 - offset);
        canvas.drawPath(mPath, mPaint);
        //bottom
        mPath.reset();
        mPath.moveTo(mRectFSource.left + leftBottomCorner / 2 - offset, mRectFSource.bottom - leftBottomCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right - rightBottomCorner / 2 + offset, mRectFSource.bottom - rightBottomCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right - rightBottomCorner / 2 + offset, mRectFSource.bottom);
        mPath.lineTo(mRectFSource.left + leftBottomCorner / 2 - offset, mRectFSource.bottom);
        mPath.lineTo(mRectFSource.left + leftBottomCorner / 2 - offset, mRectFSource.bottom - leftBottomCorner / 2 - offset);
        canvas.drawPath(mPath, mPaint);
        //center
        mPath.reset();
        mPath.moveTo(mRectFSource.left + leftTopCorner / 2 - offset, mRectFSource.top + leftTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right - rightTopCorner / 2 + offset, mRectFSource.top + rightTopCorner / 2 - offset);
        mPath.lineTo(mRectFSource.right - rightBottomCorner / 2 + offset, mRectFSource.bottom - rightBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left + leftBottomCorner / 2 - offset, mRectFSource.bottom - leftBottomCorner / 2 + offset);
        mPath.lineTo(mRectFSource.left + leftTopCorner / 2 - offset, mRectFSource.top + leftTopCorner / 2 - offset);
        canvas.drawPath(mPath, mPaint);

        mPath.reset();
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
        return PixelFormat.OPAQUE;
    }
}
