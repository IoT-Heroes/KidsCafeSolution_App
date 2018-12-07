package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.ListActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsAdapter;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsListFargment;

import java.util.List;

/*
이 페이지는 맨 처음 아이를 등록하지 않았을 때 홈 화면에서만 접근할 수 있는 페이지다.
 */
public class AddChildListActivityFragment extends KidsListFargment {

    // back stack에 있다가 돌아올 때 호출됨
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        connectAdapter();
        return view;
    }

    private void connectAdapter() {
        if (adapter == null) {
            adapter = new KidsAdapter(this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void reload() {
        adapter.notifyDataSetChanged();
    }

    public void reload(List<Kid> kids) {
        adapter.setKids(kids);
        adapter.notifyDataSetChanged();
    }
}
