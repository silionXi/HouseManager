package com.housemanager.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.housemanager.R;
import com.housemanager.ui.fragment.CommunityFragment;
import com.housemanager.ui.fragment.HouseFragment;
import com.slibrary.ui.activity.BaseActivity;
import com.slibrary.ui.fragment.LoadingFragment;
import com.slibrary.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by silion on 2017/12/29.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tvSelelctHouse)
    TextView mTvSelelctHouse;
    @BindView(R.id.ivLogin)
    ImageView mIvLogin;
    @BindView(R.id.tvCommunity)
    TextView mTvCommunity;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private MainPagerAdapter mPagerAdapter;
    private TextView mActionBarTitle;

    ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                mActionBarTitle.setText(R.string.house_select_online);
                mTvSelelctHouse.setSelected(true);
                mTvCommunity.setSelected(false);
            } else {
                mActionBarTitle.setText(R.string.house_community);
                mTvSelelctHouse.setSelected(false);
                mTvCommunity.setSelected(true);
            }
            ((LoadingFragment) mPagerAdapter.getItem(position)).loadData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.setStatusBarVisibility(getWindow(), true);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        initActionBar();
        initViewPager();
    }

    private void initViewPager() {
        mPagerAdapter = new MainPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                mPageChangeListener.onPageSelected(0);
            }
        });
    }

    private void initActionBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.view_actionbar);

        View v = actionBar.getCustomView();
        mActionBarTitle = v.findViewById(R.id.tvTitle);
        if (v != null) {
            ViewGroup.LayoutParams lp = v.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            v.setLayoutParams(lp);
        }
    }

    @OnClick({R.id.tvSelelctHouse, R.id.ivLogin, R.id.tvCommunity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSelelctHouse:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ivLogin:
                break;
            case R.id.tvCommunity:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    class MainPagerAdapter extends FragmentPagerAdapter {
        private SparseArray<Fragment> mFragments = new SparseArray<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(position);
        }

        private Fragment createFragment(int position) {
            Fragment fragment = mFragments.get(position);
            if (fragment == null) {
                switch (position) {
                    case 0:
                        fragment = new HouseFragment();
                        break;
                    case 1:
                        fragment = new CommunityFragment();
                        break;
                    default:
                }
                mFragments.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
