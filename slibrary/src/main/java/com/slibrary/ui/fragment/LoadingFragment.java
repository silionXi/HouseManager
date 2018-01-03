package com.slibrary.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.slibrary.R;
import com.slibrary.ui.widget.LoadingPage;

import java.util.List;

/**
 * 框架：
 * 1. LoadingPage封装
 * @see LoadingPage
 * 2. BaseAdapter封装
// * @see BaseListAdapter
 * 3. BaseHolder封装
// * @see BaseHolder
 */
public abstract class LoadingFragment extends Fragment {

	private LoadingPage mLoadingPage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		Context context = getActivity();
		mLoadingPage = new LoadingPage(context) {

			@Override
			public View onCreateSuccessView() {
				return LoadingFragment.this.onCreateSuccessView();
			}

			@Override
			public ResultState onLoad() {
				return LoadingFragment.this.onLoad();
			}

		};
		return mLoadingPage;
	}

	// 由子类实现创建布局的方法
	public abstract View onCreateSuccessView();

	// 由子类实现加载网络数据的逻辑, 该方法运行在子线程
	public abstract LoadingPage.ResultState onLoad();

	// 开始加载网络数据
	public void loadData() {
		if (mLoadingPage != null) {
			mLoadingPage.loadData();
		}
	}

	/**
	 * 校验数据的合法性,返回相应的状态
	 * @param data
	 * @return
	 */
	public LoadingPage.ResultState check(Object data) {
		if (data != null) {
			if (data instanceof List) {//判断当前对象是否是一个集合
				List list = (List) data;
				if (!list.isEmpty()) {//数据不为空,访问成功
					return LoadingPage.ResultState.STATE_SUCCESS;
				} else {//空集合
					return LoadingPage.ResultState.STATE_EMPTY;
				}
			}
		}

		return LoadingPage.ResultState.STATE_ERROR;
	}

}
