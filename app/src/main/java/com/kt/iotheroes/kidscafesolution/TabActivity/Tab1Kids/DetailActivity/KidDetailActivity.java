package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.R;

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
    }
}
