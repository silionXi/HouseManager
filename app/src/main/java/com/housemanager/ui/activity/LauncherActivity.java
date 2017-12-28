package com.housemanager.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.housemanager.R;
import com.slibrary.ui.activity.BaseActivity;

/**
 * Created by silion on 2017/12/28.
 */

public class LauncherActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
    }
}
