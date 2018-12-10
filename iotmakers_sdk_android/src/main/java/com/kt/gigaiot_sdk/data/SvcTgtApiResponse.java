package com.kt.gigaiot_sdk.data;

import java.util.ArrayList;

/**
 * Created by ceoko on 15. 4. 9..
 */
public class SvcTgtApiResponse extends Response {

    ArrayList<SvcTgt> svcTgts;

    public SvcTgtApiResponse(String responseCode, String message, ArrayList<SvcTgt> svcTgts) {
        super(responseCode, message);
        this.svcTgts = svcTgts;
    }

    public ArrayList<SvcTgt> getSvcTgts() {
        return svcTgts;
    }
}
