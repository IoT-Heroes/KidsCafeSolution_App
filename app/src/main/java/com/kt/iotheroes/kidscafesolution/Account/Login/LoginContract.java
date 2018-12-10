package com.kt.iotheroes.kidscafesolution.Account.Login;

import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PreferenceManager;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public interface LoginContract {

    interface LoginView{
        void presentDialog(String message);
        void goToBottomTabActivity();
        PreferenceManager getPreferenceManager();
    }

    interface LoginPresenter{
        void onLoginBtnSelected(String id, String pw);
    }
}
