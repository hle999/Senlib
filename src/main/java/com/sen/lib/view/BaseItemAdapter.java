package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sen on 2015/6/10.
 */
public abstract class BaseItemAdapter {

    private IDataObserver dataObserver;

    public abstract View getView(View v, ViewGroup container, int postion);

    public abstract int getCount();

    public void notifyDataChange() {
        if (dataObserver != null) {
            dataObserver.notifyDataChange();
        }
    }

    public void notifyDataAll() {
        if (dataObserver != null) {
            dataObserver.notifyDataAll();
        }
    }

    public void setDataObserver(IDataObserver dataObserver) {
        this.dataObserver = dataObserver;
    }
}
