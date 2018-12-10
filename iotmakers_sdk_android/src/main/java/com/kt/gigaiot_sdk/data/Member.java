package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 6. 22..
 */
public class Member {

    /*
    "mbrSeq": "1000000346",
        "mbrId": "marx1779",
        "credentialId": "",
        "userNm": "김석훈",
        "telNo": "010-6306-0417",
        "email": "kim.seokhun@kt.com",
        "mbrPwd": "",
        "delYn": "N",
        "cretDt": "2015-06-03 19:22"
     */

    @SerializedName("mbrSeq")
    String mbrSeq;

    @SerializedName("credentialId")
    String credentialId;

    @SerializedName("userNm")
    String userNm;

    @SerializedName("telNo")
    String telNo;

    @SerializedName("email")
    String email;

    @SerializedName("mbrPwd")
    String mbrPwd;

    @SerializedName("delYn")
    String delYn;

    @SerializedName("cretDt")
    String cretDt;

    public String getMbrSeq() {
        return mbrSeq;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public String getUserNm() {
        return userNm;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getEmail() {
        return email;
    }

    public String getMbrPwd() {
        return mbrPwd;
    }

    public String getDelYn() {
        return delYn;
    }

    public String getCretDt() {
        return cretDt;
    }
}
