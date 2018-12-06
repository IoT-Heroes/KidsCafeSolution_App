package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

import org.eazegraph.lib.charts.ValueLineChart;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidPulseViewHolder extends ViewHolderParent {

    ValueLineChart pulseChart;

    public KidPulseViewHolder(View itemView) {
        super(itemView);

        pulseChart = (ValueLineChart)itemView.findViewById(R.id.chart_pulse);
    }
}
