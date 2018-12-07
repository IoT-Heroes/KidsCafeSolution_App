package com.kt.iotheroes.kidscafesolution.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 6..
 */

public class KidInfo implements Serializable{
    private Kid kid;
    private List<UsingZone> zoneDatas;

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
}
