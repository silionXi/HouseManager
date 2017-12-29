package com.slibrary.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by silion on 2017/12/29.
 * 禁止滑动的ViewPager，通过定时器或者Tab切换
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 不拦截touch事件
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 消费事件，但不做任何处理
        return true;
    }
}
