package com.kt.iotheroes.kidscafesolution.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, BottomTabActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
