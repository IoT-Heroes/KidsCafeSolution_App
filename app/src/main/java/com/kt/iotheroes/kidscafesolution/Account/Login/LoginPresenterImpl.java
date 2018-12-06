package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.util.Log;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private final static String errMessage = "login에 문제가 발생하였습니다.";

    private LoginContract.LoginView view;
    private User user;

    public LoginPresenterImpl(LoginActivity view) {
        this.view = view;
        this.user = null;
    }

    public void onLoginBtnSelected(String id, String pw) {
        user = new User(id, pw);

        APIClient.getClient().login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<User>>() {
                    @Override
                    public void onNext(@NonNull Response<User> userResponse) {
                        if (userResponse.getResult().equals(R.string.connection_success)) {
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
