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

    // TODO : 나중에 관리자 권한 확인하는 용도, 가라 데이터 말고 확정되면 관련 부분 개발할 것
    @SerializedName("isAuthor")
    private String isAuthor;

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

    public String getIsAuthor() {
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
