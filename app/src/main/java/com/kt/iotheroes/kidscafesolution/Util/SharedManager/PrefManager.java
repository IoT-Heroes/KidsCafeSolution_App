package com.kt.iotheroes.kidscafesolution.Util.SharedManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 10..
 */

public class PrefManager {
    private final static String PREF_NAME = "iotHeroes.pref";

    private final static String PREF_USER_ID = "pref.user_id";
    private final static String PREF_GCM_REG_ID = "pref.gcm_regid";
    private final static String PREF_ACCESS_TOKEN = "pref.access_token";
    public static final String PREF_ACCOUNT_MEMBER_SEQ = "pref.account_mbrseq";
    public static final String PREF_SERVICE_TgtSeq = "pref.service_TgtSeq"; // 서비스 대상 일련번호

    private volatile static PrefManager single;
    private static SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    public void init(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static PrefManager getInstance() {
        if (single == null) {
            synchronized (PrefManager.class) {
                single = new PrefManager();
            }
        }
        return single;
    }

    public static SharedPreferences getPrefs() {
        return prefs;
    }

    public String getUserID() {
        return prefs.getString(PREF_USER_ID, null);
    }

    public void setUserID(String id) {
        editor.putString(PREF_USER_ID, id);
        editor.commit();
    }

    public void logout() {
        editor.clear();
        editor.commit();
//        single = null;
    }

    public String getGcmRegID() {
        return prefs.getString(PREF_GCM_REG_ID, null);
    }

    public void setGcmRegID(String id) {
        editor.putString(PREF_GCM_REG_ID, id);
        editor.commit();
    }

    public String getAccessToken() {
        return prefs.getString(PREF_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String token) {
        if (prefs.getString(PREF_ACCESS_TOKEN, null) != null)
             return;
        editor.putString(PREF_ACCESS_TOKEN, token);
        editor.commit();
    }

    public String getMemberSeq() {
        return prefs.getString(PREF_ACCOUNT_MEMBER_SEQ, null);
    }

    public void setMemberSeq(String token) {
        editor.putString(PREF_ACCOUNT_MEMBER_SEQ, token);
        editor.commit();
    }

    public String getService_TgtSeq() {
        return prefs.getString(PREF_SERVICE_TgtSeq, null);
    }

    public void setService_TgtSeq(String mbrld) {
        editor.putString(PREF_SERVICE_TgtSeq, mbrld);
        editor.commit();
    }

    // push
    public Boolean getPushTemp() {
        return prefs.getBoolean(context.getString(R.string.EVENT_ID_TEMP), true);
    }

    public Boolean getPushHumid() {
        return prefs.getBoolean(context.getString(R.string.EVENT_ID_HUMID), true);
    }

    public Boolean getPushAdminCall() {
        return prefs.getBoolean(context.getString(R.string.EVENT_ID_CALL), true);
    }

    public Boolean getPushEndBefore10() {
        return prefs.getBoolean(context.getString(R.string.EVENT_ID_END_BEFORE10), true);
    }

    public Boolean getPushEndExact() {
        return prefs.getBoolean(context.getString(R.string.EVENT_ID_END_EXACT), true);
    }

    public void setChangeListener(SharedPreferences.OnSharedPreferenceChangeListener changeListener) {
        prefs.registerOnSharedPreferenceChangeListener(changeListener);
    }

    public void initAdminPush() {
        editor.putBoolean(context.getString(R.string.EVENT_ID_END_EXACT), false);
        editor.putBoolean(context.getString(R.string.EVENT_ID_END_BEFORE10), false);
        editor.putBoolean(context.getString(R.string.EVENT_ID_CALL), true);
        editor.putBoolean(context.getString(R.string.EVENT_ID_TEMP), true);
        editor.putBoolean(context.getString(R.string.EVENT_ID_HUMID), true);
        editor.commit();
    }

    public void initParentPush() {
        editor.putBoolean(context.getString(R.string.EVENT_ID_END_EXACT), true);
        editor.putBoolean(context.getString(R.string.EVENT_ID_END_BEFORE10), true);
        editor.putBoolean(context.getString(R.string.EVENT_ID_CALL), false);
        editor.putBoolean(context.getString(R.string.EVENT_ID_TEMP), true);
        editor.putBoolean(context.getString(R.string.EVENT_ID_HUMID), true);
        editor.commit();
    }
}
