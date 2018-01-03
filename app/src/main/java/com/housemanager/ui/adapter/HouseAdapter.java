package com.housemanager.ui.adapter;

import android.content.Context;

import com.housemanager.domain.SelectHouse;
import com.housemanager.ui.holder.HouseHolder;
import com.slibrary.ui.adapter.BaseListAdapter;
import com.slibrary.ui.holder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2018/1/3.
 */

public class HouseAdapter extends BaseListAdapter<SelectHouse.NewestBean> {
    private Context mContext;
    public HouseAdapter(Context context, List<SelectHouse.NewestBean> list) {
        super(context, list);
        mContext = context;
    }

    @Override
    public BaseHolder<SelectHouse.NewestBean> getHolder(int position) {
        return new HouseHolder(mContext);
    }

    @Override
    public ArrayList<SelectHouse.NewestBean> onLoadMore() {
        return null;
    }
}
