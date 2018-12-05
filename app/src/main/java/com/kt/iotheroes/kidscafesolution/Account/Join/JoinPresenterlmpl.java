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
        user = null;
        checked = false;
    }

    @Override
    public void onJoinBtnSelected(String id, String pw, String phone) {
        Boolean joinResult = join(id, pw, phone);

        if (joinResult) view.joinSuccess(user);
        else view.joinFail();
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

    @Override
    public boolean login(String id, String pw) {
        user = new User(id, pw);

        // TODO : 로그인 통신 구현
        // login 성공 시 return true
        if (id.equals("id") && pw.equals("pw")) {
            // login 성공 시 return true
            return true;
        }
        return false;
    }

    @Override
    public boolean join(String id, String pw, String phone) {
        user = new User(id, pw, phone);

        // TODO : 회원가입 통신 구현
        // login 성공 시 return true
        if (id.equals("id") && pw.equals("pw") && phone.equals("phone")) {
            // login 성공 시 return true
            return true;
        }
        return false;
    }
}
