package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 15-4-20.
 */

public abstract class ItemTabAdpater {

    private HorizontalItemTab root;

    public abstract void onScroll(ViewGroup container, int position, float positionOffset);

    public abstract View getView(View v, ViewGroup container, int postion, int selectIndex);

    public abstract int getCount();

    public void setRoot(HorizontalItemTab root) {
        this.root = root;
    }

    public void notifyDataChange() {
        if (root != null) {
            root.reset();
        }
    }
}
