package com.housemanager.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.housemanager.domain.SelectHouse;
import com.housemanager.global.HouseApplication;
import com.housemanager.http.SelectHouseProtocol;
import com.housemanager.ui.adapter.HouseAdapter;
import com.housemanager.ui.holder.HouseBannerHolder;
import com.housemanager.ui.holder.HouseBrandHolder;
import com.slibrary.ui.fragment.LoadingFragment;
import com.slibrary.ui.widget.LoadingPage;

import java.util.List;

/**
 * Created by silion on 2018/1/2.
 */

public class HouseFragment extends LoadingFragment {

    private ListView mListView;
    private SelectHouse mSelectHouse;
    private HouseAdapter mHouseAdapter;

    @Override
    public View onCreateSuccessView() {
        Context context = HouseApplication.getApplication();
        mListView = new ListView(context);
        List<SelectHouse.BannerBean> bannerBeans = mSelectHouse.getBanner();
        if (bannerBeans != null && !bannerBeans.isEmpty()) {
            HouseBannerHolder bannerHolder = new HouseBannerHolder(context);
            bannerHolder.setData(bannerBeans);
            mListView.addHeaderView(bannerHolder.getRootView());
        }
        List<SelectHouse.BrandBean> brandBeans = mSelectHouse.getBrand();
        if (brandBeans != null && !brandBeans.isEmpty()) {
            HouseBrandHolder brandHolder = new HouseBrandHolder(context);
            brandHolder.setData(brandBeans);
            mListView.addHeaderView(brandHolder.getRootView());
        }
        mHouseAdapter = new HouseAdapter(HouseApplication.getApplication(), mSelectHouse.getNewest());
        mListView.setAdapter(mHouseAdapter);
        return mListView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        SelectHouseProtocol protocol = new SelectHouseProtocol(HouseApplication.getApplication());
        mSelectHouse = protocol.getData(-1);
        return check(mSelectHouse);
    }

    @Override
    public LoadingPage.ResultState check(Object data) {
        if (data != null) {
            if (data instanceof SelectHouse) {
                SelectHouse selectHouse = (SelectHouse) data;
                if ((selectHouse.getBanner() == null || selectHouse.getBanner().isEmpty()) &&
                        (selectHouse.getBrand() == null || selectHouse.getBrand().isEmpty()) &&
                        (selectHouse.getNewest() == null || selectHouse.getNewest().isEmpty())) {
                    return LoadingPage.ResultState.STATE_EMPTY;
                } else {
                    return LoadingPage.ResultState.STATE_SUCCESS;
                }
            }
        }
        return LoadingPage.ResultState.STATE_ERROR;
    }
}
