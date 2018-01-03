package com.slibrary.ui.holder;

import android.content.Context;
import android.view.View;

import com.slibrary.ui.adapter.BaseListAdapter;

/**
 * ViewHolder的基类
 * <p>
 * 此类实现了以下功能
 * 1. 初始化item布局
 * 2. findViewById方法(由子类在初始化布局时实现)
 * 2. 给view设置tag
 * 3. 刷新界面
 * <p>
 * 此类相当于是对{@link BaseListAdapter}getView方法的封装，封装后使用该基类只需实现2个方法：
 * 1. 加载布局已经findViewById
 *
 * @author Kevin
 * @see #initView()
 * 2. 刷新页面
 * @see #refreshView
 * <p>
 * 该基类也可以用于其他非MyBaseAdapter的情况，但是需要手动设置数据
 * @see #setData(Object)
 */
public abstract class BaseHolder<T> {
    protected Context mContext;
    private View mRootView;// item的布局对象
    private T data;// item对应的数据

    public BaseHolder(Context context) {
        mContext = context;
        mRootView = initView(context);// 初始化布局
        mRootView.setTag(this);// 给view设置tag
    }

    // 初始化布局的方法必须由子类实现
    public abstract View initView(Context context);

    // 返回布局对象
    public View getRootView() {
        return mRootView;
    }

    ;

    // 设置数据
    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    // 获取数据
    public T getData() {
        return data;
    }

    // 刷新界面,更新数据,子类必须实现
    public abstract void refreshView(T data);
}
