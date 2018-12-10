package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ceoko on 15. 4. 27..
 */
public class Log {

    @SerializedName("occDt")
    private String occDt;

    @SerializedName("spotDevSeq")
    private String spotDevSeq;

    @SerializedName("svcCode")
    private String svcCode;

    @SerializedName("svcTgtSeq")
    private String svcTgtSeq;

    @SerializedName("attributes")
    private HashMap<String, Object> attributes;


    public String getOccDt() {
        return occDt;
    }

    public void setOccDt(String occDt) {
        this.occDt = occDt;
    }

    public String getSpotDevSeq() {
        return spotDevSeq;
    }

    public void setSpotDevSeq(String spotDevSeq) {
        this.spotDevSeq = spotDevSeq;
    }

    public String getSvcCode() {
        return svcCode;
    }

    public void setSvcCode(String svcCode) {
        this.svcCode = svcCode;
    }

    public String getSvcTgtSeq() {
        return svcTgtSeq;
    }

    public void setSvcTgtSeq(String svcTgtSeq) {
        this.svcTgtSeq = svcTgtSeq;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
    }
}
