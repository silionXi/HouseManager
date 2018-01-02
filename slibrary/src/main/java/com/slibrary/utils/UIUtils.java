package com.slibrary.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by silion on 2017/12/29.
 */

public class UIUtils {

    /**
     * 设置是否显示状态栏(即是否全屏显示)
     * @param window {@link Activity#getWindow()}
     * @param visibility
     */
    public static void setStatusBarVisibility(Window window, boolean visibility) {
        if (window == null) {
            return;
        }

        if (visibility) {
            // 显示状态栏，非全屏
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            // 不显示状态栏，即全屏
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
