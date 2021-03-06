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

    private int kidTotalWalk;
    private int kidGoalWalk;
    private int calorie;

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
        // TODO : 원래는 이런경우 없는데, 지금 가데이터라 버그 막아 놓기 위해서 임시방편
        if (activityDatas.size() == 0) return;

        // 표준 걸음 수 계산 = max 값은 개별 활동량 데이터 수 x 최대 값 (maximum)
        kidGoalWalk = activityDatas.size() * activityDatas.get(0).getMaximum();
        // 아이 누적 걸음 수 계산
        for (KidStatic activity : activityDatas)
            kidTotalWalk += activity.getAverage();

        /*
        칼로리 계산
        공식 및 기준 정리 : https://github.com/IoT-Heroes/KidsCafeSolution_App/issues/2
         */
        // 아이 칼로리 소모량 계산 (그냥 int값으로..)
        // TODO : 1을 몇 분 놀았는지로 변경
        calorie = (int) (moveVal * (kidMetVal * (airVal * kid.getWeight() * 1)));

        this.activityDatas = activityDatas;
    }

    public int getKidTotalWalk() {
        return kidTotalWalk;
    }

    public int getKidGoalWalk() {
        return kidGoalWalk;
    }

    public float getCalorie() {
        return calorie;
    }
}
