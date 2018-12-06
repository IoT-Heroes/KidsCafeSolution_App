package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Setting.AddChildListActivity;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.KidDetailActivity;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsAdapter;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList.KidsListFargment;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.List;

public class Tab1KidsFargment extends KidsListFargment {
    private static final String NAVIGATION_ID = "navigationId";

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
        View view = super.onCreateView(inflater, container, savedInstanceState);

        initView(view);
        return view;
    }

    private void initView(View view) {
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
        List<Kid> kids = SharedManager.getInstance().getUser().getChild();
        if(kids.size() == 0) {
            presentDialog();
        } else {
            adapter.setKids(kids);
        }
        adapter.notifyDataSetChanged();
    }

    public void presentDialog() {
        final OkDialog okDialog = new OkDialog(getContext());
        okDialog.setMessage("등록된 자녀가 없습니다.\n자녀를 등록해주세요.");
        okDialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddChildListActivity.class);
                startActivity(intent);
                okDialog.dismiss();
            }
        });
        okDialog.show();
    }
}
