package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class KidDetailAdapter extends RecyclerView.Adapter<ViewHolderParent> {
    private static final int TYPE_ITEM_INFO = 0;
    private static final int TYPE_ITEM_FOOD = 1;
    private static final int TYPE_ITEM_TIME = 2;
    private static final int TYPE_ITEM_ACTIVITY = 3;
    private static final int TYPE_ITEM_PULSE = 4;
    private static final int TYPE_ITEM_VISIT_ZONE = 5;
    private static final int TYPE_ITEM_NO_BAND = 6;

    public Context context;
    public KidDetailActivity activity;
    public LinearLayout indicator;

    public KidDetailAdapter(Context mContext, KidDetailActivity mActivity, LinearLayout mIndicator) {
        context = mContext;
        activity = mActivity;
        indicator = mIndicator;
    }

    @Override
    public ViewHolderParent onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TYPE_ITEM_INFO) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_taste_analyst_item_keyword, parent, false);
//            return new AnalystKeywordViewHolder(v);
//        } else if (viewType == TYPE_ITEM_TASTE) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_taste_analyst_item_taste, parent, false);
//            return new AnalystTasteViewHolder(v);
//        } else if (viewType == TYPE_ITEM_COUNTRY) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_taste_analyst_item_country, parent, false);
//            return new AnalystCountryViewHolder(v);
//        } else if (viewType == TYPE_ITEM_INGREDIENT) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_taste_analyst_item_ingredient, parent, false);
//            return new AnalystIngredientViewHolder(v);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderParent holder, final int position) {
//        if (holder instanceof AnalystKeywordViewHolder) {
//            AnalystKeywordViewHolder viewHolderParent = (AnalystKeywordViewHolder)holder;
//            viewHolderParent.initViewHolder(analyst, context);
//        }
//        else if (holder instanceof AnalystTasteViewHolder) {
//            AnalystTasteViewHolder viewHolderParent = (AnalystTasteViewHolder)holder;
//            viewHolderParent.initViewHolder(analyst, context);
//        }
//        else if (holder instanceof AnalystCountryViewHolder) {
//            holder.container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(v, position);
//                }
//            });
//            AnalystCountryViewHolder viewHolderParent = (AnalystCountryViewHolder)holder;
//            viewHolderParent.initViewHolder(analyst, context);
//        }
//        else if (holder instanceof AnalystIngredientViewHolder) {
//            holder.container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(v, position);
//                }
//            });
//            AnalystIngredientViewHolder viewHolderParent = (AnalystIngredientViewHolder)holder;
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
//        if (position == 0)
//            return TYPE_ITEM_INFO;
//        else if (position == 1)
//            return TYPE_ITEM_TASTE;
//        else if (position == 2)
//            return TYPE_ITEM_COUNTRY;
//        else
//            return TYPE_ITEM_INGREDIENT;
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
