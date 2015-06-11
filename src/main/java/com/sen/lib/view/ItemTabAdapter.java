package com.sen.lib.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 15-4-20.
 */

public abstract class ItemTabAdapter extends BaseItemAdapter{

    public abstract void onScroll(int position, float positionOffset);

    public abstract void onScrolledStateChange(int state);


}
