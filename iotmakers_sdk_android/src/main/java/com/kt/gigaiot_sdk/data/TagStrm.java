package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 3. 24..
 */
public class TagStrm {

    @SerializedName("tagStrmSeq")
    private String tagStrmSeq;                  //태그스트림일련번호

    @SerializedName("tagStrmId")
    private String tagStrmId;                   //태그스트림ID

    @SerializedName("tagStrmUnitVal")
    private String tagStrmUnitVal;              //태그스트림단위값

    @SerializedName("tagStrmPrpsTypeCd")
    private String tagStrmPrpsTypeCd;           //제어코드 0000010:수집 0000020:제어

    @SerializedName("tagStrmPrpsTypeCdNm")
    private String tagStrmPrpsTypeCdNm;

    @SerializedName("tagStrmValTypeCd")
    private String tagStrmValTypeCd;            //숫자형여부 0000010:숫자 0000020:문자

    @SerializedName("tagStrmValTypeCdNm")
    private String tagStrmValTypeCdNm;

    @SerializedName("indcOdrg")
    private String indcOdrg;                    //표시순서

    @SerializedName("amdrId")
    private String amdrId;

    @SerializedName("amdDt")
    private String amdDt;

    public String getTagStrmSeq() {
        return tagStrmSeq;
    }

    public String getTagStrmId() {
        return tagStrmId;
    }

    public String getTagStrmUnitVal() {
        return tagStrmUnitVal;
    }

    public String getTagStrmPrpsTypeCd() {
        return tagStrmPrpsTypeCd;
    }

    public String getTagStrmPrpsTypeCdNm() {
        return tagStrmPrpsTypeCdNm;
    }

    public String getTagStrmValTypeCd() {
        return tagStrmValTypeCd;
    }

    public String getTagStrmValTypeCdNm() {
        return tagStrmValTypeCdNm;
    }

    public String getIndcOdrg() {
        return indcOdrg;
    }

    public String getAmdrId() {
        return amdrId;
    }

    public String getAmdDt() {
        return amdDt;
    }
}
