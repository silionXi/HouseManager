package com.housemanager.ui.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.housemanager.R;
import com.housemanager.domain.SelectHouse;
import com.housemanager.global.GlobalData;
import com.slibrary.ui.holder.BaseHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by silion on 2018/1/3.
 */

public class HouseBrandHolder extends BaseHolder<List<SelectHouse.BrandBean>> {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvMore)
    TextView mTvMore;
    @BindView(R.id.content)
    FrameLayout mContent;

    public HouseBrandHolder(Context context) {
        super(context);
    }

    @Override
    public View initView(Context context) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.listheader_house, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void refreshView(List<SelectHouse.BrandBean> data) {
        mTvTitle.setText("品牌公寓馆");
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_house_brand, mContent, false);
        LinearLayout llBrand = rootView.findViewById(R.id.llBrand);
        for (SelectHouse.BrandBean bean : data) {
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_house_brand, llBrand, false);
            ImageView ivHouse = item.findViewById(R.id.ivHouse);
            String url = GlobalData.BASE_HOST + bean.getImage();
            Glide.with(mContext).load(url).into(ivHouse);

            TextView tvDesc = item.findViewById(R.id.tvDesc);
            tvDesc.setText(bean.getDesc());

            llBrand.addView(item);
        }
        RelativeLayout.LayoutParams contentLp = (RelativeLayout.LayoutParams) mContent.getLayoutParams();
        contentLp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        contentLp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        mContent.addView(rootView);
    }
}
