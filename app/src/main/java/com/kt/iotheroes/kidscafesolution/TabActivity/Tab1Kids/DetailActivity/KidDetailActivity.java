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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_detail);

        initView();
        connectKidInfo();
        connectUsingZoneData();
        adapter.notifyDataSetChanged();
    }

    private void connectUsingZoneData() {
        APIClient.getClient().getChildUsingZone("SANG_JUNIOR", "2018-12-07")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<UsingZone>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<UsingZone>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            adapter.setZoneDatas(userResponse.getData());
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

    private void connectKidInfo() {
        KidInfo kidinfo = new KidInfo();

        kidinfo.setKid((Kid)getIntent().getSerializableExtra("kid"));
        adapter.setKidInfo(kidinfo);
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
