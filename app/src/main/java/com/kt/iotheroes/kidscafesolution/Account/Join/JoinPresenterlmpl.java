package com.kt.iotheroes.kidscafesolution.Account.Join;

import android.util.Log;

import com.kt.iotheroes.kidscafesolution.Model.User;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class JoinPresenterlmpl implements JoinContract.JoinPresenter {
    private JoinContract.JoinView view;
    private User user;
    private boolean checked;

    public JoinPresenterlmpl(JoinContract.JoinView view) {
        this.view = view;
        user = User.getInstance();
        checked = false;
    }

    @Override
    public void onJoinBtnSelected(String id, String pw, String phone) {
        Boolean joinResult = user.join(id, pw, phone);

        if (joinResult) view.goToBottomTabActivity(user);
        else view.presentDialog("회원가입에 실패했습니다.");
    }

    @Override
    public boolean isCheck() {
        return checked;
    }

    @Override
    public void pwCheck(String pw1, String pw2) {
        checked = pw1.equals(pw2);
        view.imageCheck(checked);
        Log.i("pw", "result : " + checked);
    }
}
