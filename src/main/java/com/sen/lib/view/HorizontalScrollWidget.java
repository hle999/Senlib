package com.sen.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import java.util.List;

/**
 * Created by Sen on 2015/6/10.
 */
public class HorizontalScrollWidget extends HorizontalScrollView implements LinearManager.OnLinearManagerListener, IWidgetBean{

    private LinearManager linearManager;

    public HorizontalScrollWidget(Context context) {
        super(context);
    }

    public HorizontalScrollWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollWidget(Context context, AttributeSet attrs, int defStyle) {
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

    @Override
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
    }

    private void init() {
        linearManager = new LinearManager(this, LinearGroup.HORIZONTAL);
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
