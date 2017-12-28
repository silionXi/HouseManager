package com.housemanager.global;

import android.app.Application;

import com.slibrary.utils.logger.Logger;

/**
 * Created by silion on 2017/12/27.
 */

public class HouseApplication extends Application {
    private static HouseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        // 打印日志初始化
        Logger.init();
    }

    public static HouseApplication getApplication() {
        return mApplication;
    }
}
