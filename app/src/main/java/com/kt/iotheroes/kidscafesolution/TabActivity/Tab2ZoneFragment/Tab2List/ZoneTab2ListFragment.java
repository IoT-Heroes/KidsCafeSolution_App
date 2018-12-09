package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Tab2List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Zone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ZoneTab2ListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ZoneTab2ListAdapter adapter;
    private RecyclerView recyclerView;

    private List<Zone> zoneList;

    @SuppressWarnings("unused")
    public static ZoneTab2ListFragment newInstance(int columnCount) {
        ZoneTab2ListFragment fragment = new ZoneTab2ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zone_tab2_list, container, false);

        //Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            zoneList = SharedManager.getInstance().getZonelist();
            if (zoneList == null) {
                connectZoneList();
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Zone item);
    }

    public void connectZoneList() {
        APIClient.getClient().getZoneList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<List<Zone>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<Zone>> userResponse) {
                        if (userResponse.getResult().equals("success")) {
                            if (userResponse.getData().size() > 0) {
                                zoneList = userResponse.getData();
                                SharedManager.getInstance().setZonelist(zoneList);
                            }
                        } else
                            Log.i("connect", "get zone list 에 문제가 발생하였습니다.");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        adapter = new ZoneTab2ListAdapter(zoneList, mListener);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
}
