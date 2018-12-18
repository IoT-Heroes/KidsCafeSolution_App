package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Model.VisitingRecord;
import com.kt.iotheroes.kidscafesolution.ParentView.KidList.KidsAdapter;
import com.kt.iotheroes.kidscafesolution.ParentView.KidList.KidsListFargment;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.ListActivity.AddChildListActivity;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.KidDetailActivity;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.kt.iotheroes.kidscafesolution.Util.Loadings.LoadingUtil;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Tab1KidsFargment extends KidsListFargment {
    private static final String TAG = Tab1KidsFargment.class.getSimpleName();
    private static final String NAVIGATION_ID = "navigationId";

    LinearLayout indicator;
    private KidsAdapter adapter;
    private int childVisitingCheckCount = 0;
    List<Kid> kids;

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

        Log.i(getString(R.string.activity), TAG + "on Create");
        return view;
    }

    private void initView(View view) {
        indicator = (LinearLayout)view.findViewById(R.id.indicator);
        if (adapter == null) {
            adapter = new KidsAdapter(this, new KidsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), KidDetailActivity.class);
                    intent.putExtra("kid", SharedManager.getInstance().getUser().getChild().get(position));
                    // TODO : 나중에 모델에 실어서 다 변경할 것!!!
                    intent.putExtra("kidIdx", position);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        }
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


    private void connectAllChild() {
        APIClient.getClient().getChildList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<Kid>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<Kid>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            SharedManager.getInstance().setKids(userResponse.getData());
                            kids = userResponse.getData();
                        } else
                            Log.i("connect", "get child visiting record 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getVisitingRecord(SharedManager.getInstance().getUser());
                    }
                });
    }

    public void getVisitingRecord(final User user) {
        for (int i = 0; i < user.getChild().size(); i++) {
            final Kid kid = user.getChild().get(i);
            // band 연결 아이 데이터의 경우에는 visiting record도 가져온다.
            if (kid.isBandWearing()) {
                final int finalI = i;
                APIClient.getClient().getChildVisitingRecords(kid.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Response<List<VisitingRecord>>>() {
                            @Override
                            public void onNext(@NonNull Response<List<VisitingRecord>> userResponse) {
                                if (userResponse.getResult().equals("success")) {
                                    // 최근 값만 가져온다.
                                    // TODO : 가라 데이터라서.. 밴드 미착용으로 바꾸겠음
                                    if (userResponse.getData().size() > 0)
                                        kid.setVisitingRecord(userResponse.getData().get(0));
                                    else kid.setBandWearing(false);
                                } else
                                    Log.i("connect", "get child visiting record 에 문제가 발생하였습니다.");
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                Log.e("connect", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                // visiting 정보를 추가한 아이를 업데이트 한다.
                                user.upDateChild(finalI, kid);
                                childVisitingCheckCount++;
                                if (childVisitingCheckCount == kids.size()) {
                                    // 모두 완료되었을 경우
                                    adapter.setKids(kids);
                                    adapter.notifyDataSetChanged();
                                    LoadingUtil.stopLoading(indicator);
                                }
                            }
                        });
            } else childVisitingCheckCount++;
        }
    }

    @Override
    public void reload() {
        LoadingUtil.startLoading(indicator);
        connectKids();
    }

    public void connectKids() {
        if (SharedManager.getInstance().getUser().getIsAuthor()) {
            if (kids == null)
                connectAllChild();
            else
                LoadingUtil.stopLoading(indicator);
        }else {
            // TODO : 여기서 계속 오류 뜸
            kids = SharedManager.getInstance().getUser().getChild();
            if(kids.size() == 0) {
                presentDialog();
            } else {
                adapter.setKids(kids);
            }
            adapter.notifyDataSetChanged();
            LoadingUtil.stopLoading(indicator);
        }
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
