package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.kt.gigaiot_sdk.PushApi;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Splash.SplashActivity;
import com.kt.iotheroes.kidscafesolution.Util.Constant.Constant;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.PushDialog;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;
/**
 * Created by mijeong on 2018. 12. 10..
 * BroadcastReceiver가 받은 메세지를 처리하는 역할
 * PowerManage : sleep 상태에서도 수신
 * context.startService로 GCMIntentService 시작
 */

public class GCMIntentService extends IntentService {

    private static final String TAG = GCMIntentService.class.getSimpleName();
    private static final String CHANNEL_ID = GCMIntentService.class.getSimpleName();

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

        // TODO : 공통 -> 놀이구역 쾌적도 알림
        // TODO : 부모 -> 퇴장시간 알림
        // TODO : 관리자 -> 알바생 호출

        try{
            final Context context = getApplicationContext();
            String action = intent.getAction();
            Log.d(TAG, "action : " + action);

            if(action.equals("com.google.android.c2dm.intent.REGISTRATION")){
                // TODO : 추후 보기
                handleRegistration(context, intent);
            } else if(action.equals("com.google.android.c2dm.intent.RECEIVE")){
                // 메시지 받은 것 처리
				new Handler(getMainLooper()).post(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(GCMIntentService.this, "GCM push received!!", Toast.LENGTH_LONG).show();
					}
				});
                handleMessage(context, intent);
            }
        } finally{
            synchronized (LOCK) {
                sWakeLock.release();
            }
        }
    }

    private void handleRegistration(Context context, Intent intent){
        Log.d(TAG, "GCMIntentService.handleRegistration()  GCM handleRegistration");

        String regirationId = intent.getStringExtra("registration_id");
        String unregistered = intent.getStringExtra("unregistered");
        String error = intent.getStringExtra("error");

        Log.i(TAG, "registerId : " + regirationId);
        Log.i(TAG, "unregistered : " + unregistered);
        Log.i(TAG, "error : " + error);

        if(regirationId != null && regirationId.length() > 0){

        }
        else if(unregistered != null && unregistered.length() > 0){
            Log.i(TAG, "unregistered = " + unregistered);
        }
        else if(error != null && error.length() > 0){

        }
        else{

        }
    }

    /**
     * 수신된 메시지를 처리
     * @param context Android Application Context
     * @param intent Intent
     */
    private void handleMessage(Context context, Intent intent){
        Log.d(TAG, "GCMIntentService.handleMessage()  GCM handleMessage");
//        Log 원래 IoT Makers 코드
        Set<String> keys = intent.getExtras().keySet();
        for(String key : keys){
            Log.d(TAG, "GCM Key : " + key);
            Log.d(TAG, "GCM Value : " + intent.getExtras().get(key));
        }

        String recvMsg = intent.getStringExtra("message");
        int type = 0;
        String message = "";

        try {
            JSONObject objJson = new JSONObject(recvMsg);

            // 01 : 수집 데이터, 03 : 발생 데이터
            type = objJson.getInt("type");
            if (type == 3) { // 이벤트 발생 데이터만 노티를 띄워준다. D
                message = objJson.getString("message");
                sendIntent(context, message);
            } else if (type == 1) {
                // TEST 용 (PUSH 수시로 받기 위해서)
                message = objJson.getString("message");
                sendIntent(context, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param context Android Application Context
     * @param message Push 메시지 내용
     */
    private void sendIntent(Context context, String message) {
        Log.d(TAG, "GCMIntentService.sendIntent()  #### GCM sendIntent");
        try {
            JSONObject objJson = new JSONObject(message);

            /*
            evetId : 이벤트 id
            evetNm : 이벤트 명
            collectSourceEvents(이벤트 데이터 배열) - attributes(관련 디바이스의 값)
             */
            String title = objJson.getString("evetNm");
            JSONArray collectSourceEvents = objJson.getJSONArray("collectSourceEvents");
            String[] deviceModelIdText = collectSourceEvents.getJSONObject(0).getString("deviceModelId").split("_"); // 형식 : mbr_1000006334_zone1
            String deviceModelId = deviceModelIdText[deviceModelIdText.length - 1]; // zone의 id : zone1
            JSONObject attributes = collectSourceEvents.getJSONObject(0).getJSONObject("attributes");
            int value = attributes.getInt(attributes.keys().next()); // 온도일지 뭘지 key 값을 모름

            String evetId = objJson.getString("evetId"); // evetId 형식 : 001PTL001D10005609
            String description = "";

            // TODO : 권한 제어 테스트
            // 이벤트 구별
            if (evetId.equals(getString(R.string.EVENT_ID_TEMP)) && PrefManager.getInstance().getPushTemp())
                description = deviceModelId + "놀이구역의 현재 온도는 " + value + "입니다.";
            else if(evetId.equals(getString(R.string.EVENT_ID_HUMID)) && PrefManager.getInstance().getPushHumid())
                description = deviceModelId + "놀이구역의 현재 습도는 " + value + "입니다.";
            else if (evetId.equals(getString(R.string.EVENT_ID_CALL)) && SharedManager.getInstance().getUser().getIsAuthor() && PrefManager.getInstance().getPushAdminCall())
                description = deviceModelId + "에서 관리자를 호출 했습니다.";
            else return;

            // notification을 띄워준다. (LOCK일 때는)
            int notiID = Integer.parseInt(evetId.substring(evetId.length() - 6), evetId.length());
            sendNotification(notiID, title, description);
            // dialog를 띄워준다. (UNLOCK일때)
            // TODO : dialog 뜨는지 테스트! (해당 화면에 제대로 뜨는지)
            final PushDialog dialog = new PushDialog(context);
            dialog.setMessage(title, description);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 실제 디바에스에 GCM으로부터 받은 메세지를 알려주는 함수이다. 디바이스 Notification Center에 나타난다.
     * @param title
     * @param message
     */
    private void sendNotification(int notificationId, String title, String message) {

        // 일단 splash activity로 이동.
        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "채널"; // channel_name
            String description = "채널 설명"; // channel_description
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * @param context Android Application Context
     * @param intent Push 메시지를 수신한 Intent
     */
    private void sendIntent(Context context, Intent intent) {
        Log.d(TAG, "GCMIntentService.sendIntent()  #### GCM sendIntent");

        String recvMsg = intent.getStringExtra("message");

        String type = "";
        String message = "";

        try {
            JSONObject objJson = new JSONObject(recvMsg);

            // 01 : 수집 데이터, 03 : 발생 데이터
            type = objJson.getString("type");
            /*
            evetId : 이벤트 id
            evetNm : 이벤트 명
            collectSourceEvents(이벤트 데이터 배열) - attributes(관련 디바이스의 값)
             */
            message = objJson.getString("message");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "GCMIntentService.sendIntent() type = " + type + " message = " + message);

        if (type.equals(PushApi.PUSH_MSG_TYPE_COLLECT)) {

            Log.d(TAG, "GCMIntentService.sendIntent()  TYPE = PushApi.PUSH_MSG_TYPE_COLLECT message = " + message);


        } else if (type.equals(PushApi.PUSH_MSG_TYPE_OUTBREAK)) {

            Log.d(TAG, "GCMIntentServicef.sendIntent()  TYPE = PushApi.PUSH_MSG_TYPE_OUTBREAK message = " + message);

        }
    }
}
