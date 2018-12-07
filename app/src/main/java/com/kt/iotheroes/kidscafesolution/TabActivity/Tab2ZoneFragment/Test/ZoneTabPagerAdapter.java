package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Test.Tab1Map.ZoneTab1MapFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Test.Tab2List.ZoneTab2ListFragment;

/**
 * Created by mijeong on 2018. 12. 7..
 */

public class ZoneTabPagerAdapter extends FragmentStatePagerAdapter {
    final static int FRAGMENT_SIZE = 2;

    FragmentManager childFm;

    public ZoneTabPagerAdapter(FragmentManager fm, FragmentManager childFm) {
        super(fm);
        this.childFm = childFm;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {

            return new ZoneTab1MapFragment();
        }
        else
            return new ZoneTab2ListFragment();
    }

    @Override
    public int getCount() {
        return FRAGMENT_SIZE;
    }
}
