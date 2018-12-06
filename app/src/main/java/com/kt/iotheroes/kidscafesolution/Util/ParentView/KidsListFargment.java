package com.kt.iotheroes.kidscafesolution.Util.ParentView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;

public class KidsListFargment extends TabParentFragment {
    private static final String NAVIGATION_ID = "navigationId";

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManger;
    protected KidsAdapter adapter;

    public KidsListFargment() {
    }

    //Fragment는 Bundle로 데이터 주고 받음
    public static KidsListFargment newInstance(int id) {
        KidsListFargment fragment = new KidsListFargment();
        Bundle args = new Bundle();
        args.putInt(NAVIGATION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    // back stack에 있다가 돌아올 때 호출됨
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1_kids, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        if (recyclerView == null) {
            recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_kids);
            recyclerView.setHasFixedSize(true);
            layoutManger = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManger);
        }

        if (adapter == null) {
            adapter = new KidsAdapter(this);
            recyclerView.setAdapter(adapter);
        }

//        connectKids();
    }

    @Override
    public void reload() {

    }

//    @Override
//    public void reload() {
//        connectKids();
//    }
//
//    public void connectKids() {
//        // TODO : 아이가 없을 때는 자녀 등록 화면으로 유도한다.
//        if(SharedManager.getInstance().getUser().getChild().size() == 0) {
//            Toast.makeText(getContext(), "자녀가 없어요.", Toast.LENGTH_SHORT).show();
//        } else {
//            adapter.setKids(SharedManager.getInstance().getUser().getChild());
//        }
//        adapter.notifyDataSetChanged();
//    }
}
