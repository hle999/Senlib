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
public class HorizontalScrollWidget extends HorizontalScrollView {

    private static final int UN_INVALUE = -1;
    private static final int MAX_INCREASE_NUM = 2;

    private LinearGroup mLinearGroup;
    private BaseItemAdapter lastAdapter;
    private BaseItemAdapter adapter;


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
        this.adapter = adatper;
        notifyDataChange();
    }

    public BaseItemAdapter getAdpater() {
        return adapter;
    }

    public ViewGroup getItemGroup() {
        return mLinearGroup;
    }

    protected void notifyDataChange() {
        if (getWidth() == 0) {
            post(new Runnable() {
                @Override
                public void run() {
                    resetSize();
                }
            });
        } else {
            resetSize();
        }
    }

    private void init() {
        mLinearGroup = new LinearGroup(getContext());
        mLinearGroup.setOrientation(LinearGroup.HORIZONTAL);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mLinearGroup, layoutParams);
    }

    protected void resetSize() {
        if (mLinearGroup != null) {
            if (adapter != null) {
                adapter.setRoot(this);
                if (lastAdapter != adapter) {
                    lastAdapter = adapter;
                    int x = 0;
                    int leaveWidth = getWidth();
                    int maxHeight = 0;
                    mLinearGroup.removeAllViews();
                    mLinearGroup.setIncreaseNum(MAX_INCREASE_NUM);
                    for (int i = 0; i < adapter.getCount(); i++) {
                        View itemView = adapter.getView(null, mLinearGroup, i);
                        itemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                        if (itemView.getMeasuredHeight() > maxHeight) {
                            maxHeight = itemView.getMeasuredHeight();
                        }
                        if (leaveWidth > 0) {
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        } else {
                            if (mLinearGroup.getIncreaseNum() > 0) {
                                mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                                mLinearGroup.addCacheViewInfo(i, itemView);
                                addItem(itemView);
                            }
                        }
                        mLinearGroup.addCacheToLocal(x);
                        leaveWidth -= itemView.getMeasuredWidth();
                        x += itemView.getMeasuredWidth();
                    }
                    mLinearGroup.reset(x, maxHeight);
                    mLinearGroup.requestLayout();
                } else {
                    int x = 0;
                    int leaveWidth = getWidth();
                    if (mLinearGroup != null) {
                        x = mLinearGroup.getCacheLocalArray().get(mLinearGroup.getCacheLocalArray().size() - 1);
                        View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                        lastItemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                        x += lastItemView.getMeasuredWidth();
                        for (int i = 0; i < adapter.getCount(); i++) {
                            if (mLinearGroup.getCacheLocalArray().size() > i) {

                            } else {
                                View itemView = adapter.getView(null, mLinearGroup, i);
                                itemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                                if (leaveWidth > x) {
                                    mLinearGroup.addCacheViewInfo(i, itemView);
                                    addItem(itemView);
                                } else {
                                    if (mLinearGroup.getIncreaseNum() > 0) {
                                        mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                                        mLinearGroup.addCacheViewInfo(i, itemView);
                                        addItem(itemView);
                                    }
                                }
                                mLinearGroup.addCacheToLocal(x);
                                x += itemView.getMeasuredWidth();
                            }
                        }
                        mLinearGroup.reset(x, mLinearGroup.getCacheHeight());
                        mLinearGroup.requestLayout();
                    }

                }
            }
        }
    }

    private void addItem(View item) {
        if (mLinearGroup != null) {
            mLinearGroup.addView(item);
        }
    }

    public void removeItem(View item) {
        if (mLinearGroup != null) {
            mLinearGroup.removeView(item);
        }
    }

    public void clearAllItems() {
        if (mLinearGroup != null) {
            mLinearGroup.removeAllViews();
        }
    }

    @Override
    public void onScrollChanged(int mScrollX, int mScrollY, int oldX, int oldY) {
        super.onScrollChanged(mScrollX, mScrollY, oldX, oldY);
        if (mLinearGroup != null) {
            if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
                if (mScrollY > oldY) {
                    computeToEnd(mScrollY);
                } else if (oldY > mScrollY) {
                    computeToHead(mScrollY);
                }
            } else {
                if (mScrollX > oldX) {
                    computeToEnd(mScrollX);
                } else if (oldX > mScrollX){
                    computeToHead(mScrollX);
                }
            }
        }
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        lastAdapter = null;
    }

    public View getItemView(int index) {
        if (mLinearGroup != null && mLinearGroup.getCacheViewInfoArray() != null) {
            for (LinearGroup.CacheViewInfo cacheViewInfo : mLinearGroup.getCacheViewInfoArray()) {
                if (cacheViewInfo.index == index) {
                    return cacheViewInfo.view;
                }
            }
        }
        return null;
    }

    private void computeToEnd(int local) {
        if (mLinearGroup != null && mLinearGroup.getCacheLocalArray() != null) {
            List<Integer> cacheLocalArray = mLinearGroup.getCacheLocalArray();
            int newLocal = local + getWidth();
            int index = UN_INVALUE;
            for (Integer l : cacheLocalArray) {
                if (newLocal >= l) {
                    index = cacheLocalArray.indexOf(l);
                }
            }
            if (index != UN_INVALUE) {
                if (mLinearGroup != null && cacheLocalArray != null) {
                    LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.
                            getCacheViewInfoArray().get(mLinearGroup.getCacheViewInfoArray().size() - 1);
                    if (lastCacheViewInfo != null) {
                        if (lastCacheViewInfo.index + 1 == index) {
                            if (cacheLocalArray.size() > index) {
                                addItemToEnd(index);
                            }
                        } else {
                            index++;
                            if (lastCacheViewInfo.index + 1 == index) {
                                if (cacheLocalArray.size() > index) {
                                    addItemToEnd(index);
                                }
                            }
                        }
                    }


                }
            }
        }

    }

    private void computeToHead(int local) {
        if (mLinearGroup != null && mLinearGroup.getCacheLocalArray() != null) {
            List<Integer> cacheLocalArray = mLinearGroup.getCacheLocalArray();
            int index = UN_INVALUE;
            for (Integer l : cacheLocalArray) {
                if (local >= l) {
                    index = cacheLocalArray.indexOf(l);
                }
            }
            if (index != UN_INVALUE) {
                index--;
                if (mLinearGroup != null && cacheLocalArray != null
                        && cacheLocalArray.size() > index && index >= 0) {
                    LinearGroup.CacheViewInfo firstCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(0);
                    if (firstCacheViewInfo != null) {
                        if (firstCacheViewInfo.index - 1 == index) {
                            addItemToHead(index);
                        }
                    }
                }
            }
        }
    }

    private void addItemToEnd(int addIndex) {
        LinearGroup.CacheViewInfo firstCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(0);
        mLinearGroup.getCacheViewInfoArray().remove(0);
        if (firstCacheViewInfo != null) {
            firstCacheViewInfo.needMeasured = true;
            firstCacheViewInfo.index = addIndex;
            firstCacheViewInfo.view = adapter.getView(firstCacheViewInfo.view, mLinearGroup, addIndex);
            mLinearGroup.getCacheViewInfoArray().add(firstCacheViewInfo);
            mLinearGroup.linearLayout(firstCacheViewInfo, mLinearGroup.getCacheLocalArray().get(addIndex));
        }
    }

    private void addItemToHead(int addIndex) {
        LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(mLinearGroup.getCacheViewInfoArray().size() - 1);
        mLinearGroup.getCacheViewInfoArray().remove(mLinearGroup.getCacheViewInfoArray().size() - 1);
        if (lastCacheViewInfo != null) {
            lastCacheViewInfo.needMeasured = true;
            lastCacheViewInfo.index = addIndex;
            lastCacheViewInfo.view = adapter.getView(lastCacheViewInfo.view, mLinearGroup, addIndex);
            mLinearGroup.getCacheViewInfoArray().add(0, lastCacheViewInfo);
            mLinearGroup.linearLayout(lastCacheViewInfo, mLinearGroup.getCacheLocalArray().get(addIndex));
        }
    }
}
