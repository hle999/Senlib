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
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 15-4-21.
 */
public class HorizontalItemTab extends HorizontalScrollView implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final int UNINVALUE = -1;

    private int tabHeight = 2;
    private int tabIndex = 0;
    private int tabWidth = 0;
    private int selectItemIndex = 0;

    private float positionPercent = 0;

    private Paint tabPaint = null;

    public int getSelectItemIndex() {
        return selectItemIndex;
    }

    private void setSelectItemIndex(int selectItemIndex) {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (itemTabAdpater != null && viewGroup != null && viewGroup.getChildCount() > selectItemIndex) {
            itemTabAdpater.getView(viewGroup.getChildAt(getSelectItemIndex()), viewGroup, getSelectItemIndex(), selectItemIndex);
            itemTabAdpater.getView(viewGroup.getChildAt(selectItemIndex), viewGroup, selectItemIndex, selectItemIndex);
        }
        this.selectItemIndex = selectItemIndex;
    }

    private ItemTabAdpater itemTabAdpater;

    public ItemTabAdpater getAdpater() {
        return itemTabAdpater;
    }

    public void setAdpater(ItemTabAdpater itemTabAdpater) {
        this.itemTabAdpater = itemTabAdpater;
        reset();
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
        if (tabPaint != null) {
            tabPaint.setColor(color);
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

        tabPaint = new Paint();
        tabPaint.setColor(Color.BLACK);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(linearLayout, layoutParams);

    }

    public void disableTouchScroll() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    protected void reset() {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (viewGroup != null) {
            if (itemTabAdpater != null) {
                itemTabAdpater.setRoot(this);
                if (getTag() != itemTabAdpater.toString()) {
                    for (int i = 0; i < itemTabAdpater.getCount(); i++) {
                        addItem(itemTabAdpater.getView(null, viewGroup, i, 0));
                    }
                    setTag(itemTabAdpater.toString());
                } else {
                    for (int i = 0; i < itemTabAdpater.getCount(); i++) {
                        View childView = viewGroup.getChildAt(i);
                        if (childView != null) {
                            itemTabAdpater.getView(childView, viewGroup, i, getSelectItemIndex());
                        } else {
                            addItem(itemTabAdpater.getView(childView, viewGroup, i, getSelectItemIndex()));
                        }
                    }
                }
            }
        }
    }

    private void addItem(View item) {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (viewGroup != null && item != null) {
            viewGroup.addView(item);
        }
    }

    public void clearAllItems() {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    public void resetTab(float positionPercent, int tabIndex) {
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        if (viewGroup != null && tabIndex > 0 && (viewGroup.getChildCount() - 1) > tabIndex) {
            View currentItem = viewGroup.getChildAt(tabIndex - 1);
            float currentLeft = currentItem.getLeft();
            float currentWidth = currentItem.getWidth();
            scrollTo((int) (currentLeft + currentWidth * positionPercent), 0);
        }
        this.positionPercent = positionPercent;
        this.tabIndex = tabIndex;
        invalidate();

    }

    public View getItemView(int index) {
        if (getChildAt(0) instanceof ViewGroup) {
            if (((ViewGroup) getChildAt(0)).getChildCount() > index) {
                return ((ViewGroup) getChildAt(0)).getChildAt(index);
            }
        }
        return null;
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
            canvas.drawRect(currentLeft, getHeight() - tabHeight, currentRight, getHeight(), tabPaint);

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (itemTabAdpater != null) {
            itemTabAdpater.onScroll(position, positionOffset);
        }
        resetTab(positionOffset, position);
    }

    @Override
    public void onPageSelected(int position) {
        if (getSelectItemIndex() != position) {
            setSelectItemIndex(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (itemTabAdpater != null) {
            itemTabAdpater.onScrolledStateChange(state);
        }
        /*if (ViewPager.SCROLL_STATE_IDLE == state) {
            this.direction = UNINVALUE;
        }*/
    }
}
