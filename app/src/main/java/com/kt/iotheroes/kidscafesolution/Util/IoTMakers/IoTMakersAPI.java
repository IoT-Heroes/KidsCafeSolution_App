package com.kt.iotheroes.kidscafesolution.Util.IoTMakers;

import android.os.AsyncTask;

import com.kt.gigaiot_sdk.PushApi;
import com.kt.gigaiot_sdk.SvcTgtApi;
import com.kt.gigaiot_sdk.data.PushTypePair;
import com.kt.gigaiot_sdk.data.SvcTgt;
import com.kt.gigaiot_sdk.data.SvcTgtApiResponse;
import com.kt.gigaiot_sdk.network.ApiConstants;
import com.kt.iotheroes.kidscafesolution.Util.Constant.Constant;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by mijeong on 2018. 12. 10..
 */

public class IoTMakersAPI {

    private final static String TAG = "IoTMakersAPI";

    public static class GetSvcTgtTask extends AsyncTask<Void, Void, SvcTgtApiResponse> {

        @Override
        protected SvcTgtApiResponse doInBackground(Void... params) {

            try{
                SvcTgtApi svcTgtApi = new SvcTgtApi(PreferenceManager.getInstance().getAccessToken());
                return svcTgtApi.getSvcTgtSeqList(Constant.IOT_MAKERS_OAUTH_ID);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(SvcTgtApiResponse result) {

            if (result != null && result.getResponseCode().equals(ApiConstants.CODE_OK)) {

                ArrayList<SvcTgt> mArraySvcTgt = result.getSvcTgts();
                // 서비스 대상의 일련번호를 저장한다.
                PreferenceManager.getInstance().setService_TgtSeq(mArraySvcTgt.get(0).getSvcTgtSeq());

                new PushSessionRegTask().execute();
            } else if (result != null && result.getResponseCode().equals(ApiConstants.CODE_NG) && result.getMessage().equals("Unauthorized")) {
                new PushSessionDeleteTask().execute();
            }

        }
    }

    public static class PushSessionRegTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            ArrayList<PushTypePair> pushTypePairs = new ArrayList<>();

            pushTypePairs.add(new PushTypePair(PreferenceManager.getInstance().getService_TgtSeq(), PushApi.PUSH_MSG_TYPE_COLLECT));
            pushTypePairs.add(new PushTypePair(PreferenceManager.getInstance().getService_TgtSeq(), PushApi.PUSH_MSG_TYPE_OUTBREAK));

            PushApi pushApi = new PushApi(PreferenceManager.getInstance().getAccessToken());
            pushApi.gcmSessionRegistration(PreferenceManager.getInstance().getMemberSeq(),
                    Constant.IOT_MAKERS_APP_ID, PreferenceManager.getInstance().getGcmRegID(), pushTypePairs);
            return null;
        }
    }

    public static class PushSessionDeleteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            PushApi pushApi = new PushApi(PreferenceManager.getInstance().getAccessToken());
            pushApi.gcmSessionDelete(PreferenceManager.getInstance().getGcmRegID());

            return null;
        }
    }

}
