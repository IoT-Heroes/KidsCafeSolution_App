package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 5. 6..
 */
public class Event {

    @SerializedName("camStatEvetExpnsnSeq")
    private String camStatEvetExpnsnSeq;

    @SerializedName("eplSeq")
    private String eplSeq;

    @SerializedName("devModelSeq")
    private String devModelSeq;

    @SerializedName("svcTgtSeq")
    private String svcTgtSeq;

    @SerializedName("dstrCd")
    private String dstrCd;

    @SerializedName("svcThemeCd")
    private String svcThemeCd;

    @SerializedName("unitSvcCd")
    private String unitSvcCd;

    @SerializedName("statEvetCd")
    private String statEvetCd;

    @SerializedName("evetSttusCd")
    private String evetSttusCd;

    @SerializedName("statEvetNm")
    private String statEvetNm;

    @SerializedName("amdrId")
    private String amdrId;

    @SerializedName("amdDt")
    private String amdDt;

    @SerializedName("eventId")
    private String eventId;

    public String getCamStatEvetExpnsnSeq() {
        return camStatEvetExpnsnSeq;
    }

    public String getEplSeq() {
        return eplSeq;
    }

    public String getDevModelSeq() {
        return devModelSeq;
    }

    public String getSvcTgtSeq() {
        return svcTgtSeq;
    }

    public String getDstrCd() {
        return dstrCd;
    }

    public String getSvcThemeCd() {
        return svcThemeCd;
    }

    public String getUnitSvcCd() {
        return unitSvcCd;
    }

    public String getStatEvetCd() {
        return statEvetCd;
    }

    public String getEvetSttusCd() {
        return evetSttusCd;
    }

    public String getStatEvetNm() {
        return statEvetNm;
    }

    public String getAmdrId() {
        return amdrId;
    }

    public String getAmdDt() {
        return amdDt;
    }

    public String getEventId() {
        return eventId;
    }

}
