package com.kt.iotheroes.kidscafesolution.Util.SharedManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mijeong on 2018. 12. 10..
 */

public class PreferenceManager {
    private final static String PREF_NAME = "iotHeroes.pref";

    private final static String PREF_GCM_REG_ID = "pref.gcm_regid";

    private volatile static PreferenceManager single;
    private static SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PreferenceManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static PreferenceManager getSingle(Context context) {
        if (single == null) {
            synchronized (PreferenceManager.class) {
                single = new PreferenceManager(context);
            }
        }
        return single;
    }

    public void setGcmRegID(String id) {
        editor.putString(PREF_GCM_REG_ID, id);
        editor.commit();
    }
}
