package com.housemanager.http;

import android.content.Context;

import com.housemanager.domain.SelectHouse;
import com.slibrary.http.BaseProtocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silion on 2018/1/3.
 */

public class SelectHouseProtocol extends BaseProtocol<SelectHouse> {
    public SelectHouseProtocol(Context context) {
        super(context);
    }

    @Override
    public String getKey() {
        return "selecthouse";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public SelectHouse parseJson(String result) {
        try {
            JSONObject selectHouseJO = new JSONObject(result);
            SelectHouse selectHouse = new SelectHouse();

            JSONArray bannerJA = selectHouseJO.getJSONArray("banner");
            List<SelectHouse.BannerBean> bannerBeans = new ArrayList<>();
            for (int i = 0; i < bannerJA.length(); i++) {
                JSONObject beanJO = bannerJA.getJSONObject(i);
                if (beanJO != null) {
                    SelectHouse.BannerBean bean = new SelectHouse.BannerBean();
                    bean.setImage(beanJO.getString("image"));
                    bannerBeans.add(bean);
                }
            }
            selectHouse.setBanner(bannerBeans);

            JSONArray brandJA = selectHouseJO.getJSONArray("brand");
            List<SelectHouse.BrandBean> brandBeans = new ArrayList<>();
            for (int i = 0; i < brandJA.length(); i++) {
                JSONObject beanJO = brandJA.getJSONObject(i);
                if (beanJO != null) {
                    SelectHouse.BrandBean bean = new SelectHouse.BrandBean();
                    bean.setImage(beanJO.getString("image"));
                    bean.setDesc(beanJO.getString("desc"));
                    brandBeans.add(bean);
                }
            }
            selectHouse.setBrand(brandBeans);

            JSONArray newestJA = selectHouseJO.getJSONArray("newest");
            List<SelectHouse.NewestBean> newestBeans = new ArrayList<>();
            for (int i = 0; i < newestJA.length(); i++) {
                JSONObject beanJO = newestJA.getJSONObject(i);
                if (beanJO != null) {
                    SelectHouse.NewestBean bean = new SelectHouse.NewestBean();
                    bean.setImage(beanJO.getString("image"));
                    bean.setRoom(beanJO.getString("room"));
                    bean.setPrice(beanJO.getString("price"));
                    newestBeans.add(bean);
                }
            }
            selectHouse.setNewest(newestBeans);

            return selectHouse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
