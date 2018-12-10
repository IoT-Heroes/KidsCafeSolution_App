package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kt.gigaiot_sdk.SvcTgtApi;
import com.kt.gigaiot_sdk.data.EventLog;
import com.kt.gigaiot_sdk.data.SvcTgtApiResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private final static String errMessage = "login에 문제가 발생하였습니다.";

    private LoginContract.LoginView view;
    private User user;
    private ArrayList<EventLog> mArrayEventLogs;

    public LoginPresenterImpl(LoginActivity view) {
        this.view = view;
        this.user = null;
    }

    public void demoLogin(User user) {
        if (SharedManager.getInstance().setUser(user)) {
            view.goToBottomTabActivity();
        }
    }

    public void onLoginBtnSelected(String id, String pw) {
        user = new User(id, pw);
//        demoLogin(user);

//        connectLogin();
        new GetSvcTgtTask().execute();
    }

    private class GetSvcTgtTask extends AsyncTask<Void, Void, SvcTgtApiResponse> {

        @Override
        protected SvcTgtApiResponse doInBackground(Void... params) {

            try{
                SvcTgtApi svcTgtApi = new SvcTgtApi(view.getPreferenceManager().getAccessToken());
                return svcTgtApi.getSvcTgtSeqList("lmjing");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(SvcTgtApiResponse result) {

            if (result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {

                Log.i("tt","k");

            } else if (result != null && result.getResponseCode().equals(ApiConstants.CODE_NG) && result.getMessage().equals("Unauthorized")) {

                Log.i("tt","k2");
            }

        }
    }

    private void connectLogin() {
        APIClient.getClient().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<User>>() {
                    @Override
                    public void onNext(@NonNull Response<User> userResponse) {

                        if (userResponse.getResult().equals("success")) {
                            // pw값은 보관 X
                            if (!SharedManager.getInstance().setUser(userResponse.getData()))
                                Log.i("connect", errMessage);
                        }
                        else
                            Log.i("connect", errMessage);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                        view.presentDialog(errMessage);
                    }

                    @Override
                    public void onComplete() {
                        view.goToBottomTabActivity();
                    }
                });
    }
}
