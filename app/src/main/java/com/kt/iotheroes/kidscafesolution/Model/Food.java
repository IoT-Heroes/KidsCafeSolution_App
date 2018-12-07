package com.kt.iotheroes.kidscafesolution.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class Food implements Serializable{
    @SerializedName("calorie")
    private String calorie;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    private boolean checked = false;

    public Food(String id) {
        this.id = id;
    }

    public String getCalorie() {
        return calorie;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
