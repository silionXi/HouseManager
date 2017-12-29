package com.housemanager.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.housemanager.R;
import com.slibrary.ui.activity.PermissionActivity;

/**
 * Created by silion on 2017/12/28.
 */

public class LauncherActivity extends PermissionActivity {
    private final static int REQUEST_PERMISSION_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);
        requstAllPermission(REQUEST_PERMISSION_CODE);
    }

    @Override
    public void handlerResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i =0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    finish();
                    return;
                }
            }

            startMainActivity();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
