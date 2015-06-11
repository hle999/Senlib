package com.sen.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Black_Horse on 2015/6/9.
 */
public class LinearGroup extends ViewGroup {

    public static final int VERTICAL = 0x01;
    public static final int HORIZONTAL = 0x02;

    private int orientation = HORIZONTAL;
    private int mCacheWidth;
    private int mCacheHeight;
    private int increaseNum;

    private List<Integer> mCacheXArray;
    private List<CacheViewInfo> mCacheViewInfoArray;

    public int getIncreaseNum() {
        return increaseNum;
    }

    public void setIncreaseNum(int increaseNum) {
        this.increaseNum = increaseNum;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getCacheWidth() {
        return mCacheWidth;
    }

    public int getCacheHeight() {
        return mCacheHeight;
    }

    public LinearGroup(Context context) {
        super(context);
        init();
    }

    public LinearGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mCacheXArray = new ArrayList<Integer>();
        mCacheViewInfoArray = new ArrayList<CacheViewInfo>();
    }

    public void reset(int width, int height) {
        this.mCacheWidth = width + getPaddingLeft() + getPaddingRight();
        this.mCacheHeight = height + getPaddingTop() + getPaddingBottom();
    }

    public void addCacheViewInfo(int index, View view) {
        CacheViewInfo cacheViewInfo = new CacheViewInfo(index, view);
        cacheViewInfo.needMeasured = false;
        mCacheViewInfoArray.add(cacheViewInfo);
    }

    public void addCacheToLocal(int local) {
        mCacheXArray.add(local);
    }

    public CacheViewInfo getCacheViewInfo(View view) {
        for (CacheViewInfo cacheViewInfo : mCacheViewInfoArray) {
            if (cacheViewInfo != null && cacheViewInfo.view == view) {
                return cacheViewInfo;
            }
        }
        return null;
    }

    public List<Integer> getCacheLocalArray() {
        if (mCacheXArray != null) {
            return mCacheXArray;
        }
        return null;
    }

    public List<CacheViewInfo> getCacheViewInfoArray() {
        if (mCacheViewInfoArray != null) {
            return mCacheViewInfoArray;
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mCacheWidth == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(mCacheWidth,
                    resolveSizeAndState(mCacheHeight, heightMeasureSpec, 0));
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (CacheViewInfo cacheViewInfo : mCacheViewInfoArray) {
            if (cacheViewInfo.view != null) {
                if (mCacheXArray.size() > cacheViewInfo.index) {
                    linearLayout(cacheViewInfo, mCacheXArray.get(cacheViewInfo.index));
                }
            }
        }
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        clear();
    }

    public void linearLayout(CacheViewInfo cacheViewInfo, int local) {
        if (orientation == VERTICAL) {
            verticalLinearLayout(cacheViewInfo, local);
        } else {
            horizontalLinearLayout(cacheViewInfo, local);
        }
    }

    private void horizontalLinearLayout(CacheViewInfo cacheViewInfo, int x) {
        if (cacheViewInfo != null) {
            if (cacheViewInfo.needMeasured) {
                cacheViewInfo.needMeasured = false;
                cacheViewInfo.view.measure(getMeasuredWidth(), getMeasuredHeight());
            }
            x = getPaddingLeft() + x;
            cacheViewInfo.view.layout(x, getPaddingTop(),
                    x + cacheViewInfo.view.getMeasuredWidth(),
                    getPaddingTop() + cacheViewInfo.view.getMeasuredHeight());
        }
    }

    private void verticalLinearLayout(CacheViewInfo cacheViewInfo, int y) {
        if (cacheViewInfo != null) {
            if (cacheViewInfo.needMeasured) {
                cacheViewInfo.needMeasured = false;
                cacheViewInfo.view.measure(getMeasuredWidth(), getMeasuredHeight());
            }
            y = getPaddingTop() + y;
            cacheViewInfo.view.layout(getPaddingLeft(), y,
                    getPaddingLeft() + cacheViewInfo.view.getMeasuredWidth(),
                    y + cacheViewInfo.view.getMeasuredHeight());
        }
    }

    private void clear() {
        if (mCacheViewInfoArray != null) {
            mCacheViewInfoArray.clear();
        }
        if (mCacheXArray != null) {
            mCacheXArray.clear();
        }
    }

    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize =  MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result | (childMeasuredState&MEASURED_STATE_MASK);
    }

    public class CacheViewInfo {
        public int index;
        public View view;
        protected boolean needMeasured = false;

        public CacheViewInfo(int index, View view) {
            this.index = index;
            this.view = view;
        }
    }
}
