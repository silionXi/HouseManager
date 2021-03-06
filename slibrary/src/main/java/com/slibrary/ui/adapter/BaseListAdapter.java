package com.slibrary.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.slibrary.manager.ThreadManager;
import com.slibrary.ui.holder.BaseHolder;
import com.slibrary.ui.holder.MoreHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据适配器的基类
 * <p>
 * 封装后使用该基类只需实现2个方法：
 * 1. 传入BaseHolder子类
 *
 * @author Kevin
 * @see #getHolder(int)
 * 2. 加载更多数据
 * @see #onLoadMore()
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    private static final int ITEM_LORD_MORE = 0;// 更多布局类型
    private static final int ITEM_LIST_VIEW = 1;// 普通布局类型

    private Context mContext;
    private List<T> list;

    private boolean isLoadMore = false;// 标记当前是否正在加载更多

    public BaseListAdapter(Context context, List<T> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.size() + 1;// 加载更多布局也占一个位置,所以数量加1
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 子类可以再重写该方法修改Type Count
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;// 两种布局类型,一种普通布局,一种加载更多的布局
    }

    // 获取集合大小
    public int getListSize() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            // 最后一个条目
            return ITEM_LORD_MORE;
        } else {
            // 返回普通布局类型
            return getInnerType(position);
        }
    }

    // 普通布局也有可能返回多种类型, 重写改方法,可以返回更多普通布局的类型
    public int getInnerType(int position) {
        return ITEM_LIST_VIEW;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        // 1. 封装成初始化布局
        if (convertView == null) {
            // 根据当前item的类型来初始化不同的Holder对象
            if (getItemViewType(position) == ITEM_LORD_MORE) {
                // 返回加载更多Holder对象
                // 因为所有界面加载更多的UI显示效果都是一致的,所以加载更多的业务逻辑可以做详细处理
                holder = new MoreHolder(mContext, hasMore());
            } else {
                // 在初始化holder的同时,已经对布局进行了加载,也给view设置了tag
                holder = getHolder(position);
            }
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        // 2. 封装成给各个控件设置数据
        if (getItemViewType(position) != ITEM_LORD_MORE) {
            // 刷新界面,更新数据
            holder.setData(getItem(position));
        } else {
            // 如果当前展示的是加载更多的布局,需要开始加载下一页数据
            MoreHolder moreHolder = (MoreHolder) holder;
            if (moreHolder.getData() == MoreHolder.TYPE_HAS_MORE) {// 加载之前要判断是否有更多数据
                loadMore(moreHolder);
            }
        }

        return holder.getRootView();
    }

    // 返回BaseHolder的子类,必须实现
    public abstract BaseHolder<T> getHolder(int position);

    // 返回是否需要加载更多数据, 子类可以重写该方法
    public boolean hasMore() {
        return true;
    }

    /**
     * 加载更多数据
     */
    public void loadMore(final MoreHolder holder) {
        if (!isLoadMore) {
            isLoadMore = true;
            ThreadManager.getThreadPool().execute(new Runnable() {

                @Override
                public void run() {
                    // 获取更多数据
                    final ArrayList<T> moreList = onLoadMore();

                    if (moreList == null) {// 如果没有拿到数据,说明加载失败
                        holder.setData(MoreHolder.TYPE_LOAD_ERROR);
                    } else {
                        // 每页返回20条数据,如果发现获取数据小于20条,说明已经没有更多数据了
                        if (moreList.size() < 20) {
                            holder.setData(MoreHolder.TYPE_NO_MORE);
                        } else {
                            holder.setData(MoreHolder.TYPE_HAS_MORE);
                        }

                        // 将下一页的数据追加到当前集合当中
                        list.addAll(moreList);
                        notifyDataSetChanged();
                    }

                    isLoadMore = false;

                }
            });
        }
    }

    // 加载更多数据,必须由子类实现
    public abstract ArrayList<T> onLoadMore();

}
