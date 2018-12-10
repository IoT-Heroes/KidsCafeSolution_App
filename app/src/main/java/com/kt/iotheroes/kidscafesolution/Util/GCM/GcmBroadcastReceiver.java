package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mijeong on 2018. 12. 10..
 * 실제 전송되는 메시지를 수신하기 위함
 */

public class GcmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GCMIntentService.runIntentInService(context, intent);
    }
}
