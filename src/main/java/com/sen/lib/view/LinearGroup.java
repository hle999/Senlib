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

    private int width;
    private int height;

    private List<Integer> mCacheWidthArray;
    private List<CacheViewInfo> mCacheViewInfoArray;

    public LinearGroup(Context context) {
        super(context);
    }

    public LinearGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mCacheWidthArray = new ArrayList<Integer>();
        mCacheViewInfoArray = new ArrayList<CacheViewInfo>();
    }

    public void reset(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addCacheViewInfo(int index, View view) {
        mCacheViewInfoArray.add(new CacheViewInfo(index, view));
    }

    public CacheViewInfo getCacheViewInfo(View view) {
        for (CacheViewInfo cacheViewInfo : mCacheViewInfoArray) {
            if (cacheViewInfo != null && cacheViewInfo.view == view) {
                return cacheViewInfo;
            }
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (width == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(width,
                    resolveSizeAndState(height, heightMeasureSpec, 0));
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalWidth = 0 + getPaddingLeft();
        /*for (int i = 0;i < getChildCount(); i++){
            View view = getChildAt(i);
            if (view.getMeasuredWidth() == 0) {
                view.measure(getMeasuredWidth(), getMeasuredHeight());
            }
            int right = totalWidth + view.getMeasuredWidth();
            view.layout(totalWidth, getPaddingTop(),
                    right, getPaddingTop() + view.getMeasuredHeight());
            totalWidth = right;
        }*/
        for (CacheViewInfo cacheViewInfo : mCacheViewInfoArray) {
            if (cacheViewInfo.view != null) {
                if (cacheViewInfo.view.getMeasuredWidth() == 0) {
                    cacheViewInfo.view.measure(getMeasuredWidth(), getMeasuredHeight());
                }
                if (mCacheViewInfoArray.size() > cacheViewInfo.index) {
                    int left = getPaddingLeft()+mCacheWidthArray.get(cacheViewInfo.index);
                    cacheViewInfo.view.layout(left, getPaddingTop(),
                            left + cacheViewInfo.view.getMeasuredWidth(),
                            getPaddingTop() + cacheViewInfo.view.getMeasuredHeight());
                }
            }
        }
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        clear();
    }

    private void clear() {
        if (mCacheViewInfoArray != null) {
            mCacheViewInfoArray.clear();
        }
        if (mCacheWidthArray != null) {
            mCacheWidthArray.clear();
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

        public CacheViewInfo(int index, View view) {
            this.index = index;
            this.view = view;
        }
    }
}
