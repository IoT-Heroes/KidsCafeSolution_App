package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class CafeVisitingRecord implements Serializable{
    @SerializedName("amountPrice")
    int amountPrice;

    @SerializedName("amountRest")
    int amountRest;

    @SerializedName("bandDeviceId")
    String bandDeviceId;

    @SerializedName("childId")
    String childId;

    @SerializedName("childName")
    String childName;

    @SerializedName("endTime")
    String endTime;

    @SerializedName("startTime")
    String startTime;

    @SerializedName("usingTime")
    String usingTime;
}
