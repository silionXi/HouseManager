package com.housemanager.global;

import android.app.Application;

/**
 * Created by silion on 2017/12/27.
 */

public class HoseApplication extends Application {
    private static HoseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
    }

    public static HoseApplication getApplication() {
        return mApplication;
    }
}
