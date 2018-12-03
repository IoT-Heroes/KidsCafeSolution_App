package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.User;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginActivity view;
    private User model;

    public LoginPresenterImpl(LoginActivity view) {
        this.view = view;
        this.model = User.getInstance();
    }

    public void onLoginBtnSelected(String id, String pw) {
        Toast.makeText(view, "test", Toast.LENGTH_SHORT).show();
//        boolean loginResult = model.login(id, pw);
//        if (loginResult) view.goToBottomTabActivity(model);
//        else view.presentDialog("로그인 실패하였습니다.");
    }
}
