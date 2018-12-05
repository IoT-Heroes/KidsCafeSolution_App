package com.kt.iotheroes.kidscafesolution.Account.Login;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private LoginContract.LoginView view;
    private User user;

    public LoginPresenterImpl(LoginActivity view) {
        this.view = view;
        this.user = null;
    }

    public void onLoginBtnSelected(String id, String pw) {
        // TODO : Login 통신 구현
        user = new User(id, pw);

        if (id.equals("id") && pw.equals("pw")) {
            SharedManager.getInstance().setUser(user);
            view.goToBottomTabActivity();
        }
        else view.presentDialog("로그인 실패하였습니다.");
    }
}
