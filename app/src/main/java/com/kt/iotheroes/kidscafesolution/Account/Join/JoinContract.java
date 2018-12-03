package com.kt.iotheroes.kidscafesolution.Account.Join;

import com.kt.iotheroes.kidscafesolution.Model.User;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public interface JoinContract {

    interface JoinView {
        void presentDialog(String message);
        void goToBottomTabActivity(User user);
        void imageCheck(boolean check);
        void close();
    }

    interface  JoinPresenter {
        void onJoinBtnSelected(String id, String pw, String phone);
        boolean isCheck();
        void pwCheck(String pw1, String pw2);
    }

}
