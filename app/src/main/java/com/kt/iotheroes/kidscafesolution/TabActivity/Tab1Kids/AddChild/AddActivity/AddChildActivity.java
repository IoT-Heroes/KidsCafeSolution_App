package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kt.iotheroes.kidscafesolution.Model.Food;
import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.List;
import java.util.Map;

public class AddChildActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button_add;

    private AddChildAdapter adapter;
    private RecyclerView.LayoutManager layoutManger;

    private Kid kid;
    private List<Food> foodList;

    public void setKidInputInfo(String name, int age, String sex, int height, int weight) {
        kid.setName(name);
        kid.setAge(age);
        kid.setSex(sex);
        kid.setHeight(height);
        kid.setWeight(weight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        kid = new Kid();
        foodList = (List<Food>) getIntent().getSerializableExtra("foodList");

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

        adapter.setFoods(foodList);
        adapter.notifyDataSetChanged();

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 정보 담는다.
                Map<Integer, String> inputs = adapter.getInputs();
                try {
                    String name = inputs.get(R.id.edit_name);
                    int age = Integer.parseInt(inputs.get(R.id.edit_age));
                    int height = Integer.parseInt(inputs.get(R.id.edit_height));
                    int weight = Integer.parseInt(inputs.get(R.id.edit_weight));
                    String sex = inputs.get(R.id.radio_group);
                    setKidInputInfo(name, age, sex, height, weight);
                } catch (Exception e) {
                    // 입력 정보 안 담겼을 경우
                    final OkDialog okDialog = new OkDialog(AddChildActivity.this);
                    okDialog.setMessage("입력 정보가 잘못되었어요.");
                    okDialog.show();
                }
                // user_id 넣기
                kid.setUserId(SharedManager.getInstance().getUser().getId());

                // 음식 담는다.
                kid.setEatableFoodList(adapter.getSelectedFoods());

                // 목록으로 데이터를 보낸다.
                Intent intent = new Intent();
                intent.putExtra("data", kid);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
