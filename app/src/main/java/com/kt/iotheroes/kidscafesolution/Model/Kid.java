package com.kt.iotheroes.kidscafesolution.Model;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class Kid {
    String name;
    int age;

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

    String sex;
    int height;
    int weight;
    boolean wearingBand;

    public Kid(String name, int age, String sex, int height, int weight, boolean wearingBand) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.wearingBand = wearingBand;
    }

}
