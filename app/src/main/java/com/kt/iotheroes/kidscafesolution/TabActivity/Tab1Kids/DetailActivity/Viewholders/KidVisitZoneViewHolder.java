package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;

import com.kt.iotheroes.kidscafesolution.Model.UsingZone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.List;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidVisitZoneViewHolder extends ViewHolderParent {

    BarChart visitZoneChart;

    public KidVisitZoneViewHolder(View itemView) {
        super(itemView);

        visitZoneChart = (BarChart)itemView.findViewById(R.id.chart_visit_zone);
    }

    public void initViewHolder(List<UsingZone> zoneDatas, int[] colors) {
        int colorIdx = 0;

        for (UsingZone data : zoneDatas) {
            visitZoneChart.addBar(new BarModel(data.getCount(), colors[colorIdx]));
            if (++colorIdx == colors.length)
                colorIdx = 0;
        }

        visitZoneChart.startAnimation();
    }
}
