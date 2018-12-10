package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.content.Context;
import android.os.AsyncTask;

import com.kt.gigaiot_sdk.PushApi;
import com.kt.iotheroes.kidscafesolution.Util.Constant;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PreferenceManager;
import com.kt.gigaiot_sdk.data.PushTypePair;

import java.util.ArrayList;

/**
 * Created by mijeong on 2018. 12. 10..
 * GCM 세션을 등록
 */

public class PushSessionRegTask extends AsyncTask<Void, Void, Void> {

    PreferenceManager preferenceManager;

    public PushSessionRegTask(Context context) {
        this.preferenceManager = PreferenceManager.getInstance(context);
    }

    @Override
    protected Void doInBackground(Void... params) {

        ArrayList<PushTypePair> pushTypePairs = new ArrayList<>();

        pushTypePairs.add(new PushTypePair("서비스 대상 일련번호", PushApi.PUSH_MSG_TYPE_COLLECT));
        pushTypePairs.add(new PushTypePair("서비스 대상 일련번호", PushApi.PUSH_MSG_TYPE_OUTBREAK));

        PushApi pushApi = new PushApi(preferenceManager.getAccessToken());
        pushApi.gcmSessionRegistration(preferenceManager.getMemberSeq(),
                preferenceManager.getGcmRegID(), Constant.IOT_MAKERS_APP_ID, pushTypePairs);
        return null;
    }
}
