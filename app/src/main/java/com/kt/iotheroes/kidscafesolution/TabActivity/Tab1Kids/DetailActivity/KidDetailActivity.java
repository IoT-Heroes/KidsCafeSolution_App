package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Model.KidInfo;
import com.kt.iotheroes.kidscafesolution.Model.Pulse;
import com.kt.iotheroes.kidscafesolution.Model.UsingZone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/*
이 부분 가라데이터라서 제대로 작동 안하는 것 처럼 보이는 것들 있음
 */

public class KidDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout indicator;

    private KidDetailAdapter adapter;
    private RecyclerView.LayoutManager layoutManger;

    private KidInfo kidInfo;
    private Kid kid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_detail);

        kidInfo = new KidInfo();
        kid = (Kid)getIntent().getSerializableExtra("kid");
        kidInfo.setKid(kid);

        initView();
        if (kid.isBandWearing()) {
            connectUsingZoneData();
            connectPulseData();
        }
        adapter.setKidInfo(kidInfo);

        adapter.notifyDataSetChanged();
    }

    private void connectPulseData() {
        String kidId = kidInfo.getKid().getId();
        String startDate = kidInfo.getKid().getVisitingRecord().getStartDate();
        String endDate = kidInfo.getKid().getVisitingRecord().getStartDate();
        String batchType = "M"; // 나는 분이라서 M, 시면 H

        // 가라 데이터 : "SANG_JUNIOR", "2018-12-07", "2018-12-08", "M"
        APIClient.getClient().getChildPulse(kidId, startDate, endDate, batchType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<Pulse>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<Pulse>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            kidInfo.setPulseDatas(userResponse.getData());
                        }
                        else
                            Log.i("connect", "get child pulse 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());

                        Toast.makeText(getApplicationContext(), "get child pulse에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), "get child pulse 성공.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void connectUsingZoneData() {
        String kidId = kidInfo.getKid().getId();
        String entranceDate = kidInfo.getKid().getVisitingRecord().getStartDate();

        APIClient.getClient().getChildUsingZone(kidId, entranceDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<UsingZone>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<UsingZone>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            kidInfo.setZoneDatas(userResponse.getData());
                        }
                        else
                            Log.i("connect", "get child using zone 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());

                        Toast.makeText(getApplicationContext(), "get child using zone 에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), "get child using zone 성공.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        indicator = (LinearLayout)findViewById(R.id.indicator);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);

        if (adapter == null) {
            adapter = new KidDetailAdapter(getApplicationContext(), this, indicator, kid.isBandWearing());
        }
        recyclerView.setAdapter(adapter);
    }
}
