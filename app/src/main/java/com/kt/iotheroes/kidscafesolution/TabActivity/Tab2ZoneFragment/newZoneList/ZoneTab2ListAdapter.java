package com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.newZoneList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.Model.Zone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.newZoneList.ZoneTab2ListFragment.OnListFragmentInteractionListener;

import java.util.List;

public class ZoneTab2ListAdapter extends RecyclerView.Adapter<ZoneTab2ListAdapter.ViewHolder> {

    private List<Zone> mValues;
    private final OnListFragmentInteractionListener mListener;

    public void setmValues(List<Zone> mValues) {
        this.mValues = mValues;
    }

    public ZoneTab2ListAdapter(List<Zone> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_zone, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.text_name.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView text_name;
        public Zone mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            text_name = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + text_name.getText() + "'";
        }
    }
}
