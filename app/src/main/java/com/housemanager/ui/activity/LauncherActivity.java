package com.housemanager.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.housemanager.R;
import com.slibrary.ui.activity.PermissionActivity;
import com.slibrary.utils.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by silion on 2017/12/28.
 */

public class LauncherActivity extends PermissionActivity {
    private final static int REQUEST_PERMISSION_CODE = 0;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private final int[] mImageIds = new int[]{
            R.drawable.launcher_viewpage_01,
            R.drawable.launcher_viewpage_02,
            R.drawable.launcher_viewpage_03
    };
    @BindView(R.id.skip)
    TextView mSkip;
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO: 2017/12/29 从网络加载图片
            ImageView imageView = new ImageView(LauncherActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mImageIds[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
    private Timer mTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        requstAllPermission(REQUEST_PERMISSION_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
        }
    }

    @Override
    public void handlerResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    finish();
                    return;
                }
            }

            initViewPager();
        }
    }

    private void initViewPager() {
        mViewPager.setAdapter(mPagerAdapter);
        mSkip.setVisibility(View.VISIBLE);
        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Logger.d(LauncherActivity.this, "viewpager update");
                final int position = mViewPager.getCurrentItem();
                if (position == mImageIds.length - 1) {
                    startMainActivity();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(position + 1);
                        }
                    });
                }
            }
        };
        mTimer.schedule(timerTask, 1000, 1000);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.skip)
    public void onViewClicked() {
        startMainActivity();
    }
}
