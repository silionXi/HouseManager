package com.slibrary.utils;

import android.content.Context;

/**
 * Created by silion on 2018/1/4.
 */

public class MeasureUtils {
    /*
     * dp转px
     * 可以参考TypedValue#applyDimension
     */
    public static int dp2Px(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5);
    }
}
