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
import com.kt.iotheroes.kidscafesolution.Model.UsingZone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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

        initView();

        kidInfo = new KidInfo();
        kid = (Kid)getIntent().getSerializableExtra("kid");

        kidInfo.setKid(kid);
        if (kid.isBandWearing()) {
            connectUsingZoneData();
        }
        adapter.setKidInfo(kidInfo);

        connectUsingZoneData();
        adapter.notifyDataSetChanged();
    }


/*
 zone data 입장 시간 넣어주어야 함.
 입장시간 -> child_visiting_record 으로 부터 가져와야 함.
 앱에서는 방문 기록은 필요 없으니, 상세 페이지 호출 시 최초 한번만 가져온다. (저장된 값 없을 경우에만)
 */


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
            adapter = new KidDetailAdapter(getApplicationContext(), this, indicator, true);
        }
        recyclerView.setAdapter(adapter);
    }
}
