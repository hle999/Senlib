package com.sen.lib.graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgc on 2015/7/22.
 */
public class TransitionalColor {

    /**
     * 输入一个颜色,将它拆成三个部分:
     * 红色,绿色和蓝色
     */
    private static int[] retrieveRGBComponent(int color)
    {
        int[] rgb = new int[3];
        rgb[0] = color >> 16;
        rgb[1] = (color >> 8) & 0xff;
        rgb[2] = color & 0xff;
        return rgb;
    }

    /**
     * 红色,绿色和蓝色三色组合
     */
    private static int generateFromRGBComponent(int[] rgb)
    {
        if( rgb == null) {
            return 0xFFFFFF;
        }
        return rgb[0] << 16 | rgb[1] << 8 | rgb[2];
    }

    /**
     * startColor是浅色，endColor是深色，实现渐变
     * steps是指在多大的区域中渐变,
     */
    public static List<Integer> generateTransitionalColor(int startColor, int endColor, int steps)
    {
        List<Integer> colors = new ArrayList<Integer>();
        colors.add(startColor);
        if( steps < 3 ) {
            colors.add(endColor);
            return colors;
        }

        int[] color1RGB = retrieveRGBComponent(startColor);
        int[] color2RGB = retrieveRGBComponent(endColor);
        steps = steps - 2;

        int redDiff = color2RGB[0] - color1RGB[0];
        int greenDiff = color2RGB[1] - color1RGB[1];
        int blueDiff = color2RGB[2] - color1RGB[2];
        for(int i = 1; steps >= i; i++)
        {
            int[] tmpRGB = {
                    color1RGB[0] + redDiff * i / steps,
                    color1RGB[1] + greenDiff * i / steps,
                    color1RGB[2] + blueDiff * i / steps
            };
            colors.add(generateFromRGBComponent(tmpRGB));
        }
        colors.add(endColor);

        return colors;
    }

}
