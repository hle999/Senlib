package com.sen.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by Sen on 2015/6/11.
 */
public class VerticalScrollWidget extends ScrollView implements LinearManager.OnLinearManagerListener/*, IWidgetBean*/ {

    private LinearManager linearManager;

    public VerticalScrollWidget(Context context) {
        super(context);
    }

    public VerticalScrollWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalScrollWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setAdapter(BaseItemAdapter adatper) {
        if (linearManager != null) {
            linearManager.setAdapter(adatper);
        }
    }

    public BaseItemAdapter getAdpater() {
        if (linearManager != null) {
            return linearManager.getAdpater();
        }
        return null;
    }

    public ViewGroup getItemGroup() {
        if (linearManager != null) {
            return linearManager.getItemGroup();
        }
        return null;
    }

    public int getOriginWidth() {
        if (linearManager != null) {
            return linearManager.getOriginWidth();
        }
        return 0;
    }

    public int getOriginHeight() {
        if (linearManager != null) {
            return linearManager.getOriginHeight();
        }
        return 0;
    }

    public void clearCacheItemsView() {
        if (linearManager != null) {
            linearManager.clearAllItems();
        }
    }

    /*@Override
    public void notifyDataChange() {
        if (linearManager != null) {
            linearManager.notifyDataChange();
        }
    }

    @Override
    public void notifyDataAll() {
        if (linearManager != null) {
            linearManager.notifyDataAll();
        }
    }*/

    private void init() {
        linearManager = new LinearManager(this, LinearGroup.VERTICAL);
        linearManager.setOnLinearManagerListener(this);
    }

    @Override
    public void onScrollChanged(int mScrollX, int mScrollY, int oldX, int oldY) {
        super.onScrollChanged(mScrollX, mScrollY, oldX, oldY);
        if (linearManager != null) {
            linearManager.onScrollChanged(mScrollX, mScrollY, oldX, oldY);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (linearManager != null) {
            if (width > (getPaddingLeft() + getPaddingRight())
                    && height > (getPaddingTop() + getPaddingBottom())) {
                linearManager.setOriginSize(width - getPaddingLeft() - getPaddingRight(), height - getPaddingTop() - getPaddingBottom());
            } else {
                throw new IllegalStateException("It's can not be (paddingLeft + paddingRihgt) > width or (paddingTop + paddingBottom) > height!");
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (linearManager != null && changed) {
            linearManager.setOriginSize(r - l - getPaddingLeft() - getPaddingRight(), b - t);
        }
    }*/

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        if (linearManager != null) {
            linearManager.destroy();
        }
        linearManager = null;
    }

    public View getItemView(int index) {
        if (linearManager != null) {
            return linearManager.getItemView(index);
        }
        return null;
    }

    @Override
    public void onResetSize() {

    }
}
