package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class KidInfo implements Serializable{
    // cal 계산에 필요한 값
    final static float kidMetVal = 3.0f;
    final static float standartMetVal = 4.0f;
    final static float airVal = 3.5f;
    final static float moveVal = 5f;

    private Kid kid;
    private List<UsingZone> zoneDatas;
    private List<KidStatic> pulseDatas;
    private List<KidStatic> activityDatas;

    public class ActivityTotal {
        private float kidTotalData;
        private float standartData;

        public float getKidTotalData() {
            return kidTotalData;
        }

        public float getStandartData() {
            return standartData;
        }
    }

    private ActivityTotal walk;
    private ActivityTotal cal;

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
        ActivityTotal walk = new ActivityTotal();

        // 표준 걸음 수 계산 = max 값은 개별 활동량 데이터 수 x 최대 값 (maximum)
        walk.standartData = activityDatas.size() * activityDatas.get(0).getMaximum();
        // 아이 누적 걸음 수 계산
        for (KidStatic activity : activityDatas)
            walk.kidTotalData += activity.getAverage();
        this.walk = walk;

        /*
        칼로리 계산
        공식 및 기준 정리 : https://github.com/IoT-Heroes/KidsCafeSolution_App/issues/2
         */

        ActivityTotal cal = new ActivityTotal();
        // TODO : Issue에 맞게 문제 해결!!!
        // 표준 칼로리 소모량 계산
        cal.standartData = moveVal * (standartMetVal * (airVal * kid.getWeight() * 1));
        // 아이 칼로리 소모량 계산
        cal.kidTotalData = moveVal * (kidMetVal * (airVal * kid.getWeight() * 1));
        this.cal = cal;

        this.activityDatas = activityDatas;
    }

    public ActivityTotal getWalk() {
        return walk;
    }

    public ActivityTotal getCal() {
        return cal;
    }
}
