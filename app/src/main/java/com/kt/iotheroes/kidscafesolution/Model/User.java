package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class User implements Serializable{
    private static User instance;
    private User () {}

    public static User getInstance () {
        if (instance == null)
            instance = new User();
        return instance;
    }

    private String id;
    private String pw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean login(String id, String pw) {
        this.id = id;
        this.pw = pw;

        // TODO : 로그인 통신 구현
        // login 성공 시 return true
        if (id.equals("id") && pw.equals("pw")) {
            // login 성공 시 return true
            return true;
        }
        return false;
    }
}
