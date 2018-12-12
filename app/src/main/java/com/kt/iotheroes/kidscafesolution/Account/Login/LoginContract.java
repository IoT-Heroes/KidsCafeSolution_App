package com.kt.iotheroes.kidscafesolution.Account.Login;

import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public interface LoginContract {

    interface LoginView{
        void presentDialog(String message);
        void goToBottomTabActivity();
        PrefManager getPreferenceManager();
    }

    interface LoginPresenter{
        void onLoginBtnSelected(String id, String pw);
    }
}
