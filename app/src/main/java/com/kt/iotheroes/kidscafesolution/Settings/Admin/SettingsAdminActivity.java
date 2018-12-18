package com.kt.iotheroes.kidscafesolution.Settings.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Splash.SplashActivity;
import com.kt.iotheroes.kidscafesolution.Util.IoTMakers.IoTMakersAPI;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;

public class SettingsAdminActivity extends AppCompatActivity implements AdminSettingsFragment.OnLogoutFragmentInteractionListener{
    private static final String TAG = SettingsAdminActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);
        Log.i(getString(R.string.activity), TAG + "on Create");
    }

    @Override
    public void onLogoutFragmentInteractionListener(final SharedPreferences.OnSharedPreferenceChangeListener changeListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PrefManager.getInstance().removeChangeListener(changeListener);
                new IoTMakersAPI.PushSessionDeleteTask().execute();

                Intent intent = new Intent(SettingsAdminActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 0);
    }
}