package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;

/**
 * Created by mijeong on 2018. 12. 7..
 */

public class VisitingRecord implements Serializable{
    private String startDate;
    private String endDate;
    private String bandDeviceId;
    private String childId;
    private int amountPrice;
    private int amountRest;
    private int usingTime;

    public void setBandDeviceId(String bandDeviceId) {
        this.bandDeviceId = bandDeviceId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getBandDeviceId() {
        return bandDeviceId;
    }

    public String getChildId() {
        return childId;
    }

    public int getAmountPrice() {
        return amountPrice;
    }

    public int getAmountRest() {
        return amountRest;
    }

    public int getUsingTime() {
        return usingTime;
    }
}
