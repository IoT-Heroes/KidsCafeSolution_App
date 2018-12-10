package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ceoko on 15. 3. 24..
 */
public class Device {

    @SerializedName("amdrId")
    private String amdrId;

    @SerializedName("devUUID")
    private String devUUID;

    @SerializedName("devSttusCd")
    private String devSttusCd;

    @SerializedName("spotDevSeq")
    private String spotDevSeq;

    @SerializedName("bindTypeNm")
    private String bindTypeNm;

    @SerializedName("spotDevId")
    private String spotDevId;

    @SerializedName("protID")
    private String protID;

    @SerializedName("devModelNm")
    private String devModelNm;

    @SerializedName("useYn")
    private String useYn;

    @SerializedName("devNm")
    private String devNm;

    @SerializedName("gwCnctId")
    private String gwCnctId;

    @SerializedName("bindTypeCd")
    private String bindTypeCd;

    @SerializedName("protNm")
    private String protNm;

    @SerializedName("devModelSeq")
    private String devModelSeq;

    @SerializedName("amdDt")
    private String amdDt;

    @SerializedName("svcTgtSeq")
    private String svcTgtSeq;

    @SerializedName("oprtSttusCd")
    private String oprtSttusCd;

    @SerializedName("cretDt")
    private String cretDt;

    @SerializedName("devPwd")
    private String devPwd;

    @SerializedName("termlMakrNm")
    String termlMakrNm;

    @SerializedName("status")
    private String status;

    @SerializedName("atcFileSeq")
    private String atcFileSeq;

    @SerializedName("atcFileNm")
    private String atcFileNm;

    @SerializedName("tagStrmList")
    private List<TagStrm> tagStrmList;

    public String getAmdrId() {
        return amdrId;
    }

    public void setAmdrId(String amdrId) {
        this.amdrId = amdrId;
    }

    public String getDevUUID() {
        return devUUID;
    }

    public void setDevUUID(String devUUID) {
        this.devUUID = devUUID;
    }

    public String getDevSttusCd() {
        return devSttusCd;
    }

    public void setDevSttusCd(String devSttusCd) {
        this.devSttusCd = devSttusCd;
    }

    public String getSpotDevSeq() {
        return spotDevSeq;
    }

    public void setSpotDevSeq(String spotDevSeq) {
        this.spotDevSeq = spotDevSeq;
    }

    public String getBindTypeNm() {
        return bindTypeNm;
    }

    public void setBindTypeNm(String bindTypeNm) {
        this.bindTypeNm = bindTypeNm;
    }

    public String getSpotDevId() {
        return spotDevId;
    }

    public void setSpotDevId(String spotDevId) {
        this.spotDevId = spotDevId;
    }

    public String getProtID() {
        return protID;
    }

    public void setProtID(String protID) {
        this.protID = protID;
    }

    public String getDevModelNm() {
        return devModelNm;
    }

    public void setDevModelNm(String devModelNm) {
        this.devModelNm = devModelNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getDevNm() {
        return devNm;
    }

    public void setDevNm(String devNm) {
        this.devNm = devNm;
    }

    public String getGwCnctId() {
        return gwCnctId;
    }

    public void setGwCnctId(String gwCnctId) {
        this.gwCnctId = gwCnctId;
    }

    public String getBindTypeCd() {
        return bindTypeCd;
    }

    public void setBindTypeCd(String bindTypeCd) {
        this.bindTypeCd = bindTypeCd;
    }

    public String getProtNm() {
        return protNm;
    }

    public void setProtNm(String protNm) {
        this.protNm = protNm;
    }

    public String getDevModelSeq() {
        return devModelSeq;
    }

    public void setDevModelSeq(String devModelSeq) {
        this.devModelSeq = devModelSeq;
    }

    public String getAmdDt() {
        return amdDt;
    }

    public void setAmdDt(String amdDt) {
        this.amdDt = amdDt;
    }

    public String getSvcTgtSeq() {
        return svcTgtSeq;
    }

    public void setSvcTgtSeq(String svcTgtSeq) {
        this.svcTgtSeq = svcTgtSeq;
    }

    public String getOprtSttusCd() {
        return oprtSttusCd;
    }

    public void setOprtSttusCd(String oprtSttusCd) {
        this.oprtSttusCd = oprtSttusCd;
    }

    public String getCretDt() {
        return cretDt;
    }

    public void setCretDt(String cretDt) {
        this.cretDt = cretDt;
    }

    public String getDevPwd() {
        return devPwd;
    }

    public void setDevPwd(String devPwd) {
        this.devPwd = devPwd;
    }

    public String getTermlMakrNm() {
        return termlMakrNm;
    }

    public void setTermlMakrNm(String termlMakrNm) {
        this.termlMakrNm = termlMakrNm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtcFileNm() {
        return atcFileNm;
    }

    public void setAtcFileNm(String atcFileNm) {
        this.atcFileNm = atcFileNm;
    }

    public String getAtcFileSeq() {
        return atcFileSeq;
    }

    public void setAtcFileSeq(String atcFileSeq) {
        this.atcFileSeq = atcFileSeq;
    }

    public List<TagStrm> getTagStrmList() {
        return tagStrmList;
    }

    public void setTagStrmList(List<TagStrm> tagStrmList) {
        this.tagStrmList = tagStrmList;
    }

    @Override
    public String toString() {
        return "Device{" +
                "amdrId='" + amdrId + '\'' +
                ", devUUID='" + devUUID + '\'' +
                ", devSttusCd='" + devSttusCd + '\'' +
                ", spotDevSeq='" + spotDevSeq + '\'' +
                ", bindTypeNm='" + bindTypeNm + '\'' +
                ", spotDevId='" + spotDevId + '\'' +
                ", protID='" + protID + '\'' +
                ", devModelNm='" + devModelNm + '\'' +
                ", useYn='" + useYn + '\'' +
                ", devNm='" + devNm + '\'' +
                ", gwCnctId='" + gwCnctId + '\'' +
                ", bindTypeCd='" + bindTypeCd + '\'' +
                ", protNm='" + protNm + '\'' +
                ", devModelSeq='" + devModelSeq + '\'' +
                ", amdDt='" + amdDt + '\'' +
                ", svcTgtSeq='" + svcTgtSeq + '\'' +
                ", oprtSttusCd='" + oprtSttusCd + '\'' +
                ", cretDt='" + cretDt + '\'' +
                ", devPwd='" + devPwd + '\'' +
                ", termlMakrNm='" + termlMakrNm + '\'' +
                ", status='" + status + '\'' +
                ", atcFileSeq='" + atcFileSeq + '\'' +
                ", atcFileNm='" + atcFileNm + '\'' +
                ", tagStrmList=" + tagStrmList +
                '}';
    }
}
