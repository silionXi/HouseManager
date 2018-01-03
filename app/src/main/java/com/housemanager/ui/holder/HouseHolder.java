package com.housemanager.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.housemanager.R;
import com.housemanager.domain.SelectHouse;
import com.housemanager.global.HouseApplication;
import com.slibrary.ui.holder.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by silion on 2018/1/3.
 */

public class HouseHolder extends BaseHolder<SelectHouse.NewestBean> {

    @BindView(R.id.ivHouse)
    ImageView mIvHouse;
    @BindView(R.id.tvRoom)
    TextView mTvRoom;
    @BindView(R.id.tvPrice)
    TextView mTvPrice;

    public HouseHolder(Context context) {
        super(context);
    }

    @Override
    public View initView(Context context) {
        View view = LayoutInflater.from(HouseApplication.getApplication()).inflate(R.layout.listitem_house, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void refreshView(SelectHouse.NewestBean data) {
        mTvRoom.setText(data.getRoom());
        mTvPrice.setText(data.getPrice());
    }
}
