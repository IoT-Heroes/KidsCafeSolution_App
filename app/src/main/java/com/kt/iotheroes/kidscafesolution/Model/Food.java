package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class Food implements Serializable{
    @SerializedName("calorie")
    String calorie;

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public Food(String id) {
        this.id = id;
    }
}
