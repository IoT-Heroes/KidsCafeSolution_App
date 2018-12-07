package com.kt.iotheroes.kidscafesolution.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String pw;

    @SerializedName("phoneNumber")
    private String phone;

    // TODO : 나중에 관리자 권한 확인하는 용도, 가라 데이터 말고 확정되면 관련 부분 개발할 것
    @SerializedName("isAuthor")
    private String isAuthor;

    @SerializedName("token")
    private String token;

    @SerializedName("child")
    private List<Kid> child = new ArrayList<>();

    // 로그인 용
    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    // 회원가입 용
    public User(String id, String pw, String phone, String name) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getPhone() {
        return phone;
    }

    public String getIsAuthor() {
        return isAuthor;
    }

    public String getToken() {
        return token;
    }

    public List<Kid> getChild() {
        return child;
    }

    public void setChild(final List<Kid> child) {
        this.child = new ArrayList<>();
        for (Kid c : child) {
            final Kid kid = c;
            // band 연결 아이 데이터의 경우에는 visiting record도 가져온다.
            if (kid.isBandWearing()) {
                APIClient.getClient().getChildVisitingRecords(kid.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Response<List<VisitingRecord>>>() {
                            @Override
                            public void onNext(@NonNull Response<List<VisitingRecord>> userResponse) {
                                if (userResponse.getResult().equals("success")) {
                                    // 최근 값만 가져온다.
                                    kid.setVisitingRecord(userResponse.getData().get(0));
                                    Log.i("connect", kid.getName() + "의 startDate : " + kid.getVisitingRecord().getStartDate());
                                }
                                else
                                    Log.i("connect", "get child visiting record 에 문제가 발생하였습니다.");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                Log.e("connect", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                // visiting 정보를 저장한 아이 값을 추가한다.
                                child.add(kid);
                            }
                        });
            } else // 아닌 경우 그냥 저장
                child.add(kid);
        }
    }
}
