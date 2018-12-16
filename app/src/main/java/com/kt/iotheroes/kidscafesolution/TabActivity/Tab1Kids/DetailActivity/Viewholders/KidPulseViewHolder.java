package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;

import com.kt.iotheroes.kidscafesolution.Model.KidStatic;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;
import com.kt.iotheroes.kidscafesolution.Util.TimeFormatter.TimeFormmater;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.Date;
import java.util.List;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidPulseViewHolder extends ViewHolderParent {

    private final static int GraphCount = 12;
    ValueLineChart pulseChart;

    public KidPulseViewHolder(View itemView) {
        super(itemView);

        pulseChart = (ValueLineChart)itemView.findViewById(R.id.chart_pulse);
    }

    public void initViewHolder(List<KidStatic> pulseDatas) {
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        // 최근 10개만 보여주기
        int getDataSize = pulseDatas.size();
        int setSize = GraphCount > getDataSize ? getDataSize : GraphCount;

        // 처음과 끝이 그래프 상에서 잘 안보여서 0으로 해주었음 -> 그것도 이상해서 원래대로
//        series.addPoint(new ValueLinePoint("", 0));
        for (int i = getDataSize - setSize; i < getDataSize; i++) {
            KidStatic pulse = pulseDatas.get(i);
            Date date = TimeFormmater.getDateFromString(pulse.getDate());
            String time = TimeFormmater.getTime(date);
            series.addPoint(new ValueLinePoint(time, pulse.getAverage()));
        }
//        series.addPoint(new ValueLinePoint("", 0));

        pulseChart.addSeries(series);
        pulseChart.startAnimation();
    }
}
