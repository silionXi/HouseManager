package com.slibrary.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.slibrary.R;
import com.slibrary.utils.logger.Logger;

/**
 * Created by silion on 2017/12/28.
 * <p>
 * 如果需要请求权限，直接继承该Activity
 */

public abstract class PermissionActivity extends BaseActivity {
    private final static String[] REQUEST_PERMISSION = new String[]{
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.BODY_SENSORS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void requstAllPermission(final int requestCode) {
        requestPermission(REQUEST_PERMISSION, requestCode);
    }

    public void requestPermission(final String[] permissons, final int requestCode) {
        ActivityCompat.requestPermissions(PermissionActivity.this, permissons, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handlerResult(requestCode, permissions, grantResults);
    }

    public abstract void handlerResult(int requestCode, String[] permissions, int[] grantResults);
}
