package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("pw")

    private String pw;
    @SerializedName("phone")
    private String phone;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public User(String id, String pw, String phone) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
    }

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
}
