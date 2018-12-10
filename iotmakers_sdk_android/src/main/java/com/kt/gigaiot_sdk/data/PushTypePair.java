package com.kt.gigaiot_sdk.data;

/**
 * Created by ceoko on 15. 6. 29..
 */
public class PushTypePair {

    String svcTgtSeq;
    String msgTypeCd;

    public PushTypePair(String svcTgtSeq, String msgTypeCd) {
        this.svcTgtSeq = svcTgtSeq;
        this.msgTypeCd = msgTypeCd;
    }

    public String getSvcTgtSeq() {
        return svcTgtSeq;
    }

    public String getMsgTypeCd() {
        return msgTypeCd;
    }
}
