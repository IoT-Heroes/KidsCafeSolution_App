package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class KidInfo implements Serializable{
    private Kid kid;
    private List<UsingZone> zoneDatas;
    private List<KidStatic> pulseDatas;
    private List<KidStatic> activityDatas;
    private int totalWalk;

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }

    public List<UsingZone> getZoneDatas() {
        return zoneDatas;
    }

    public void setZoneDatas(List<UsingZone> zoneDatas) {
        this.zoneDatas = zoneDatas;
    }

    public List<KidStatic> getPulseDatas() {
        return pulseDatas;
    }

    public void setPulseDatas(List<KidStatic> pulseDatas) {
        this.pulseDatas = pulseDatas;
    }

    public List<KidStatic> getActivityDatas() {
        return activityDatas;
    }

    public void setActivityDatas(List<KidStatic> activityDatas) {
        this.activityDatas = activityDatas;
        for (KidStatic activity : activityDatas)
            totalWalk += activity.getAverage();
    }

    public int getTotalWalk() {
        return totalWalk;
    }
}
