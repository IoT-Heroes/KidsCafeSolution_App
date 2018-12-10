package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 5. 19..
 */
public class Protocol {

    @SerializedName("protId")
    private String protId;

    @SerializedName("protNm")
    private String protNm;

    @SerializedName("protDesc")
    private String protDesc;

    @SerializedName("stdYn")
    private String stdYn;

    public String getProtId() {
        return protId;
    }

    public String getProtNm() {
        return protNm;
    }

    public String getProtDesc() {
        return protDesc;
    }

    public String getStdYn() {
        return stdYn;
    }
}
