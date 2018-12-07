package com.kt.iotheroes.kidscafesolution.Model;

/**
 * Created by mijeong on 2018. 12. 7..
 */

public class VisitingRecord {
    private String startDate;
    private String endDate;
    private String bandDeviceId;
    private int amountPrice;
    private int amountRest;
    private int usingTime;

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getBandDeviceId() {
        return bandDeviceId;
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
