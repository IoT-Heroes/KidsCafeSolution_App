package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.Model.Food;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidFoodViewHolder extends ViewHolderParent {

    FlowLayout flowlayout;

    public KidFoodViewHolder(View itemView) {
        super(itemView);

        flowlayout = (FlowLayout)itemView.findViewById(R.id.flowlayout);
    }

    public void initViewHolder(Context context, List<Food> eatableFoodList) {
        // TODO : notifyDataSetChanged될때 마다 불려서 막아놓음. 나중엔 특정 cell만 update하는 구조로 변경할 수 있으면 할 것
        if (flowlayout.getChildCount() == 0) {
            for (Food food : eatableFoodList) {
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(dip2px(context, 10), 0, dip2px(context, 10), dip2px(context, 10));

                TextView tv = new TextView(context);
                tv.setPadding(dip2px(context, 15), 0, dip2px(context, 15), 0);
                tv.setTextColor(Color.parseColor("#FFFFFF"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setText(food.getName());
                tv.setBackgroundResource(R.drawable.label_band);
                flowlayout.addView(tv, lp);
            }
            flowlayout.relayoutToAlign();
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
