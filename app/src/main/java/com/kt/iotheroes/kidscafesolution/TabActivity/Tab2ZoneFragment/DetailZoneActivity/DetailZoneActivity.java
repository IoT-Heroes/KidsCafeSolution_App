package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.DetailZoneActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.kt.iotheroes.kidscafesolution.R;

public class DetailZoneActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_zone);

        webView = (WebView) findViewById(R.id.webView);
    }
}
