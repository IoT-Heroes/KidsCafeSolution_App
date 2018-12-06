package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.R;

public class AddChildActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout indicator;

    private AddChildAdapter adapter;
    private RecyclerView.LayoutManager layoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        initView();
    }

    private void initView() {
        indicator = (LinearLayout)findViewById(R.id.indicator);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);

        if (adapter == null) {
            adapter = new AddChildAdapter(this);
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
