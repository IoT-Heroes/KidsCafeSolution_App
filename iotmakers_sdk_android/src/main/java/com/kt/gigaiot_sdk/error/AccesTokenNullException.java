package com.kt.gigaiot_sdk.error;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class AccesTokenNullException extends GiGaIotException {

    public static String DEFAULT_MSG = "accessToken is missing";

    public AccesTokenNullException(String detailMessage) {
        super(detailMessage);
    }
}
