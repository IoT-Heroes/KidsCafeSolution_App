package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.DetailZoneActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kt.iotheroes.kidscafesolution.R;

public class DetailZoneActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_zone);

        webView = (WebView) findViewById(R.id.webView);

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
        webView.loadUrl("https://www.naver.com/");
    }

    public void goURL(String url){
        final long startTime = System.currentTimeMillis();
        // 하드웨어 가속
        // 캐쉬 끄기
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(url);

    }
}
