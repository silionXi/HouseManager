package com.housemanager.ui.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.housemanager.R;
import com.housemanager.domain.SelectHouse;
import com.housemanager.global.GlobalData;
import com.slibrary.ui.holder.BaseHolder;
import com.slibrary.utils.MeasureUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by silion on 2018/1/3.
 */

public class HouseBannerHolder extends BaseHolder<List<SelectHouse.BannerBean>> {
    private Context mContext;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.tvMore)
    TextView mTvMore;
    @BindView(R.id.content)
    FrameLayout mContent;
    private int mCurrentPager;
    private Timer mTimer;

    public HouseBannerHolder(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.listheader_house, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void refreshView(final List<SelectHouse.BannerBean> data) {
        mTvTitle.setText("本月最佳公寓");

        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_house_banner, mContent, false);

        final LinearLayout llIndicator = view.findViewById(R.id.llIndicator);
        for (int i = 0; i < data.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout
                    .LayoutParams.WRAP_CONTENT);
            if (i != 0) {
                imageView.setImageResource(R.drawable.shape_circle_normal);
                lp.leftMargin = MeasureUtils.dp2Px(mContext, 5);
            } else {
                imageView.setImageResource(R.drawable.shape_circle_select);
                mCurrentPager = 0;
            }
            llIndicator.addView(imageView, lp);
        }

        final ViewPager viewPager = view.findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                String url = GlobalData.BASE_HOST + data.get(position).getImage();
                Glide.with(mContext).load(url).into(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mCurrentPager != position) {
                    ((ImageView) llIndicator.getChildAt(position)).setImageResource(R.drawable.shape_circle_select);
                    ((ImageView) llIndicator.getChildAt(mCurrentPager)).setImageResource(R.drawable.shape_circle_normal);
                    mCurrentPager = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                int position = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(position % data.size());
                viewPager.postDelayed(this, 1500);
            }
        }, 1500);

        mContent.addView(view);
    }
}
