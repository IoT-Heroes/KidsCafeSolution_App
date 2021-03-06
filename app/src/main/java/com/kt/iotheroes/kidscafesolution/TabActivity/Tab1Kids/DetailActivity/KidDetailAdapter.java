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
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidVisitZoneCellViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders.KidVisitZoneViewHolder;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.KidInfoViewHolder;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;
import com.kt.iotheroes.kidscafesolution.Util.Loadings.LoadingUtil;

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
    private static final int TYPE_ITEM_VISIT_ZONE_CELL = 6;
    private static final int TYPE_ITEM_NO_BAND = 7;

    final static int[] visited_bar_color = new int[]{0xFF123456, 0xFF343456, 0xFF563456, 0xFF873F56, 0xFF56B7F1, 0xFF343456, 0xFF1FF4AC, 0xFF1BA4E6};

    private Context context;
    private KidDetailActivity activity;
    private LinearLayout indicator;
    private boolean wearingBand;

    private KidInfo kidInfo;

    public void setKidInfo(KidInfo kidInfo, LinearLayout indicator) {
        this.kidInfo = kidInfo;
        LoadingUtil.stopLoading(indicator);
        this.wearingBand = kidInfo.getKid().isBandWearing();
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
        } else if (viewType == TYPE_ITEM_VISIT_ZONE_CELL && wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_visited_zone_cell, parent, false);
            return new KidVisitZoneCellViewHolder(v);
        } else if (viewType == TYPE_ITEM_NO_BAND && !wearingBand) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_content_no_band, parent, false);
            return new KidNoBandViewHolder(v);
        }
        return null;
    }

    /*
     - 바인딩 문제 발생 : 데이터 받아오기 전 onBindViewHolder가 호출되어 데이터 바인딩이 제대로 되지 않는 문제 발생
     - 임시 해결 : 데이터 호출을 각각 하고 받아올 때 마다 notifyDataSetChanged 호출
     데이터 확인 후, null이 아닐 경우에만 initViewholder 호출
     - TODO : 모든 데이터가 호출이 완료 될 때 까지 indicator가 돌고 호출 완료 시 한번에 그리는 작업
     데이터가 작아 보이는데 문제 없지만, 현재는 너무 비효율적이다.
      */

    @Override
    public void onBindViewHolder(ViewHolderParent holder, final int position) {

        if (holder instanceof KidInfoViewHolder) {
            KidInfoViewHolder viewHolderParent = (KidInfoViewHolder)holder;
            viewHolderParent.initViewHolder(kidInfo.getKid());
            viewHolderParent.setSexImage(activity.getResources().getDrawable(kidInfo.getKid().getSex().equals("W") ? R.drawable.woman : R.drawable.man));
        }
        else if (holder instanceof KidFoodViewHolder) {
            KidFoodViewHolder viewHolderParent = (KidFoodViewHolder)holder;
            viewHolderParent.initViewHolder(context, kidInfo.getKid().getEatableFoodList());
        }
        else if (holder instanceof KidTimeViewHolder) {
            KidTimeViewHolder viewHolderParent = (KidTimeViewHolder)holder;
            if (kidInfo.getKid().getVisitingRecord() != null)
                viewHolderParent.initViewHolder(kidInfo.getKid().getVisitingRecord());
        }
        else if (holder instanceof KidAcitivityViewHolder) {
            KidAcitivityViewHolder viewHolderParent = (KidAcitivityViewHolder)holder;
            if (kidInfo.getActivityDatas() != null)
                viewHolderParent.initViewHolder(kidInfo);
        }
        else if (holder instanceof KidPulseViewHolder) {
            KidPulseViewHolder viewHolderParent = (KidPulseViewHolder)holder;
            if (kidInfo.getPulseDatas() != null)
                viewHolderParent.initViewHolder(kidInfo.getPulseDatas());
        }
        else if (holder instanceof KidVisitZoneViewHolder) {
            KidVisitZoneViewHolder viewHolderParent = (KidVisitZoneViewHolder)holder;
            if (kidInfo.getZoneDatas() != null)
                viewHolderParent.initViewHolder(kidInfo.getZoneDatas(), visited_bar_color);
        }
        else if (holder instanceof KidVisitZoneCellViewHolder) {
            KidVisitZoneCellViewHolder viewHolderParent = (KidVisitZoneCellViewHolder)holder;
            int i = position - TYPE_ITEM_VISIT_ZONE_CELL;
            viewHolderParent.initViewHolder(kidInfo.getZoneDatas().get(i), visited_bar_color[i % kidInfo.getZoneDatas().size()]);
        }
        else if (holder instanceof KidNoBandViewHolder) {
            KidNoBandViewHolder viewHolderParent = (KidNoBandViewHolder)holder;
            int i = position - TYPE_ITEM_VISIT_ZONE_CELL;
            viewHolderParent.initViewHolder(activity, kidInfo.getKid().getId());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!wearingBand && position == 2)
            return TYPE_ITEM_NO_BAND;
        else if (position >= 6)
            return TYPE_ITEM_VISIT_ZONE_CELL;
        else
            return position;
    }

    @Override
    public int getItemCount() {
        if (wearingBand && kidInfo.getZoneDatas() != null)
            return 6 + kidInfo.getZoneDatas().size();
        else if (wearingBand)
            return 6;
        else
            return 3;
    }
}
