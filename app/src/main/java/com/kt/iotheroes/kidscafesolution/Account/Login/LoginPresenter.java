package com.kt.iotheroes.kidscafesolution.Account.Login;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Template.Presenter;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class LoginPresenter implements Presenter {

    private LoginActivity view;
    private User model;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
        this.model = User.getInstance();
    }

    @Override
    public void onCreate() {
        model = User.getInstance();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onLoginBtnSelected(String id, String pw) {
        boolean loginResult = model.login(id, pw);
        if (loginResult) view.goToBottomTabActivity(model);
        else view.presentDialog("로그인 실패하였습니다.");
    }
}
