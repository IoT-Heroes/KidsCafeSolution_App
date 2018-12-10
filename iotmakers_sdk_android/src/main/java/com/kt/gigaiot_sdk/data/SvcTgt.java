package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 4. 9..
 */
public class SvcTgt {

    @SerializedName("svcTgtSeq")
    String svcTgtSeq;

    @SerializedName("svcTgtNm")
    String svcTgtNm;

    @SerializedName("mbrSeq")
    String mbrSeq;

    @SerializedName("userTokn")
    String userTokn;

    @SerializedName("userNm")
    String userNm;

    @SerializedName("telNo")
    String telNo;

    @SerializedName("email")
    String email;

    @SerializedName("dstrCd")
    String dstrCd;

    @SerializedName("svcThemeCd")
    String svcThemeCd;

    @SerializedName("unitSvcCd")
    String unitSvcCd;

    public String getSvcTgtSeq() {
        return svcTgtSeq;
    }

    public void setSvcTgtSeq(String svcTgtSeq) {
        this.svcTgtSeq = svcTgtSeq;
    }

    public String getSvcTgtNm() {
        return svcTgtNm;
    }

    public void setSvcTgtNm(String svcTgtNm) {
        this.svcTgtNm = svcTgtNm;
    }

    public String getMbrSeq() {
        return mbrSeq;
    }

    public void setMbrSeq(String mbrSeq) {
        this.mbrSeq = mbrSeq;
    }

    public String getUserTokn() {
        return userTokn;
    }

    public void setUserTokn(String userTokn) {
        this.userTokn = userTokn;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDstrCd() {
        return dstrCd;
    }

    public void setDstrCd(String dstrCd) {
        this.dstrCd = dstrCd;
    }

    public String getSvcThemeCd() {
        return svcThemeCd;
    }

    public void setSvcThemeCd(String svcThemeCd) {
        this.svcThemeCd = svcThemeCd;
    }

    public String getUnitSvcCd() {
        return unitSvcCd;
    }

    public void setUnitSvcCd(String unitSvcCd) {
        this.unitSvcCd = unitSvcCd;
    }

    @Override
    public String toString() {
        return "SvcTgt{" +
                "svcTgtSeq='" + svcTgtSeq + '\'' +
                ", svcTgtNm='" + svcTgtNm + '\'' +
                ", mbrSeq='" + mbrSeq + '\'' +
                ", userTokn='" + userTokn + '\'' +
                ", userNm='" + userNm + '\'' +
                ", telNo='" + telNo + '\'' +
                ", email='" + email + '\'' +
                ", dstrCd='" + dstrCd + '\'' +
                ", svcThemeCd='" + svcThemeCd + '\'' +
                ", unitSvcCd='" + unitSvcCd + '\'' +
                '}';
    }
}
