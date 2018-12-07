package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class Kid implements Serializable {

    @SerializedName("id")
    String id;

    @SerializedName("parentId")
    String parentId;

    @SerializedName("userId")
    String userId;

    @SerializedName("name")
    String name;

    @SerializedName("isBandWearing")
    Boolean isBandWearing;

    @SerializedName("sex")
    String sex;

    // 목표 활동량
    @SerializedName("targetActivityFigure")
    int targetActivityFigure;

    @SerializedName("age")
    int age;

    @SerializedName("birth")
    String birth;

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

    VisitingRecord visitingRecord;

    public String getId() {
        return id;
    }

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

    public VisitingRecord getVisitingRecord() {
        return visitingRecord;
    }

    public Boolean isBandWearing() {
        return isBandWearing;
    }

    public Kid() {}

    public Kid(String name, int age, String sex, int height, int weight, boolean wearingBand) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        // TODO : 여기 나중에 다 false로 초기화 시킬 것
        this.isBandWearing = wearingBand;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setEatableFoodList(List<Food> eatableFoodList) {
        this.eatableFoodList = eatableFoodList;
    }

    public void setVisitingRecord(VisitingRecord visitingRecord) {
        this.visitingRecord = visitingRecord;
    }
}
