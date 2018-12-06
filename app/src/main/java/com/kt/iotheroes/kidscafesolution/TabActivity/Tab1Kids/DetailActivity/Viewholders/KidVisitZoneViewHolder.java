package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.ListView;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

import org.eazegraph.lib.charts.BarChart;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidVisitZoneViewHolder extends ViewHolderParent {

    BarChart visitZoneChart;
    ListView listView;

    public KidVisitZoneViewHolder(View itemView) {
        super(itemView);

        visitZoneChart = (BarChart)itemView.findViewById(R.id.chart_visit_zone);
        listView = (ListView)itemView.findViewById(R.id.list_item);
    }
}
