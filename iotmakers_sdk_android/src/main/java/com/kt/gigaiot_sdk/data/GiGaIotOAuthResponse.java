package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class GiGaIotOAuthResponse extends Response {

    private String accessToken;
    private String mbrSeq;

    public GiGaIotOAuthResponse(String responseCode, String message, String accessToken, String mbrSeq) {
        super(responseCode, message);
        this.accessToken = accessToken;
        this.mbrSeq = mbrSeq;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getMbrSeq() {
        return mbrSeq;
    }
}
