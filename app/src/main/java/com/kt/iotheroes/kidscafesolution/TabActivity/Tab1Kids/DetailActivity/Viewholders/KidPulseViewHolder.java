package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;

import com.kt.iotheroes.kidscafesolution.Model.Pulse;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;
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

    ValueLineChart pulseChart;

    public KidPulseViewHolder(View itemView) {
        super(itemView);

        pulseChart = (ValueLineChart)itemView.findViewById(R.id.chart_pulse);
    }

    public void initViewHolder(List<Pulse> pulseDatas) {
        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        // 최근 10개만 보여주기
        int getDataSize = pulseDatas.size();
        int setSize = 10 > getDataSize ? getDataSize : 10;

        // 처음과 끝이 그래프 상에서 잘 안보여서 0으로 해주었음
        series.addPoint(new ValueLinePoint("", 0));
        for (int i = getDataSize - setSize; i < getDataSize; i++) {
            Pulse pulse = pulseDatas.get(i);
            Date date = TimeFormmater.getDateFromString(pulse.getDate());
            String time = TimeFormmater.getTime(date);
            series.addPoint(new ValueLinePoint(time, pulse.getAverage()));
        }
        series.addPoint(new ValueLinePoint("", 0));

        pulseChart.addSeries(series);
        pulseChart.startAnimation();
    }
}
