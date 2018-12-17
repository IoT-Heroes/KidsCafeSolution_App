package com.kt.iotheroes.kidscafesolution.Settings.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kt.iotheroes.kidscafesolution.R;

public class SettingsAdminActivity extends AppCompatActivity {
    private static final String TAG = SettingsAdminActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);
        Log.i(getString(R.string.activity), TAG + "on Create");
    }
}