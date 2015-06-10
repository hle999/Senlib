package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 15-4-20.
 */

public abstract class HorizontalItemTabAdapter extends BaseItemAdapter{

    private HorizontalScrollWidget root;

    public abstract void onScroll(int position, float positionOffset);

    public abstract void onScrolledStateChange(int state);

    @Override
    public void setRoot(View root) {
        if (root instanceof HorizontalScrollWidget) {
            this.root = (HorizontalScrollWidget)root;
        }
    }

    public void notifyDataChange() {
        if (root != null) {
            root.notifyDataChange();
        }
    }
}
