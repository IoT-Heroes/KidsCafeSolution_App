package com.kt.gigaiot_sdk.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 4. 7..
 */
public class SvrResponse<T> {

    @SerializedName("responseCode")
    String responseCode;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    T data;

    public SvrResponse(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public SvrResponse(String responseCode, String message, T data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
