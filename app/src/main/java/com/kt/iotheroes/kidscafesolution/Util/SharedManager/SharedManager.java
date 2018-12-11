package com.kt.iotheroes.kidscafesolution.Util.SharedManager;

import android.util.Log;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Model.VisitingRecord;
import com.kt.iotheroes.kidscafesolution.Model.Zone;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class SharedManager {
    /*
    volatile : memory에서만 read/write 연산이 일어남. 변수의 가시성 문제 해결
     */
    private volatile static SharedManager single;

    private volatile User user;
    private volatile List<Zone> zonelist;
    public static boolean finishVisitingRecord = false;

    public static SharedManager getInstance() {
        if (single == null) {
            // static method 동기화는 SharedManager 객체를 기준으로 이루어짐
            // JVM 안에 클래스 객체는 클래스 당 하나만 존재할 수 있으므로, 같은 클래스에 대해서는 오직 한 쓰레드만 동기화된 스태틱 메소드를 실행할 수 있다.
            synchronized (SharedManager.class) {
                if (single == null) {
                    single = new SharedManager();
                }
            }
        }
        return single;
    }

    public synchronized User getUser() {
        return user;
    }

    // TODO : 로그인 결과 값이 담기기 때문에 pw는 없다.
    // visiting record가 호출되지 않았을 때 false를 리턴한다.
    public synchronized boolean setUser(final User user) {
        try {
            this.user = user;

            for (int i = 0; i < user.getChild().size(); i++) {
                final Kid kid = user.getChild().get(i);
                // band 연결 아이 데이터의 경우에는 visiting record도 가져온다.
                if (kid.isBandWearing()) {
                    final int finalI = i;
                    APIClient.getClient().getChildVisitingRecords(kid.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableObserver<Response<List<VisitingRecord>>>() {
                                @Override
                                public void onNext(@NonNull Response<List<VisitingRecord>> userResponse) {
                                    if (userResponse.getResult().equals("success")) {
                                        // 최근 값만 가져온다.
                                        // TODO : 가라 데이터라서.. 밴드 미착용으로 바꾸겠음
                                        if (userResponse.getData().size() > 0)
                                            kid.setVisitingRecord(userResponse.getData().get(0));
                                        else kid.setBandWearing(false);
                                    } else
                                        Log.i("connect", "get child visiting record 에 문제가 발생하였습니다.");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    e.printStackTrace();
                                    Log.e("connect", e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    // visiting 정보를 추가한 아이를 업데이트 한다.
                                    user.upDateChild(finalI, kid);
                                    finishVisitingRecord = true;
                                }
                            });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return finishVisitingRecord;
    }

    public boolean setKids(List<Kid> kids) {
        try {
            this.user.setChild(kids);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Zone> getZonelist() {
        return zonelist;
    }

    public void setZonelist(List<Zone> zonelist) {
        this.zonelist = zonelist;
    }
}