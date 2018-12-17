package com.kt.iotheroes.kidscafesolution.Settings.Parent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kt.iotheroes.kidscafesolution.R;

public class SettingsParentActivity extends AppCompatActivity {
    private static final String TAG = SettingsParentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_parent);
        Log.i(getString(R.string.activity), TAG + "on Create");
    }
}