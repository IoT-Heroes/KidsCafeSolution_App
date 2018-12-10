//package com.kt.iotheroes.kidscafesolution.Util.IoTMakers;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by mijeong on 2018. 12. 10..
// * IoTMakers 와 안드로이드 앱을 디바이스 용도로 연동하기 위해서 다음과 같이 AsyncTask를 상속받은 클래스를 만들고,
// * Task를 실행 했을 때 TcpConnector를 통해 데이터를 전송하기 위해 doInBackground 메소드에 아래와 같은 코드를 작성
// */
//
//public class DeviceTask extends AsyncTask<Void, Void, Void> {
//    private static final String TAG = DeviceTask.class.getSimpleName();
//
//    private Map<String, Double> rows = new HashMap<String, Double>();
//
//    public DeviceTask(Map<String, Double> rows) {
//        this.rows = rows;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//    @Override
//    protected Void doInBackground(Void... params) {
//
//        // IoTMakers 연동 설정 정보
//        BaseInfo info = new BaseInfo();
//        // 접속 IP, Port 설정
//        info.setIp("220.90.216.90");
//        info.setPort(10020);
//        // 디바이스상세정보-> Gateway 연결 ID를 입력한다.
//        info.setExtrSysId("OPEN_TCP_001PTL001_1000000346");
//        // 디바이스상세정보-> 디바이스 아이디를 입력한다.
//        info.setDeviceId("android002");
//        // 디바이스상세정보-> 디바이스 패스워드를 입력한다.
//        info.setPassword("123456789");
//
//        // IoTMakers 연동 TCP Connector 생성
//        IMTcpConnector connector = new IMTcpConnector();
//        try {
//            connector.activate(new LogIf(), info, (long) 3000);
//
//            long transId = IMUtil.getTransactionLongRoundKey4();
//
//            Log.d(TAG, rows.toString());
//            // 계측 데이터 HashMap 객체로 전송한다. key는 센싱태그 명 value는 계측값을 넣는다.
//            connector.requestNumColecDatas(rows, new Date(), transId);
//
//            connector.deactivate();;
//
//        } catch (SdkException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//    }
//}
