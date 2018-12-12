package com.kt.iotheroes.kidscafesolution.Splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kt.gigaiot_sdk.GigaIotOAuth;
import com.kt.gigaiot_sdk.data.GiGaIotOAuthResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.iotheroes.kidscafesolution.Account.Login.LoginActivity;
import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.Constant.Constant;
import com.kt.iotheroes.kidscafesolution.Util.GCM.GetGcmRegIdTask;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {
    private GoogleCloudMessaging mGcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        PrefManager.getInstance().init(getApplicationContext());

//        new IoTMakersAPI.PushSessionDeleteTask().execute();
        // iot makers 플랫폼 로그인
        new LoginTask().execute();

//        connectLogin();
    }

    private class LoginTask extends AsyncTask<Void, Void, GiGaIotOAuthResponse> {

        ProgressDialog progressDialog;
        String id;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SplashActivity.this, "", "wait", true, false);
        }

        @Override
        protected GiGaIotOAuthResponse doInBackground(Void... params) {
            //테스트용
            GigaIotOAuth gigaIotOAuth = new GigaIotOAuth(Constant.IOT_MAKERS_APP_ID, Constant.IOT_MAKERS_APP_SECRET);
            GiGaIotOAuthResponse response = gigaIotOAuth.loginWithPassword(
                    Constant.IOT_MAKERS_OAUTH_ID, Constant.IOT_MAKERS_OAUTH_PW
            );

            return response;
        }

        @Override
        protected void onPostExecute(GiGaIotOAuthResponse result) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }

            if (result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {
                PrefManager.getInstance().setAccessToken(result.getAccessToken());
                PrefManager.getInstance().setMemberSeq(result.getMbrSeq());

                if (PrefManager.getInstance().getUserID() != null) {
                    connectLogin();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);
                }

                mGcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
                //mGcm.register("772329232378");

                // gcm 등록키를 통한 세션 등록
                new GetGcmRegIdTask(getApplicationContext()).execute();
            } else {
                Toast.makeText(SplashActivity.this, "fail : " + result.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void connectLogin() {
        APIClient.getClient().getUSer(PrefManager.getInstance().getUserID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<User>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<User>> userResponse) {

                        if (userResponse.getResult().equals("success")) {
                            // pw값은 보관 X
                            if (!SharedManager.getInstance().setUser(userResponse.getData().get(0)))
                                Log.i("connect", "자동로그인 문제");
                        }
                        else
                            Log.i("connect", "자동로그인 문제");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // main 화면으로 이동
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashActivity.this, BottomTabActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                });
    }
}