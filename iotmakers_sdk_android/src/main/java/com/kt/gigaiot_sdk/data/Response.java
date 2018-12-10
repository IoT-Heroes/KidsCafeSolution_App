package com.kt.gigaiot_sdk.data;

/**
 * Created by ceoko on 15. 4. 3..
 */
public class Response {

    private String responseCode;
    private String message;

    public Response(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return message;
    }
}
