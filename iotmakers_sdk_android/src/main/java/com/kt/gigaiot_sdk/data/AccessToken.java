package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 6. 12..
 */
public class AccessToken {

    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String error_description;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("token_type")
    private String token_type;

    @SerializedName("expires_in")
    private String expires_in;

    @SerializedName("scope")
    private String scope;

    @SerializedName("svc_tgt_seq")
    private String svc_tgt_seq;

    @SerializedName("platform")
    private String platform;

    @SerializedName("svc_cd")
    private String svc_cd;

    @SerializedName("mbr_seq")
    private String mbr_seq;

    @SerializedName("company")
    private String company;

    @SerializedName("dstr_cd")
    private String dstr_cd;

    @SerializedName("theme_cd")
    private String theme_cd;

    @SerializedName("mbr_id")
    private String mbr_id;

    @SerializedName("jti")
    private String jti;

    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    public String getSvc_tgt_seq() {
        return svc_tgt_seq;
    }

    public String getPlatform() {
        return platform;
    }

    public String getSvc_cd() {
        return svc_cd;
    }

    public String getMbr_seq() {
        return mbr_seq;
    }

    public String getCompany() {
        return company;
    }

    public String getDstr_cd() {
        return dstr_cd;
    }

    public String getTheme_cd() {
        return theme_cd;
    }

    public String getMbr_id() {
        return mbr_id;
    }

    public String getJti() {
        return jti;
    }
}
