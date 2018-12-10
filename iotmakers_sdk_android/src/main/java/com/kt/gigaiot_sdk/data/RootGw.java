package com.kt.gigaiot_sdk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ceoko on 15. 5. 20..
 */
public class RootGw {

    @SerializedName("rootGwCnctId")
    String rootGwCnctId;

    public String getRootGwCnctId() {
        return rootGwCnctId;
    }
}
