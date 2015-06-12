package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Sen on 2015/6/11.
 */
class LinearManager {

    private static final int UN_INVALUE = -1;
    private static final int MAX_INCREASE_NUM = 2;

    private LinearGroup mLinearGroup;
    private BaseItemAdapter lastAdapter;
    private BaseItemAdapter adapter;

    private OnLinearManagerListener onLinearManagerListener;

    public void setOnLinearManagerListener(OnLinearManagerListener onLinearManagerListener) {
        this.onLinearManagerListener = onLinearManagerListener;
    }

    public LinearManager(View root, int orientation) {
        if (root instanceof ViewGroup) {
            mLinearGroup = new LinearGroup(root.getContext());
            mLinearGroup.setOrientation(orientation);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup)root).addView(mLinearGroup, layoutParams);
        }

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

    public void destroy() {
        clearAllItems();
        adapter = null;
        lastAdapter = null;
        onLinearManagerListener = null;
        mLinearGroup = null;

    }

    public void onScrollChanged(int mScrollX, int mScrollY, int oldX, int oldY) {
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

    private void resetHorizontalSize() {
        if (mLinearGroup != null) {
            if (adapter != null) {
                adapter.setRoot((IWidgetBean)mLinearGroup.getParent());
                if (lastAdapter != adapter) {
                    lastAdapter = adapter;
                    int x = 0;
                    int leaveWidth = ((View)mLinearGroup.getParent()).getWidth();
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
                    int leaveWidth = ((View)mLinearGroup.getParent()).getWidth();
                    int maxHeight = ((View)mLinearGroup.getParent()).getHeight();
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
                                if (itemView.getMeasuredHeight() > maxHeight) {
                                    maxHeight = itemView.getMeasuredHeight();
                                }
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
                        mLinearGroup.reset(x, maxHeight);
                        mLinearGroup.requestLayout();
                    }

                }
            }
        }
        if (onLinearManagerListener != null) {
            onLinearManagerListener.onResetSize();
        }
    }

    private void resetVerticalSize() {
        if (mLinearGroup != null) {
            if (adapter != null) {
                adapter.setRoot((IWidgetBean)mLinearGroup.getParent());
                if (lastAdapter != adapter) {
                    lastAdapter = adapter;
                    int y = 0;
                    int leaveHeight = ((View)mLinearGroup.getParent()).getHeight();
                    int maxWidth = 0;
                    mLinearGroup.removeAllViews();
                    mLinearGroup.setIncreaseNum(MAX_INCREASE_NUM);
                    for (int i = 0; i < adapter.getCount(); i++) {
                        View itemView = adapter.getView(null, mLinearGroup, i);
                        itemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                        if (itemView.getMeasuredWidth() > maxWidth) {
                            maxWidth = itemView.getMeasuredWidth();
                        }
                        if (leaveHeight > 0) {
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        } else {
                            if (mLinearGroup.getIncreaseNum() > 0) {
                                mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                                mLinearGroup.addCacheViewInfo(i, itemView);
                                addItem(itemView);
                            }
                        }
                        mLinearGroup.addCacheToLocal(y);
                        leaveHeight -= itemView.getMeasuredHeight();
                        y += itemView.getMeasuredHeight();
                    }
                    mLinearGroup.reset(maxWidth, y);
                    mLinearGroup.requestLayout();
                } else {
                    int y = 0;
                    int leaveHeight = ((View)mLinearGroup.getParent()).getHeight();
                    int maxWidth = ((View)mLinearGroup.getParent()).getWidth();
                    if (mLinearGroup != null) {
                        y = mLinearGroup.getCacheLocalArray().get(mLinearGroup.getCacheLocalArray().size() - 1);
                        View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                        lastItemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                        y += lastItemView.getMeasuredHeight();
                        for (int i = 0; i < adapter.getCount(); i++) {
                            if (mLinearGroup.getCacheLocalArray().size() > i) {

                            } else {
                                View itemView = adapter.getView(null, mLinearGroup, i);
                                itemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                                if (itemView.getMeasuredWidth() > maxWidth) {
                                    maxWidth = itemView.getMeasuredWidth();
                                }
                                if (leaveHeight > y) {
                                    mLinearGroup.addCacheViewInfo(i, itemView);
                                    addItem(itemView);
                                } else {
                                    if (mLinearGroup.getIncreaseNum() > 0) {
                                        mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                                        mLinearGroup.addCacheViewInfo(i, itemView);
                                        addItem(itemView);
                                    }
                                }
                                mLinearGroup.addCacheToLocal(y);
                                y += itemView.getMeasuredHeight();
                            }
                        }
                        mLinearGroup.reset(maxWidth, y);
                        mLinearGroup.requestLayout();
                    }

                }
            }
        }
        if (onLinearManagerListener != null) {
            onLinearManagerListener.onResetSize();
        }
    }

    protected void notifyDataChange() {
        if (mLinearGroup != null) {
            if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
                if (((View)mLinearGroup.getParent()).getHeight() == 0) {
                    mLinearGroup.post(new Runnable() {
                        @Override
                        public void run() {
                            resetVerticalSize();
                        }
                    });
                } else {
                    resetVerticalSize();
                }
            } else {
                if (((View)mLinearGroup.getParent()).getWidth() == 0) {
                    mLinearGroup.post(new Runnable() {
                        @Override
                        public void run() {
                            resetHorizontalSize();
                        }
                    });
                } else {
                    resetHorizontalSize();
                }
            }
        }
    }

    private void computeToEnd(int local) {
        if (mLinearGroup != null && mLinearGroup.getCacheLocalArray() != null) {
            List<Integer> cacheLocalArray = mLinearGroup.getCacheLocalArray();
            int newLocal = 0;
            if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
                newLocal = local + ((View)mLinearGroup.getParent()).getHeight();
            } else {
                newLocal = local + ((View)mLinearGroup.getParent()).getWidth();
            }
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
                        for (int i = 1;(index - lastCacheViewInfo.index) >= i;i++) {
                            if (cacheLocalArray.size() > lastCacheViewInfo.index + i) {
                                addItemToEnd(lastCacheViewInfo.index + i);
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
            if (local > 0) {
                for (Integer l : cacheLocalArray) {
                    if (local >= l) {
                        index = cacheLocalArray.indexOf(l);
                    }
                }
            } else {
                index = 0;
            }
            if (index != UN_INVALUE) {
                if (mLinearGroup != null && cacheLocalArray != null
                        && cacheLocalArray.size() > index && index >= 0) {
                    LinearGroup.CacheViewInfo firstCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(0);
                    if (firstCacheViewInfo != null) {
                        for (int i = 1; (firstCacheViewInfo.index - index) >= i;i++) {
                            if (cacheLocalArray.size() > firstCacheViewInfo.index - i && firstCacheViewInfo.index - i > UN_INVALUE) {
                                addItemToHead(firstCacheViewInfo.index - i);
                            }
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

    private void addItem(View item) {
        if (mLinearGroup != null) {
            mLinearGroup.addView(item);
        }
    }

    public interface OnLinearManagerListener {
        void onResetSize();
    }

}
