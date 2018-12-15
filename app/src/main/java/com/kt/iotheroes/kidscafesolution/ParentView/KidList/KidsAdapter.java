package com.kt.iotheroes.kidscafesolution.ParentView.KidList;

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
    private OnItemClickListener onItemClickListener;
    private List<Kid> mDatas = new ArrayList<>();

    public KidsAdapter(KidsListFargment fragment) {
        this.fragment = fragment;
    }

    public KidsAdapter(KidsListFargment fragment, OnItemClickListener onItemClickListener) {
        this.fragment = fragment;
        this.onItemClickListener = onItemClickListener;
    }

    public void addData(Kid kid) {
        mDatas.add(kid);
    }

    public void setKids(List<Kid> kids) {
        this.mDatas = kids;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public KidInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_info, parent, false);
        return new KidInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KidInfoViewHolder holder, final int position) {
        if (onItemClickListener != null)
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });

        Kid kid = mDatas.get(position);
        holder.initViewHolder(kid);
        holder.setSexImage(fragment.getResources().getDrawable(kid.getSex().equals("W") ? R.drawable.woman : R.drawable.man));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
