package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class Food {
    @SerializedName("calorie")
    String calorie;

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;
}
