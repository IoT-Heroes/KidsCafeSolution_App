package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.ListView;

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

//    final static int[] color = new int[]{R.color.zoneBarColor1};
    final static int[] color = new int[]{0xFF563456, 0xFF873F56, 0xFF343456};

    BarChart visitZoneChart;
    ListView listView;

    public KidVisitZoneViewHolder(View itemView) {
        super(itemView);

        visitZoneChart = (BarChart)itemView.findViewById(R.id.chart_visit_zone);
        listView = (ListView)itemView.findViewById(R.id.list_item);

        // TODO : 방문 활동량 찾아와서 보여주기.
//        visitZoneChart.addBar(new BarModel(2.3f, 0xFF123456));
//        visitZoneChart.addBar(new BarModel(2.f,  0xFF343456));
//        visitZoneChart.addBar(new BarModel(3.3f, 0xFF563456));
//        visitZoneChart.addBar(new BarModel(1.1f, 0xFF873F56));
//        visitZoneChart.addBar(new BarModel(2.7f, 0xFF56B7F1));
//        visitZoneChart.addBar(new BarModel(2.f,  0xFF343456));
//        visitZoneChart.addBar(new BarModel(0.4f, 0xFF1FF4AC));
//        visitZoneChart.addBar(new BarModel(4.f,  0xFF1BA4E6));

//        visitZoneChart.startAnimation();
    }

    public void initViewHolder(List<UsingZone> zoneDatas) {
        int colorIdx = 0;

        for (UsingZone data : zoneDatas) {
            visitZoneChart.addBar(new BarModel(data.getCount(), color[colorIdx]));
            if (++colorIdx == color.length)
                colorIdx = 0;
        }

        visitZoneChart.startAnimation();
    }
}
