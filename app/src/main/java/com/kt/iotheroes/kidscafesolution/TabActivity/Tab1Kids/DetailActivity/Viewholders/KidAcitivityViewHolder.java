package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidAcitivityViewHolder extends ViewHolderParent {

    private static final float seriesMax = 50f;
    private static final String format = "%.0f%%";

    private TextView text_walk, text_cal;
    private DecoView graph_walk, graph_cal;

    public KidAcitivityViewHolder(View itemView) {
        super(itemView);

        text_walk = (TextView) itemView.findViewById(R.id.text_walk);
        text_cal = (TextView) itemView.findViewById(R.id.text_cal);
        graph_cal = (DecoView) itemView.findViewById(R.id.graph_cal);
        graph_walk = (DecoView) itemView.findViewById(R.id.graph_walk);

        drawGraph(graph_walk);
    }

    public void addProgressListener(@NonNull final SeriesItem seriesItem, @NonNull final TextView textView, float maxValue) {
        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = (currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue());
                textView.setText(String.format(format, percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
    }

    public void drawGraph(DecoView graph) {
    }
}
