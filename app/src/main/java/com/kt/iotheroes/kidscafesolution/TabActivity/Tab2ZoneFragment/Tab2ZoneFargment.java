package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;

public class Tab2ZoneFargment extends TabParentFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAVIGATION_ID = "navigationId";

    private int navigationId;

    private OnFragmentInteractionListener mListener;

    public Tab2ZoneFargment() {
        // Required empty public constructor
    }

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
        return inflater.inflate(R.layout.fragment_tab2_zone, container, false);
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
