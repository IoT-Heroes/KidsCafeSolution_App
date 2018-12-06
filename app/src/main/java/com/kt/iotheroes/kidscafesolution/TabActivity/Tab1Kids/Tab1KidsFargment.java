package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.KidDetailActivity;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

public class Tab1KidsFargment extends TabParentFragment {
    private static final String NAVIGATION_ID = "navigationId";

    private BottomTabActivity tabActivity;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManger;
    private KidsAdapter adapter;

    public Tab1KidsFargment() {
    }

    //Fragment는 Bundle로 데이터 주고 받음
    public static Tab1KidsFargment newInstance(int id) {
        Tab1KidsFargment fragment = new Tab1KidsFargment();
        Bundle args = new Bundle();
        args.putInt(NAVIGATION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    // back stack에 있다가 돌아올 때 호출됨
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1_kids_fargment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tabActivity = (BottomTabActivity)getActivity();

        if (recyclerView == null) {
            recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_kids);
            recyclerView.setHasFixedSize(true);
            layoutManger = new LinearLayoutManager(tabActivity);
            recyclerView.setLayoutManager(layoutManger);
        }

        if (adapter == null) {
            adapter = new KidsAdapter(this, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 아이 상세 페이지로 이동
                    Intent intent = new Intent(getActivity(), KidDetailActivity.class);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        }

        connectKids();
    }

    // 버튼 클릭 리스트 아이템 클릭과 같은 특정 이벤트 발생 시 리스너 호출
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    /*
    onAttach, onDetach : 다른 외부 요소들과 통신 할 수 있는 리스너를 프래그먼트에 탈/부착
     */

    // Activity에 할당되었을 때 호출되며 인자로 Activity를 넘겨준다.
    //Activity에 이벤트 콜백을 설정 - 리스너 부착
    // onAttach -> onCreate -> onCreateView()
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        Log.i("tag", "onAttach 호출");
//
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    // Fragment가 Activity로부터 할당이 제거될 때 호출 - 리스너 탈착
//   @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//
//       Log.i("tag", "onDetach 호출");
//    }

    @Override
    public void reload() {
        connectKids();
    }

    public void connectKids() {
        // TODO : 아이가 없을 때는 자녀 등록 화면으로 유도한다.
        if(SharedManager.getInstance().getUser().getChild().size() == 0) {
            Toast.makeText(getContext(), "자녀가 없어요.", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setKids(SharedManager.getInstance().getUser().getChild());
            adapter.notifyDataSetChanged();
        }


        // 통신해서 데이터를 불러온다.
//        ArrayList<Kid> kids = new ArrayList<>();
//
//        kids.add(new Kid("이미정", 25, "여", 160, 00, true));
//        kids.add(new Kid("이미주", 25, "여", 180, 00, false));
//        adapter.setKids(kids);
//        adapter.notifyDataSetChanged();
    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
