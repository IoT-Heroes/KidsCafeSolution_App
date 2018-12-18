package com.kt.iotheroes.kidscafesolution.Settings.Parent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Splash.SplashActivity;
import com.kt.iotheroes.kidscafesolution.Util.IoTMakers.IoTMakersAPI;

public class SettingsParentActivity extends AppCompatActivity implements ParentSettingsFragment.OnLogoutFragmentInteractionListener {
    private static final String TAG = SettingsParentActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_parent);
        Log.i(getString(R.string.activity), TAG + "on Create");
    }

    @Override
    public void onLogoutFragmentInteractionListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new IoTMakersAPI.PushSessionDeleteTask().execute();

                Intent intent = new Intent(SettingsParentActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}