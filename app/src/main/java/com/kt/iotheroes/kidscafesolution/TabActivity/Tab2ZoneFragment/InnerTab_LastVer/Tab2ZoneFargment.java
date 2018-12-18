//package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment;
//
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.kt.iotheroes.kidscafesolution.Model.Zone;
//import com.kt.iotheroes.kidscafesolution.R;
//import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
//import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.InnerTab_LastVer.Tab1Map.ZoneTab1MapFragment;
//import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.InnerTab_LastVer.Tab2List.ZoneTab2ListFragment;
//import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
//import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
//import com.kt.iotheroes.kidscafesolution.Util.Loadings.LoadingUtil;
//import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;
//
//// TODO : fragment parent버전 만들고 adapter로 화면 전환 관리하기
//public class Tab2ZoneFargment extends TabParentFragment {
//    private static final String NAVIGATION_ID = "navigationId";
//    private int navigationId;
//
//    private ArrayList<Fragment> fragments = new ArrayList<>();
//    private TabLayout tabLayout;
//    private LinearLayout indicator;
//
//    private List<Zone> zoneList;
//
//    public Tab2ZoneFargment() {}
//
//    //Fragment는 Bundle로 데이터 주고 받음
//    public static Tab2ZoneFargment newInstance(int id) {
//        Tab2ZoneFargment fragment = new Tab2ZoneFargment();
//        Bundle args = new Bundle();
//        args.putInt(NAVIGATION_ID, id);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            navigationId = getArguments().getInt(NAVIGATION_ID);
//        }
//
//        zoneList = SharedManager.getInstance().getZonelist();
//        if (zoneList == null) {
//            connectZoneList();
//        }
//
//        fragments.add(new ZoneTab1MapFragment());
//        fragments.add(new ZoneTab2ListFragment());
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tab2_zone, container, false);
//
//        initView(view);
//        return view;
//    }
//
//    private void initView(View view) {
//        indicator = (LinearLayout)view.findViewById(R.id.indicator);
//        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
//
//        tabLayout.addTab(tabLayout.newTab().setText("지도"));
//        tabLayout.addTab(tabLayout.newTab().setText("목록"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final FragmentTransaction childFt = getChildFragmentManager().beginTransaction();
//        childFt.add(R.id.child_fragment_container, new ZoneTab1MapFragment()).commit();
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                setChildFragment(fragments.get(tab.getPosition()));
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }
//
//    private void setChildFragment(Fragment child) {
//        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();
//
//        if (!child.isAdded()) {
//            childFt.replace(R.id.child_fragment_container, child);
//            childFt.addToBackStack(null);
//            childFt.commit();
//        }
//    }
//
//    @Override
//    public void reload() {
//
//    }
//
//    public void connectZoneList() {
//        LoadingUtil.startLoading(indicator);
//        APIClient.getClient().getZoneList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<Response<List<Zone>>>() {
//                    @Override
//                    public void onNext(@NonNull Response<List<Zone>> userResponse) {
//                        if (userResponse.getResult().equals("success")) {
//                            if (userResponse.getData().size() > 0) {
//                                zoneList = userResponse.getData();
//                                SharedManager.getInstance().setZonelist(zoneList);
//                            }
//                        } else
//                            Log.i("connect", "get zone list 에 문제가 발생하였습니다.");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        e.printStackTrace();
//                        Log.e("connect", e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LoadingUtil.stopLoading(indicator);
//                    }
//                });
//    }
//}
