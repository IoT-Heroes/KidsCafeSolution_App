package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;

import java.util.ArrayList;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class KidsAdapter extends RecyclerView.Adapter<KidsViewHolder> {

    private Tab1KidsFargment fragment;
    private View.OnClickListener clickListener;
    private ArrayList<Kid> mDatas;

    public KidsAdapter(Tab1KidsFargment fragment, View.OnClickListener clickListener) {
        this.fragment = fragment;
        this.clickListener = clickListener;
    }

    public void setKids(ArrayList<Kid> kids) {
        this.mDatas = kids;
    }

    @Override
    public KidsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_info, parent, false);
        return new KidsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KidsViewHolder holder, int position) {
        holder.container.setOnClickListener(clickListener);

        Kid kid = mDatas.get(position);
        holder.text_name.setText(kid.getName());
        holder.text_age.setText(kid.getAge() + "세");
        holder.text_weight.setText(kid.getWeight() + "kg");
        holder.text_height.setText(kid.getHeight() + "cm");
        if (!kid.isWearingBand()) holder.text_band.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
