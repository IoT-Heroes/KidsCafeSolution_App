package com.kt.iotheroes.kidscafesolution.Settings.Admin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;

/**
 * Created by mijeong on 2018. 12. 11..
 */

public class AdminSettingsFragment extends PreferenceFragment {

    PreferenceScreen zoneConditionPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting_preference_admin);
        zoneConditionPreference = (PreferenceScreen)findPreference("zoneCondition");

        setZoneConditionTextSummary();

        PrefManager.getInstance().setChangeListener(prefListener);
    }

    private void setZoneConditionTextSummary() {
        int zoneConditionCnt = 0;
        if(PrefManager.getInstance().getPushTemp()){
            zoneConditionCnt++;
        }
        if(PrefManager.getInstance().getPushHumid()){
            zoneConditionCnt++;
        }
        setTextSummary(zoneConditionCnt, zoneConditionPreference);
    }

    private void setTextSummary(int cnt, PreferenceScreen preferenceScreen) {
        if (cnt == 2)
            preferenceScreen.setSummary("모두 사용 중");
        else if (cnt == 1)
            preferenceScreen.setSummary("일부 사용 중");
        else
            preferenceScreen.setSummary("사용 안함");
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(getString(R.string.EVENT_ID_TEMP)) || key.equals(getString(R.string.EVENT_ID_HUMID))) {
                // zoneConditionPreference text 변경
                setZoneConditionTextSummary();
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }
        }
    };
}
