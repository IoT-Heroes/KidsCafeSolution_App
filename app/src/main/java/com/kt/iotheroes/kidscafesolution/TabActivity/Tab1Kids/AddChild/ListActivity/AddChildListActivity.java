package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity.AddChildActivity;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddChildListActivity extends AppCompatActivity {
    static final int PICK_CONTACT_REQUEST = 1;

    List<Kid> kids;
    AddChildListActivityFragment fragment;

    private Button button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_child_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_ok = (Button)toolbar.findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : 데이터 POST 구현
                connectAddKid();
            }
        });

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

    private void connectAddKid() {
        APIClient.getClient().addChildList(kids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<Kid>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<Kid>> userResponse) {
                        if (userResponse.getResult().equals("success")) {

                        }
                        else
                            Log.i("connect", "add Child 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());

                        final OkDialog okDialog = new OkDialog(AddChildListActivity.this);
                        okDialog.setMessage("쟈녀 추가에 실패하셨습니다.");
                        okDialog.show();
                    }

                    @Override
                    public void onComplete() {
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {
            kids.add((Kid) data.getSerializableExtra("data"));
            fragment.reload(kids);
        }
    }
}
