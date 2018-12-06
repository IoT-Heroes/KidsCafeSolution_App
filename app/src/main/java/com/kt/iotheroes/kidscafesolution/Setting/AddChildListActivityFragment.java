package com.kt.iotheroes.kidscafesolution.Setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsAdapter;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsListFargment;

import java.util.ArrayList;
import java.util.List;

public class AddChildListActivityFragment extends KidsListFargment {
    private static final String NAVIGATION_ID = "navigationId";

    public AddChildListActivityFragment() {
    }

    //Fragment는 Bundle로 데이터 주고 받음
    public static AddChildListActivityFragment newInstance(int id) {
        AddChildListActivityFragment fragment = new AddChildListActivityFragment();
        Bundle args = new Bundle();
        args.putInt(NAVIGATION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    // back stack에 있다가 돌아올 때 호출됨
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        connectAdapter();
        connectKids();
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
        connectKids();
    }

    public void connectKids() {
        List<Kid> kids = new ArrayList<>();
        kids.add(new Kid("테스트지롱", 2, "ㅇ", 2, 2, true));
        adapter.setKids(kids);
        adapter.notifyDataSetChanged();

//        // TODO : 아이가 없을 때는 자녀 등록 화면으로 유도한다.
//        if(SharedManager.getInstance().getUser().getChild().size() == 0) {
//            Toast.makeText(getContext(), "자녀가 없어요.", Toast.LENGTH_SHORT).show();
//        } else {
//            adapter.setKids(SharedManager.getInstance().getUser().getChild());
//        }
//        adapter.notifyDataSetChanged();
    }
}