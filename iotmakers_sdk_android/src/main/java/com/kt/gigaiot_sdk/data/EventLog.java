package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 6. 4..
 */
public class EventLog {

    @SerializedName("evetNm")
    String evetNm;

    @SerializedName("outbDtm")
    String outbDtm;

    public String getEvetNm() {
        return evetNm;
    }

    public String getOutbDtm() {
        return outbDtm;
    }
}
