package com.housemanager.global;

import android.app.Application;

/**
 * Created by silion on 2017/12/27.
 */

public class HouseApplication extends Application {
    private static HouseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
    }

    public static HouseApplication getApplication() {
        return mApplication;
    }
}
