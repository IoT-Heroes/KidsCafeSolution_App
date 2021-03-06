package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.Model.Food;
import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity.AddChildActivity;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.kt.iotheroes.kidscafesolution.Util.Loadings.LoadingUtil;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddChildListActivity extends AppCompatActivity {
    private static final String TAG = AddChildListActivity.class.getSimpleName();
    static final int PICK_CONTACT_REQUEST = 1;

    List<Kid> kids;
    List<Food> foodList;
    AddChildListActivityFragment fragment;
    LinearLayout indicator;
    FloatingActionButton fab;

    private Button button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(getString(R.string.activity), TAG + "on Create");
        setContentView(R.layout.activity_add_child_list);

        initView();

        kids = new ArrayList<>();
        getFoodList();
    }

    private void getFoodList() {
        LoadingUtil.startLoading(indicator);
        APIClient.getClient().getFoodList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<Food>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<Food>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            foodList = userResponse.getData();
                        }
                        else
                            Log.i("connect", "get FoodList 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LoadingUtil.stopLoading(indicator);
                        // 다 받아오면 floating button을 그려준다.
                        fab.show();
                    }
                });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        indicator = (LinearLayout)findViewById(R.id.indicator);

        button_ok = (Button)toolbar.findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectAddKid();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddChildListActivity.this, AddChildActivity.class);
                intent.putExtra("foodList", (Serializable) foodList);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
        // 통신이 완료되기 전까지 띄우지 않는다.
        fab.hide();
        // 데이터가 없을 경우 ok 버튼을 띄우지 않는다.
        button_ok.setVisibility(View.GONE);

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
                            if (!SharedManager.getInstance().setKids(userResponse.getData()))
                                Log.i("connect", "add Child 에 문제가 발생하였습니다.");
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
                        final OkDialog okDialog = new OkDialog(AddChildListActivity.this);
                        okDialog.setMessage("쟈녀가 추가되었습니다!");
                        okDialog.setOkListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AddChildListActivity.this.finish();
                                okDialog.dismiss();
                            }
                        });
                        okDialog.show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {
            kids.add((Kid) data.getSerializableExtra("data"));
            fragment.reload(kids);

            if (kids.size() > 0)
                button_ok.setVisibility(View.VISIBLE);
        }
    }
}
