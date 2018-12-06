package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class Kid {

    @SerializedName("id")
    String id;

    @SerializedName("parentId")
    String parentId;

    @SerializedName("userId")
    String userId;

    @SerializedName("name")
    String name;

    @SerializedName("isBandWearing")
    String isBandWearing;

    @SerializedName("sex")
    String sex;

    @SerializedName("targetActivityFigure")
    int targetActivityFigure;

    @SerializedName("age")
    int age;

    @SerializedName("height")
    int height;

    @SerializedName("weight")
    int weight;

    @SerializedName("eatableFoodList")
    List<Food> eatableFoodList;

    @SerializedName("currentCafeVisitingRecord")
    CafeVisitingRecord currentCafeVisitingRecord;

    @SerializedName("cafeVisitingRecord")
    List<CafeVisitingRecord> cafeVisitingRecord;

    boolean wearingBand;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isWearingBand() {
        return wearingBand;
    }


    public Kid(String name, int age, String sex, int height, int weight, boolean wearingBand) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.wearingBand = wearingBand;
    }

}
