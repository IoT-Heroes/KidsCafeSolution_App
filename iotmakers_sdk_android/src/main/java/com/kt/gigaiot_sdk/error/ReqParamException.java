package com.kt.gigaiot_sdk.error;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class ReqParamException extends GiGaIotException {

    public static String DEFAULT_MSG = "parameter is missing";

    public ReqParamException(String detailMessage) {
        super(detailMessage);
    }
}
