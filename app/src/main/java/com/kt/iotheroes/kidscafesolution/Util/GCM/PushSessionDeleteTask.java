package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.content.Context;
import android.os.AsyncTask;

import com.kt.gigaiot_sdk.PushApi;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PreferenceManager;

/**
 * Created by mijeong on 2018. 12. 10..
 * GCM 세션 삭제
 */

public class PushSessionDeleteTask extends AsyncTask<Void, Void, Void> {

    PreferenceManager preferenceManager;

    public PushSessionDeleteTask(Context context) {
        this.preferenceManager = PreferenceManager.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        PushApi pushApi = new PushApi(preferenceManager.getAccessToken());
        pushApi.gcmSessionDelete(preferenceManager.getGcmRegID());

        return null;
    }
}
