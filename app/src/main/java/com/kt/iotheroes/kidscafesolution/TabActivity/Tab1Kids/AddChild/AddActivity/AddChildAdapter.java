package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class AddChildAdapter extends RecyclerView.Adapter<ViewHolderParent> {
    private static final int TYPE_ITEM_INPUT = 0;
    private static final int TYPE_ITEM_CHECKLIST = 1;

    private Context context;
    private AddChildActivity activity;
    private View.OnClickListener clickListener;
    private LinearLayout indicator;
    private List<String> foods = new ArrayList<>();

    public AddChildAdapter(AddChildActivity activity) {
        this.activity = activity;
    }

    public AddChildAdapter(AddChildActivity fragment, View.OnClickListener clickListener) {
        this.activity = fragment;
        this.clickListener = clickListener;
    }

    public AddChildAdapter(Context mContext, AddChildActivity mActivity, LinearLayout mIndicator) {
        context = mContext;
        activity = mActivity;
        indicator = mIndicator;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    @Override
    public ViewHolderParent onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_INPUT) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_add_child_input, parent, false);
            return new InputInfoViewHolder(v);
        } else if (viewType == TYPE_ITEM_CHECKLIST) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_add_child_select, parent, false);
            return new SelectInfoViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderParent holder, final int position) {

        if (holder instanceof InputInfoViewHolder) {
            InputInfoViewHolder viewHolderParent = (InputInfoViewHolder)holder;
        }
        else if (holder instanceof SelectInfoViewHolder) {
            SelectInfoViewHolder viewHolderParent = (SelectInfoViewHolder)holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_ITEM_INPUT;
        else return TYPE_ITEM_CHECKLIST;
    }

    @Override
    public int getItemCount() {
        return 1 + foods.size();
    }
}
