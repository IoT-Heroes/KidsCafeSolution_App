package com.kt.gigaiot_sdk.error;

/**
 * Created by ceoko on 15. 4. 2..
 */
public class GiGaIotException extends RuntimeException {

    public GiGaIotException() {
    }

    public GiGaIotException(String detailMessage) {
        super(detailMessage);
    }

    public GiGaIotException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public GiGaIotException(Throwable throwable) {
        super(throwable);
    }
}
