package com.kt.iotheroes.kidscafesolution.Util.SharedManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mijeong on 2018. 12. 10..
 */

public class PreferenceManager {
    private final static String PREF_NAME = "iotHeroes.pref";

    private final static String PREF_GCM_REG_ID = "pref.gcm_regid";
    private final static String PREF_ACCESS_TOKEN = "pref.access_token";
    public static final String PREF_ACCOUNT_MEMBER_SEQ = "pref.account_mbrseq";

    private volatile static PreferenceManager single;
    private static SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PreferenceManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static PreferenceManager getInstance(Context context) {
        if (single == null) {
            synchronized (PreferenceManager.class) {
                single = new PreferenceManager(context);
            }
        }
        return single;
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
}
