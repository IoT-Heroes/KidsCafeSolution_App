package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kt.iotheroes.kidscafesolution.Util.Constant.Constant;

/**
 * Created by mijeong on 2018. 12. 10..
 * BroadcastReceiver가 받은 메세지를 처리하는 역할
 * PowerManage : sleep 상태에서도 수신
 * context.startService로 GCMIntentService 시작
 */

public class GCMIntentService extends IntentService {

    private static final String TAG = GCMIntentService.class.getSimpleName();

    private static PowerManager.WakeLock sWakeLock;
    private static final Object LOCK = GCMIntentService.class;

    public GCMIntentService() {
        super(Constant.GCM_INTENT_SERVICE_NAME);
    }

    public static void runIntentInService(Context context, Intent intent) {

        synchronized (LOCK) {
            if (sWakeLock == null) {
                PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                sWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Push_wakeLock");
            }
            sWakeLock.acquire();
            intent.setClassName(context, GCMIntentService.class.getName());
            context.startService(intent);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "GCMIntentService.onHandleIntent()  GCM onHandleIntent");
        Log.d(TAG, "intent : " + intent.toString());
        Log.d(TAG, "intent : " + intent);
    }
}
