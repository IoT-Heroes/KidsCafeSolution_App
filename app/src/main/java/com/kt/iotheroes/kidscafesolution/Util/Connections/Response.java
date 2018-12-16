package com.kt.iotheroes.kidscafesolution.Util.Connections;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class Response<T> {
    public String responseCode;

    @SerializedName("result")
    private String result;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("state")
    private int state;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public int getState() {
        return state;
    }
}
