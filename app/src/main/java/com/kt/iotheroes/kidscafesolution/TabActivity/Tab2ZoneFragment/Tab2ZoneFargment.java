package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Test.ZoneTabPagerAdapter;

public class Tab2ZoneFargment extends TabParentFragment {
    private static final String NAVIGATION_ID = "navigationId";
    private int navigationId;
    private OnFragmentInteractionListener mListener;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public Tab2ZoneFargment() {}

    //Fragment는 Bundle로 데이터 주고 받음
    public static Tab2ZoneFargment newInstance(int id) {
        Tab2ZoneFargment fragment = new Tab2ZoneFargment();
        Bundle args = new Bundle();
        args.putInt(NAVIGATION_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            navigationId = getArguments().getInt(NAVIGATION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2_zone, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("지도"));
        tabLayout.addTab(tabLayout.newTab().setText("목록"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)view.findViewById(R.id.view_pager);

        // TODO : Adpater만들기.
        ZoneTabPagerAdapter pagerAdapter = new ZoneTabPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    // Activity에 할당되었을 때 호출되며 인자로 Activity를 넘겨준다.
    //Activity에 이벤트 콜백을 설정
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    // Fragment가 Activity로부터 할당이 제거될 때 호출
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void reload() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
