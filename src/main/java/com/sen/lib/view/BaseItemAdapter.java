package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Sen on 2015/6/10.
 */
public abstract class BaseItemAdapter {

    private IWidgetBean root;

    public abstract View getView(View v, ViewGroup container, int postion);

    public abstract int getCount();

    public void notifyDataChange() {
        if (root != null) {
            root.notifyDataChange();
        }
    }

    public void notifyDataAll() {
        if (root != null) {
            root.notifyDataAll();
        }
    }

    public void setRoot(IWidgetBean root) {
        this.root = root;
    }
}
