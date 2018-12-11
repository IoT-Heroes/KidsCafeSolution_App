package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String pw;

    @SerializedName("phoneNumber")
    private String phone;

    @SerializedName("isAuthor")
    private Boolean isAuthor;

    @SerializedName("token")
    private String token;

    @SerializedName("child")
    private List<Kid> child = new ArrayList<>();

    // 로그인 용
    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    // 회원가입 용
    public User(String id, String pw, String phone, String name) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getIsAuthor() {
        return isAuthor;
    }

    public String getToken() {
        return token;
    }

    public List<Kid> getChild() {
        return child;
    }

    public void setChild(final List<Kid> child) {
        this.child = child;
    }

    public void upDateChild(int i, Kid kid) {
        this.child.set(i, kid);
    }
}
