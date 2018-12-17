package com.kt.iotheroes.kidscafesolution.AR;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.VisitingRecord;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.unity3d.player.UnityPlayer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UnityPlayerActivity extends Activity
{
    private long backKeyPressedTime = 0;
    private Toast toast;

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    String kidId;
    String arPage;

    boolean connectStatus = true;

    // Setup activity layout
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        mUnityPlayer = new UnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();

        arPage = getIntent().getStringExtra("page");
        if (arPage.equals(getString(R.string.AR_CONNECT))) {
            kidId = getIntent().getStringExtra("kidId");
        }
    }

    /**
     * unity에게 어떤 scene을 띄울건지 알려준다.
     */
    public void setScenePage() {
        UnityPlayer.UnitySendMessage("SceneGate", "SetScenePage", arPage);
    }

    /**
     * unity로 부터 밴드 id를 전송 받는다.
     * 키즈밴드 등록화면에서 마커가 인식되었을 때 호출되는 함수이다.
     * 받은 데이터를 서버에 전송해 아이-밴드를 맵핑 시킨다.
     * @param id : band id
     */
    public void setBandId(String id) {
        // 여러번 호출 되는 것을 막기 위함
        if (connectStatus) {
            connectStatus = false;
            connectBand(id);
        }

//        if (connectStatus) {
//            connectStatus = false;
////            connectBand("BAND2");
//            VisitingRecord test = new VisitingRecord();
//            test.setBandDeviceId("BAND3");
//            test.setChildId(kidId);
//            showSuccessDialog(test);
//        }
    }

    /**
    * unity에게 아이의 id를 전송한다.
    */
    public void setKidId()
    {
        /**
         * UnityPlayer.UnitySendMessage라는 함수는 유니티로 메시지를 전송하는 기능
         * @param object : 메시지를 받을 객체
         * @param func : 실행할 함수 명
         * @param parameter : 함수의 파라미터에 넣어줄 값
         */
        UnityPlayer.UnitySendMessage("AndroidGate", "SetKidId", kidId);
    }

    private void connectBand(String bandId) {
        VisitingRecord sendData = new VisitingRecord();
        sendData.setBandDeviceId(bandId);
        sendData.setChildId(kidId);

        APIClient.getClient().connectBand(sendData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<VisitingRecord>>() {
                    @Override
                    public void onNext(@NonNull Response<VisitingRecord> userResponse) {
                        if (userResponse.getState() == 2001) { // success
                            showSuccessDialog(userResponse.getData());
                        }
                        else if (userResponse.getState() == 4000)
                            showAlreadyRentBandDialog();
                        else
                            Log.e("connect", "밴드 등록에 문제 발생");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void showSuccessDialog(final VisitingRecord visitingRecord) {
        final OkDialog okDialog = new OkDialog(this);
        okDialog.setMessage("밴드 연결에 성공했습니다!");
        okDialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("visitingRecord", visitingRecord);
                setResult(RESULT_OK, intent);
//                UnityPlayerActivity.this.finish();
                UnityPlayer.UnitySendMessage("AndroidGate", "GoBack", null);
                okDialog.dismiss();
            }
        });
        okDialog.show();
    }

    private void showAlreadyRentBandDialog() {
        final OkDialog okDialog = new OkDialog(this);
        okDialog.setMessage("이미 연결되어 있는 밴드입니다.");
        okDialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectStatus = true;
                okDialog.dismiss();
            }
        });
        okDialog.show();
    }

    public void goBack() {
        Runnable action = new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        };
        runOnUiThread(action);
    }

    /// EXIT
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(getApplicationContext(), getString(R.string.exit_app), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override protected void onNewIntent(Intent intent)
    {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override protected void onDestroy ()
    {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override protected void onStart()
    {
        super.onStart();
        mUnityPlayer.start();
    }

    @Override protected void onStop()
    {
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override public void onLowMemory()
    {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
    /*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }
}
