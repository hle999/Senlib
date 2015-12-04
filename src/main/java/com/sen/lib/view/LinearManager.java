package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Sen on 2015/6/11.
 */
class LinearManager implements IDataObserver {

    private static final int UN_INVALUE = -1;
    private static final int MAX_INCREASE_NUM = 2;

    private LinearGroup mLinearGroup;
    private BaseItemAdapter lastAdapter;
    private BaseItemAdapter adapter;

    private int originWidth;
    private int originHeight;

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
        if (this.adapter != null) {
            this.adapter.setDataObserver(this);
        } else {
            if (mLinearGroup != null) {
                mLinearGroup.removeAllViews();
                mLinearGroup.reset(originWidth, originHeight);
            }
            lastAdapter = null;
        }
        notifyDataChange();
    }

    public BaseItemAdapter getAdpater() {
        return adapter;
    }

    public int getOriginWidth() {
        return originWidth;
    }

    public int getOriginHeight() {
        return originHeight;
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

    public void setOriginSize(int width, int height) {
        if (mLinearGroup != null) {
            originWidth = width;
            originHeight = height;
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

    @Override
    public void notifyDataChange() {
        if (mLinearGroup != null) {
            if (((View)mLinearGroup.getParent()).getHeight() == 0) {
                mLinearGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        resetSize();
                    }
                });
            } else {
                resetSize();
            }
        }
    }

    @Override
    public void notifyDataAll() {
        if (mLinearGroup != null) {
            if (((View)mLinearGroup.getParent()).getHeight() == 0) {
                mLinearGroup.post(new Runnable() {
                    @Override
                    public void run() {
                        resetAllSize();
                    }
                });
            } else {
                resetAllSize();
            }
        }
    }

    private void resetSize() {
        if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
            resetVerticalSize();
        } else {
            resetHorizontalSize();
        }
    }

    private void resetAllSize() {
        if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
            resetVerticalAllSize();
        } else {
            resetHorizontalAllSize();
        }
    }

    private void resetHorizontalAllSize() {
        if (mLinearGroup != null && adapter != null) {
            int x = 0;
            int leaveWidth = originWidth;
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
                    if (!mLinearGroup.isCacheViewIndexExists(i)) {
                        mLinearGroup.addCacheViewInfo(i, itemView);
                        addItem(itemView);
                    }
                } else {
                    if (mLinearGroup.getIncreaseNum() > 0) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        }
                    }
                }
                mLinearGroup.addCacheToLocal(x);
                leaveWidth -= itemView.getMeasuredWidth();
                x += itemView.getMeasuredWidth();
            }
            mLinearGroup.reset(x, maxHeight);
            mLinearGroup.requestLayout();
        }
    }

    private void resetVerticalAllSize() {
        if (mLinearGroup != null && adapter != null) {
            int y = 0;
            int leaveHeight = originHeight;
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
                    if (!mLinearGroup.isCacheViewIndexExists(i)) {
                        mLinearGroup.addCacheViewInfo(i, itemView);
                        addItem(itemView);
                    }
                } else {
                    if (mLinearGroup.getIncreaseNum() > 0) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        }
                    }
                }
                mLinearGroup.addCacheToLocal(y);
                leaveHeight -= itemView.getMeasuredHeight();
                y += itemView.getMeasuredHeight();
            }
            mLinearGroup.reset(maxWidth, y);
            mLinearGroup.requestLayout();
        }
    }

    private void resetHorizontalSize() {
        if (mLinearGroup != null) {
            if (adapter != null) {
//                adapter.setDataObserver((IDataObserver) mLinearGroup.getParent());
                if (lastAdapter != adapter
                        || mLinearGroup.getCacheLocalArray() != null
                        && (mLinearGroup.getCacheLocalArray().size() == 0
                        || mLinearGroup.getCacheLocalArray().size() != adapter.getCount())) {
                    if (lastAdapter != adapter && lastAdapter != null) {
                        lastAdapter.setDataObserver(null);
                    }
                    lastAdapter = adapter;
                    resetHorizontalAllSize();
                } else {
                    changeHorizontalSize();
                    /*int x = 0;
                    int leaveWidth = originWidth;
                    int maxHeight = originWidth;
                    if (mLinearGroup != null) {
                        if (adapter.getCount() > mLinearGroup.getCacheLocalArray().size()) {
                            x = mLinearGroup.getCacheLocalArray().get(mLinearGroup.getCacheLocalArray().size() - 1);
                            View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                            lastItemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
                            x += lastItemView.getMeasuredWidth();
                            for (int i = mLinearGroup.getCacheLocalArray().size(); i < adapter.getCount(); i++) {
                                if (mLinearGroup.getCacheLocalArray().size() > i) {

                                } else {
                                    View itemView = adapter.getView(null, mLinearGroup, i);
                                    itemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
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
                        } else {
                            x = mLinearGroup.getCacheLocalArray().get(adapter.getCount() - 1);
                            View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                            lastItemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
                            x += lastItemView.getMeasuredWidth();
                            int cacheNum = mLinearGroup.getCacheLocalArray().size();
                            for (int i = 0;i<(cacheNum - adapter.getCount());i++) {
                                try {
                                    mLinearGroup.getCacheLocalArray().
                                            remove(mLinearGroup.getCacheLocalArray().size() - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        mLinearGroup.reset(x, maxHeight);
                        mLinearGroup.requestLayout();
                    }*/

                }
            }
        }
        if (onLinearManagerListener != null) {
            onLinearManagerListener.onResetSize();
        }
    }

    private void changeHorizontalSize() {
        if (mLinearGroup != null && adapter != null) {
            int x = 0;
            int maxHeight = originHeight;
            int leaveWidth = originWidth;
            if (mLinearGroup.getCacheLocalArray() != null) {
                mLinearGroup.getCacheLocalArray().clear();
            }
            int cacheViewNum = mLinearGroup.getCacheViewInfoArray().size();
            if (cacheViewNum > adapter.getCount()) {
                for (int i = 0;i<(cacheViewNum - adapter.getCount());i++) {
                    try {
                        LinearGroup.CacheViewInfo cacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(
                                mLinearGroup.getCacheViewInfoArray().size() - 1);
                        if (cacheViewInfo != null) {
                            if (cacheViewInfo.view != null) {

                                mLinearGroup.removeView(cacheViewInfo.view);
                            }
                            mLinearGroup.getCacheViewInfoArray().remove(cacheViewInfo);
                        }
                        if (MAX_INCREASE_NUM > mLinearGroup.getIncreaseNum()) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() + 1);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
            LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(
                    mLinearGroup.getCacheViewInfoArray().size() - 1);
            int lastItemViewIndex = -1;
            if (lastCacheViewInfo != null) {
                lastItemViewIndex = lastCacheViewInfo.index;
            }
            for (int i = 0; i < adapter.getCount(); i++) {
                View itemView = adapter.getView(null, mLinearGroup, i);
                itemView.measure(mLinearGroup.getMeasuredWidth(), mLinearGroup.getMeasuredHeight());
                if (itemView.getMeasuredHeight() > maxHeight) {
                    maxHeight = itemView.getMeasuredHeight();
                }
                if (leaveWidth > 0) {
                    if (i > lastItemViewIndex) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                            if (MAX_INCREASE_NUM > mLinearGroup.getIncreaseNum()) {
                                mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() + 1);
                            }
                        }
                    }
                } else {
                    if (mLinearGroup.getIncreaseNum() > 0) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        }
                    }
                }
                mLinearGroup.addCacheToLocal(x);
                if (leaveWidth > 0) {
                    leaveWidth -= itemView.getMeasuredWidth();
                }
                x += itemView.getMeasuredWidth();
            }
            mLinearGroup.reset(x, maxHeight);
            mLinearGroup.requestLayout();
        }
    }

    private void resetVerticalSize() {
        if (mLinearGroup != null) {
            if (adapter != null) {
//                adapter.setDataObserver((IDataObserver) mLinearGroup.getParent());
                if (lastAdapter != adapter
                        || mLinearGroup.getCacheLocalArray() != null
                        && (mLinearGroup.getCacheLocalArray().size() == 0
                        || mLinearGroup.getCacheLocalArray().size() != adapter.getCount())) {
                    if (lastAdapter != adapter && lastAdapter != null) {
                        lastAdapter.setDataObserver(null);
                    }
                    lastAdapter = adapter;
                    resetVerticalAllSize();
                } else {
                    changeVerticalSize();
                    /*int y = 0;
                    int leaveHeight = originHeight;
                    int maxWidth = originWidth;
                    if (mLinearGroup != null) {
                        if (adapter.getCount() > mLinearGroup.getCacheLocalArray().size()) {
                            y = mLinearGroup.getCacheLocalArray().get(mLinearGroup.getCacheLocalArray().size() - 1);
                            View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                            lastItemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
                            y += lastItemView.getMeasuredHeight();
                            for (int i = mLinearGroup.getCacheLocalArray().size(); i < adapter.getCount(); i++) {
                                if (mLinearGroup.getCacheLocalArray().size() > i) {

                                } else {
                                    View itemView = adapter.getView(null, mLinearGroup, i);
                                    itemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());

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
                        } else {
                            y = mLinearGroup.getCacheLocalArray().get(adapter.getCount() - 1);
                            View lastItemView = adapter.getView(null, mLinearGroup, mLinearGroup.getCacheLocalArray().size() - 1);
                            lastItemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
                            y += lastItemView.getMeasuredHeight();
                            int cacheNum = mLinearGroup.getCacheLocalArray().size();
                            for (int i = 0;i<(cacheNum - adapter.getCount());i++) {
                                try {
                                    mLinearGroup.getCacheLocalArray().
                                            remove(mLinearGroup.getCacheLocalArray().size() - 1);
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        mLinearGroup.reset(maxWidth, y);
                        mLinearGroup.requestLayout();

                    }*/

                }
            }
        }
        if (onLinearManagerListener != null) {
            onLinearManagerListener.onResetSize();
        }
    }

    private void changeVerticalSize() {
        if (mLinearGroup != null && adapter != null) {
            int y = 0;
            int leaveHeight = originHeight;
            int maxWidth = originWidth;
            if (mLinearGroup.getCacheLocalArray() != null) {
                mLinearGroup.getCacheLocalArray().clear();
            }
            int cacheViewNum = mLinearGroup.getCacheViewInfoArray().size();
            if (cacheViewNum > adapter.getCount()) {
                for (int i = 0;i<(cacheViewNum - adapter.getCount());i++) {
                    try {
                        LinearGroup.CacheViewInfo cacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(
                                mLinearGroup.getCacheViewInfoArray().size() - 1);
                        if (cacheViewInfo != null) {
                            if (cacheViewInfo.view != null) {

                                mLinearGroup.removeView(cacheViewInfo.view);
                            }
                            mLinearGroup.getCacheViewInfoArray().remove(cacheViewInfo);
                        }
                        if (MAX_INCREASE_NUM > mLinearGroup.getIncreaseNum()) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() + 1);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
            LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(
                    mLinearGroup.getCacheViewInfoArray().size() - 1);
            int lastItemViewIndex = -1;
            if (lastCacheViewInfo != null) {
                lastItemViewIndex = lastCacheViewInfo.index;
            }
            for (int i = 0; i < adapter.getCount(); i++) {
                View itemView = adapter.getView(null, mLinearGroup, i);
                itemView.measure(mLinearGroup.getWidth(), mLinearGroup.getHeight());
                if (itemView.getMeasuredWidth() > maxWidth) {
                    maxWidth = itemView.getMeasuredWidth();
                }

                if (leaveHeight > 0) {
                    if (i > lastItemViewIndex) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                            if (MAX_INCREASE_NUM > mLinearGroup.getIncreaseNum()) {
                                mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() + 1);
                            }
                        }
                    }
                } else {
                    if (mLinearGroup.getIncreaseNum() > 0) {
                        if (!mLinearGroup.isCacheViewIndexExists(i)) {
                            mLinearGroup.setIncreaseNum(mLinearGroup.getIncreaseNum() - 1);
                            mLinearGroup.addCacheViewInfo(i, itemView);
                            addItem(itemView);
                        }
                    }
                }

                mLinearGroup.addCacheToLocal(y);
                if (leaveHeight > 0) {
                    leaveHeight -= itemView.getMeasuredHeight();
                }
                y += itemView.getMeasuredHeight();
            }
            mLinearGroup.reset(maxWidth, y);
            mLinearGroup.requestLayout();
        }
    }

    private void computeToEnd(int local) {
        if (mLinearGroup != null && mLinearGroup.getCacheLocalArray() != null) {
            List<Integer> cacheLocalArray = mLinearGroup.getCacheLocalArray();
            /*int height = 0;
            if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
                height = ((View)mLinearGroup.getParent()).getHeight();
            } else {
                height = ((View)mLinearGroup.getParent()).getWidth();
            }
            local += height;*/
            int index = UN_INVALUE;
            if (mLinearGroup.getOrientation() == LinearGroup.VERTICAL) {
                for (Integer l : cacheLocalArray) {
                    if ((local + originHeight) >= l) {
                        index = cacheLocalArray.indexOf(l);
                    } else {
                        break;
                    }
                }
            } else {
                for (Integer l : cacheLocalArray) {
                    if ((local + originWidth) >= l) {
                        index = cacheLocalArray.indexOf(l);
                    } else {
                        break;
                    }
                }
            }


            if (index != UN_INVALUE) {
                if (mLinearGroup != null && cacheLocalArray != null) {
                    LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.
                            getCacheViewInfoArray().get(mLinearGroup.getCacheViewInfoArray().size() - 1);
                    if (lastCacheViewInfo != null) {
                        int lastCacheViewIndex = lastCacheViewInfo.index;
                        if (index == lastCacheViewIndex && (cacheLocalArray.size() - 1) > index) {
                            index++;
                        }
                        for (int i = 1;(index - lastCacheViewIndex) >= i;i++) {
                            if (cacheLocalArray.size() > lastCacheViewIndex + i) {
                                addItemToEnd(lastCacheViewIndex + i);
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
                    } else {
                        break;
                    }
                }
            } else {
                index = 0;
            }
            if (index != UN_INVALUE) {
                if (mLinearGroup != null && cacheLocalArray.size() > index && index >= 0) {
                    LinearGroup.CacheViewInfo firstCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(0);
                    if (firstCacheViewInfo != null) {
                        int firstCacheViewIndex = firstCacheViewInfo.index;
                        if (index == firstCacheViewIndex && index > 0) {
                            index--;
                        }
                        for (int i = 1; (firstCacheViewIndex - index) >= i;i++) {
                            if (cacheLocalArray.size() > firstCacheViewIndex - i && firstCacheViewIndex - i > UN_INVALUE) {
                                addItemToHead(firstCacheViewIndex - i);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addItemToEnd(int addIndex) {
        if (mLinearGroup != null && !mLinearGroup.isCacheViewIndexExists(addIndex)) {
            LinearGroup.CacheViewInfo firstCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(0);
//        mLinearGroup.getCacheViewInfoArray().remove(0);
            if (firstCacheViewInfo != null) {
                mLinearGroup.removeCacheViewInfo(0);
//            firstCacheViewInfo.needMeasured = true;
                firstCacheViewInfo.index = addIndex;
                firstCacheViewInfo.view = adapter.getView(firstCacheViewInfo.view, mLinearGroup, addIndex);
//            mLinearGroup.getCacheViewInfoArray().add(firstCacheViewInfo);
                mLinearGroup.addCacheViewInfo(mLinearGroup.getCacheViewInfoArray().size(), firstCacheViewInfo);
                mLinearGroup.linearLayout(firstCacheViewInfo, mLinearGroup.getCacheLocalArray().get(addIndex));
            }
        }
    }

    private void addItemToHead(int addIndex) {
        if (mLinearGroup != null && !mLinearGroup.isCacheViewIndexExists(addIndex)) {
            LinearGroup.CacheViewInfo lastCacheViewInfo = mLinearGroup.getCacheViewInfoArray().get(mLinearGroup.getCacheViewInfoArray().size() - 1);
//        mLinearGroup.getCacheViewInfoArray().remove(mLinearGroup.getCacheViewInfoArray().size() - 1);
            if (lastCacheViewInfo != null) {
                mLinearGroup.removeCacheViewInfo(mLinearGroup.getCacheViewInfoArray().size() - 1);
//            lastCacheViewInfo.needMeasured = true;
                lastCacheViewInfo.index = addIndex;
                lastCacheViewInfo.view = adapter.getView(lastCacheViewInfo.view, mLinearGroup, addIndex);
//            mLinearGroup.getCacheViewInfoArray().add(0, lastCacheViewInfo);
                mLinearGroup.addCacheViewInfo(0, lastCacheViewInfo);
                mLinearGroup.linearLayout(lastCacheViewInfo, mLinearGroup.getCacheLocalArray().get(addIndex));
            }
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
