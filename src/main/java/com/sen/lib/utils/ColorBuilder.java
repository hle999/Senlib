package com.sen.lib.utils;

import android.graphics.Color;

/**
 * Created by Administrator on 15-4-22.
 */
public class ColorBuilder {

    public static int createGrey(float percen) {
        int lf = (int)(0xf * percen);
        int hf = lf;
        hf = hf << 4;
        int v = hf + lf;
        return rgb(v, v, v);
    }

    public static int rgb(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

}
