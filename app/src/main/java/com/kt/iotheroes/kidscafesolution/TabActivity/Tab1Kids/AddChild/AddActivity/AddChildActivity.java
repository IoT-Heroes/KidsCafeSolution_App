package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kt.iotheroes.kidscafesolution.Model.Food;
import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;

import java.util.List;

public class AddChildActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button_add;

    private AddChildAdapter adapter;
    private RecyclerView.LayoutManager layoutManger;

    private Kid kid;

    public void setKidInputInfo(String name, int age, String sex, int height, int weight) {
        kid.setName(name);
        kid.setAge(age);
        kid.setSex(sex);
        kid.setHeight(height);
        kid.setWeight(weight);
    }

    public void setKidEatableFood(List<Food> eatableFoodList) {
        kid.setEatableFoodList(eatableFoodList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        kid = new Kid();

        initView();
    }

    private void initView() {
        button_add = (Button)findViewById(R.id.button_add);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);

        if (adapter == null) {
            adapter = new AddChildAdapter(this);
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : POST 통신 구현
            }
        });
    }
}
