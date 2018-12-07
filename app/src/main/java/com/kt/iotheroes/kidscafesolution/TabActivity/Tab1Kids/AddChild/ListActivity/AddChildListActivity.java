package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity.AddChildActivity;

import java.util.ArrayList;
import java.util.List;

public class AddChildListActivity extends AppCompatActivity {
    static final int PICK_CONTACT_REQUEST = 1;

    List<Kid> kids;
    AddChildListActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_child_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kids = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddChildListActivity.this, AddChildActivity.class);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        fragment = (AddChildListActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {
            kids.add((Kid) data.getSerializableExtra("data"));
            Toast.makeText(getApplicationContext(), kids.get(0).getName(), Toast.LENGTH_SHORT).show();
            fragment.reload();
        }
    }
}
