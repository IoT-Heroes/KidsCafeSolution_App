package com.kt.iotheroes.kidscafesolution.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;

import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 11..
 */

public class ParentSettings extends PreferenceFragment {

    SharedPreferences prefs;
    PreferenceScreen zoneConditionPreference, endTimePreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting_preference);
        zoneConditionPreference = (PreferenceScreen)findPreference("zoneCondition");
        endTimePreference = (PreferenceScreen)findPreference("endTime");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(prefs.getBoolean("temperature", true)){
            zoneConditionPreference.setSummary("온도 사용 중");
        }
        if(prefs.getBoolean("humid", true)){
            String str = zoneConditionPreference.getSummary().toString();
            if (str.length() > 0)
                str += ", ";
            zoneConditionPreference.setSummary(str + "습도 사용 중");
        }

        if(prefs.getBoolean("before10minute", true)){
            endTimePreference.setSummary("10분 전 안내");
        }
        if(prefs.getBoolean("exact", true)){
            String str = endTimePreference.getSummary().toString();
            if (str.length() > 0)
                str += ", ";
            endTimePreference.setSummary(str + "정각 안내");
        }

        prefs.registerOnSharedPreferenceChangeListener(prefListener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("temperature")){

                String zoneConditionText;
                zoneConditionText = prefs.getBoolean("temperature", true) ? "온도 사용 중" : "";

                if(prefs.getBoolean("humid", true)){
                    zoneConditionText = zoneConditionText.length() > 0 ? "모두 사용" : "습도 사용 중";
                }else{
                    zoneConditionText += zoneConditionText.length() > 0 ? "" : "사용 안함";
                }
                zoneConditionPreference.setSummary(zoneConditionText);

                String endTimeText;
                endTimeText = prefs.getBoolean("before10minute", true) ? "온도 사용 중" : "";

                if(prefs.getBoolean("exact", true)){
                    endTimeText = endTimeText.length() > 0 ? "모두 사용" : "습도 사용 중";
                }else{
                    endTimeText += endTimeText.length() > 0 ? "" : "사용 안함";
                }
                endTimePreference.setSummary(endTimeText);

                //2뎁스 PreferenceScreen 내부에서 발생한 환경설정 내용을 2뎁스 PreferenceScreen에 적용하기 위한 소스
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }

        }
    };
}
