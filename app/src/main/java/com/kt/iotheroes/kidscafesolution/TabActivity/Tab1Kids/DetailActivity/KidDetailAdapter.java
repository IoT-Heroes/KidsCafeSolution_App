package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kt.iotheroes.kidscafesolution.Model.KidInfo;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidAcitivityViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidFoodViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidNoBandViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidPulseViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidTimeViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidVisitZoneViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.KidInfoViewHolder;
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

    private Context context;
    private KidDetailActivity activity;
    private LinearLayout indicator;
    private boolean wearingBand;

    private KidInfo kidInfo;

    public void setKidInfo(KidInfo kidInfo) {
        this.kidInfo = kidInfo;
    }

    public KidDetailAdapter(Context mContext, KidDetailActivity mActivity, LinearLayout mIndicator, boolean wearingBand) {
        context = mContext;
        activity = mActivity;
        indicator = mIndicator;
        this.wearingBand = wearingBand;
    }

    @Override
    public ViewHolderParent onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_INFO) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_info, parent, false);
            return new KidInfoViewHolder(v);
        } else if (viewType == TYPE_ITEM_FOOD) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_eatable_food, parent, false);
            return new KidFoodViewHolder(v);
        } else if (viewType == TYPE_ITEM_TIME && wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_time, parent, false);
            return new KidTimeViewHolder(v);
        } else if (viewType == TYPE_ITEM_ACTIVITY && wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_activity, parent, false);
            return new KidAcitivityViewHolder(v);
        } else if (viewType == TYPE_ITEM_PULSE && wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_pulse, parent, false);
            return new KidPulseViewHolder(v);
        } else if (viewType == TYPE_ITEM_VISIT_ZONE && wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_visited_zone, parent, false);
            return new KidVisitZoneViewHolder(v);
        } else if (viewType == TYPE_ITEM_NO_BAND && !wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_no_band, parent, false);
            return new KidNoBandViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderParent holder, final int position) {

        if (holder instanceof KidInfoViewHolder) {
            KidInfoViewHolder viewHolderParent = (KidInfoViewHolder)holder;
            viewHolderParent.initViewHolder(kidInfo.kid);
        }
        else if (holder instanceof KidFoodViewHolder) {
            KidFoodViewHolder viewHolderParent = (KidFoodViewHolder)holder;
        }
        else if (holder instanceof KidTimeViewHolder) {
            KidTimeViewHolder viewHolderParent = (KidTimeViewHolder)holder;
        }
        else if (holder instanceof KidAcitivityViewHolder) {
            KidAcitivityViewHolder viewHolderParent = (KidAcitivityViewHolder)holder;
        }
        else if (holder instanceof KidPulseViewHolder) {
            KidPulseViewHolder viewHolderParent = (KidPulseViewHolder)holder;
//            viewHolderParent.initViewHolder()
        }
        else if (holder instanceof KidVisitZoneViewHolder) {
            KidVisitZoneViewHolder viewHolderParent = (KidVisitZoneViewHolder)holder;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (!wearingBand && position == 2)
            return TYPE_ITEM_NO_BAND;
        else return position;
    }

    @Override
    public int getItemCount() {
        return wearingBand ? 5 : 3;
    }
}
