package com.kt.iotheroes.kidscafesolution.Util.ParentView.KidList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.KidInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class KidsAdapter extends RecyclerView.Adapter<KidInfoViewHolder> {

    private KidsListFargment fragment;
    private View.OnClickListener clickListener;
    private List<Kid> mDatas = new ArrayList<>();

    public KidsAdapter(KidsListFargment fragment) {
        this.fragment = fragment;
    }

    public KidsAdapter(KidsListFargment fragment, View.OnClickListener clickListener) {
        this.fragment = fragment;
        this.clickListener = clickListener;
    }

    public void setKids(List<Kid> kids) {
        this.mDatas = kids;
    }

    @Override
    public KidInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_info, parent, false);
        return new KidInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KidInfoViewHolder holder, int position) {
        if (clickListener != null)
            holder.container.setOnClickListener(clickListener);
        holder.initViewHolder(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}