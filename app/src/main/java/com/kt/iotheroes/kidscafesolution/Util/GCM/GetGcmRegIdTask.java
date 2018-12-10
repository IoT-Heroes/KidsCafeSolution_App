package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PreferenceManager;

import java.io.IOException;

/**
 * Created by mijeong on 2018. 12. 10..
 * 구글 GCM 서버에 어플리케이션이 어떤 메시지를 수신하는지 알린다.
 */

public class GetGcmRegIdTask extends AsyncTask<Void, Void, String> {

    private GoogleCloudMessaging mGcm;

    public GetGcmRegIdTask(Context context) {
        this.mGcm = GoogleCloudMessaging.getInstance(context);
    }

    @Override
    protected String doInBackground(Void... params) {

        String regId = null;

        try {
            regId = mGcm.register("259837524286");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PreferenceManager.getInstance().setGcmRegID(regId);

        return regId;
    }
}
