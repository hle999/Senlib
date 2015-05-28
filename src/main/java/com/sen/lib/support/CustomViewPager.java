package com.sen.lib.support;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-4-20.
 */
public class CustomViewPager extends ViewPager {

    private boolean scrollAble = true;

    private List<ViewPager.OnPageChangeListener> listenerList;

    public boolean isScrollAble() {
        return scrollAble;
    }

    public void setScrollAble(boolean scrollAble) {
        this.scrollAble = scrollAble;
    }

    public void addPagerChangeListener(ViewPager.OnPageChangeListener listener) {
        if (listenerList == null) {
            listenerList = new ArrayList<ViewPager.OnPageChangeListener>();
        }
        listenerList.add(listener);
    }

    public CustomViewPager(Context context) {
        super(context);
        setOnPageChangeListener();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener();
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollAble) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!scrollAble) {
            return false;
        } else {
            boolean result;
            try {
                result = super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                result = false;
            }
            return result;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (listenerList != null) {
            listenerList.clear();
        }
        listenerList = null;
    }

    public boolean checkInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_CANCEL) {

        }else if(ev.getAction() == MotionEventCompat.ACTION_POINTER_DOWN && super.onInterceptTouchEvent(ev)) {
            return false;
        }else if(ev.getAction() == MotionEventCompat.ACTION_POINTER_UP && super.onInterceptTouchEvent(ev)){
            return false;
        }else if(ev.getAction() == MotionEvent.ACTION_DOWN && super.onInterceptTouchEvent(ev)){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void setOnPageChangeListener() {
        super.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (listenerList != null) {
                    for (ViewPager.OnPageChangeListener listener : listenerList) {
                        listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (listenerList != null) {
                    for (ViewPager.OnPageChangeListener listener : listenerList) {
                        listener.onPageSelected(position);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (listenerList != null) {
                    for (ViewPager.OnPageChangeListener listener : listenerList) {
                        listener.onPageScrollStateChanged(state);
                    }
                }
            }
        });
    }
}
