package com.sen.lib.graphics;

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

    public static int createGrey(int startColor, int endColor, float percen) {
        int red = Color.red(startColor) + (int) ((Color.red(startColor) - Color.red(endColor)) * percen);
        int green = Color.green(startColor) + (int) ((Color.green(startColor) - Color.green(endColor)) * percen);
        int blue = Color.blue(startColor) + (int) ((Color.blue(startColor) - Color.blue(endColor)) * percen);
        return rgb(red, green, blue);
    }

    public static int rgb(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

}
