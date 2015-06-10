package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Sen on 2015/6/10.
 */
public abstract class BaseItemAdapter {

    public abstract View getView(View v, ViewGroup container, int postion);

    public abstract int getCount();

    public abstract void notifyDataChange();

    public abstract void setRoot(View root);
}
