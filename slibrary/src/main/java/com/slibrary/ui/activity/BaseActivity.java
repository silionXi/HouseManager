package com.slibrary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.slibrary.utils.logger.Logger;


/**
 * BaseActivity,所有Activity继承该Activity
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(this, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(this, "onDestroy");
    }
}
