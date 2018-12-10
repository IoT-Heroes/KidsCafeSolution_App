package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 5. 20..
 */
public class BindType {

    @SerializedName("bindTypeCd")
    String bindTypeCd;

    @SerializedName("bindTypeNm")
    String bindTypeNm;

    public String getBindTypeCd() {
        return bindTypeCd;
    }

    public String getBindTypeNm() {
        return bindTypeNm;
    }
}
