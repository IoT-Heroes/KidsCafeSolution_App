package com.kt.iotheroes.kidscafesolution.Model;

import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIInterface;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class User implements Serializable{
    private static User instance;
    private User () {}
    private APIInterface api;

    public User(APIInterface api) {
        this.api = APIClient.getClient();
    }

    public static User getInstance () {
        if (instance == null)
            instance = new User();
        return instance;
    }

     String id;
    private String pw;
    private String phone;

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

    public boolean join(String id, String pw, String phone) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;

        // TODO : 회원가입 통신 구현
        // login 성공 시 return true
        if (id.equals("id") && pw.equals("pw") && phone.equals("phone")) {
            // login 성공 시 return true
            return true;
        }
        return false;
    }
}
