package com.sen.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 15-4-21.
 */
public class HorizontalItemTab extends HorizontalScrollWidget implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private int tabHeight = 2;
    private int tabIndex;
    private int tabWidth;
    private int selectItemIndex;
    private float positionPercent;

    private Paint mTabPaint;

    public int getSelectItemIndex() {
        return selectItemIndex;
    }

    private void setSelectItemIndex(int selectItemIndex) {
        LinearGroup mLinearGroup = (LinearGroup)getItemGroup();
        HorizontalItemTabAdapter horizontalItemTabAdapter = (HorizontalItemTabAdapter)getAdpater();
        if (horizontalItemTabAdapter != null && mLinearGroup != null && mLinearGroup.getCacheLocalArray().size() > selectItemIndex) {
            horizontalItemTabAdapter.getView(getItemView(getSelectItemIndex()),
                    mLinearGroup, getSelectItemIndex());
            horizontalItemTabAdapter.getView(getItemView(selectItemIndex),
                    mLinearGroup, selectItemIndex);
        }
        this.selectItemIndex = selectItemIndex;
    }

    public int getTabHeight() {
        return tabHeight;
    }

    public void setTabHeight(int tabHeight) {
        this.tabHeight = tabHeight;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
    }

    public void setTabColor(int color) {
        if (mTabPaint != null) {
            mTabPaint.setColor(color);
        }
    }

    public HorizontalItemTab(Context context) {
        super(context);
    }

    public HorizontalItemTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public HorizontalItemTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setFillViewport(true);
        setWillNotDraw(false);

        mTabPaint = new Paint();
        mTabPaint.setColor(Color.BLACK);
        disableTouchScroll();
    }

    public void disableTouchScroll() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void scrollTab(float positionPercent, int tabIndex) {
        this.positionPercent = positionPercent;
        this.tabIndex = tabIndex;
        LinearGroup mLinearGroup = (LinearGroup)getItemGroup();
        if (mLinearGroup != null && mLinearGroup.getCacheLocalArray() != null && mLinearGroup.getCacheLocalArray().size() > tabIndex) {
            int currentLeft = mLinearGroup.getCacheLocalArray().get(tabIndex);
            int currentWidth = 0;
            if (mLinearGroup.getCacheViewInfoArray() != null) {
                for (LinearGroup.CacheViewInfo cacheViewInfo : mLinearGroup.getCacheViewInfoArray()) {
                    if (cacheViewInfo.index == tabIndex) {
                        currentWidth = cacheViewInfo.view.getMeasuredWidth();
                        break;
                    }
                }
            }
            if (currentWidth != 0) {
                scrollTo((int) (currentLeft + currentWidth * positionPercent), 0);
            }
        }
    }

    @Override
    protected void resetSize() {
        super.resetSize();
        if (getItemGroup() instanceof LinearGroup) {
            final int currentIndexX = ((LinearGroup)getItemGroup()).getCacheLocalArray().get(selectItemIndex);
            if (currentIndexX != getScrollX()) {
                getItemGroup().post(new Runnable() {
                    @Override
                    public void run() {
                        HorizontalItemTab.this.smoothScrollBy(currentIndexX - getScrollX(), 0);
                    }
                });

            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() instanceof Integer) {
            setSelectItemIndex(indexOfChild(v));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (viewGroup != null && viewGroup.getChildCount() > tabIndex) {
            View currentItem = viewGroup.getChildAt(tabIndex);
            float currentLeft = currentItem.getLeft();
            float currentRight = currentItem.getRight();
            int currentItemWidth = (int) (currentRight - currentLeft);
            if (currentItemWidth > tabWidth) {
                int currentMargin = (currentItemWidth - tabWidth) / 2;
                currentLeft = currentLeft + currentMargin;
                currentRight = currentRight - currentMargin;
            }

            if (viewGroup.getChildCount() > (tabIndex + 1)) {
                View nextItem = viewGroup.getChildAt(tabIndex + 1);
                float nextLeft = nextItem.getLeft();
                float nextRight = nextItem.getRight();
                int nextItemWidth = (int) (nextRight - nextLeft);
                if (nextItemWidth > tabWidth) {
                    int nextMargin = (nextItemWidth - tabWidth) / 2;
                    nextLeft = nextLeft + nextMargin;
                    nextRight = nextRight - nextMargin;
                }
                currentLeft = currentLeft + (nextLeft - currentLeft) * positionPercent;
                currentRight = currentRight + (nextRight - currentRight) * positionPercent;
            }
            canvas.drawRect(currentLeft, getHeight() - tabHeight, currentRight, getHeight(), mTabPaint);

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        HorizontalItemTabAdapter horizontalItemTabAdapter = (HorizontalItemTabAdapter)getAdpater();
        if (horizontalItemTabAdapter != null) {
            horizontalItemTabAdapter.onScroll(position, positionOffset);
        }
        scrollTab(positionOffset, position);
    }

    @Override
    public void onPageSelected(int position) {
        if (getSelectItemIndex() != position) {
            setSelectItemIndex(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        HorizontalItemTabAdapter horizontalItemTabAdapter = (HorizontalItemTabAdapter)getAdpater();
        if (horizontalItemTabAdapter != null) {
            horizontalItemTabAdapter.onScrolledStateChange(state);
        }
        /*if (ViewPager.SCROLL_STATE_IDLE == state) {
            this.direction = UNINVALUE;
        }*/
    }
}
