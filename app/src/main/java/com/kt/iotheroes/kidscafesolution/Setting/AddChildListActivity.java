package com.kt.iotheroes.kidscafesolution.Setting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.ArrayList;
import java.util.List;

public class AddChildListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Kid> kids = new ArrayList<>();
        kids.add(new Kid("ㅁ", 2, "ㅇ", 2, 2, true));
        SharedManager.getInstance().setUser(new User(kids));

        setContentView(R.layout.activity_add_child_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
