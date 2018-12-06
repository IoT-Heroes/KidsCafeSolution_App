package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidAcitivityViewHolder extends ViewHolderParent {

    private static final float seriesMax = 100f;
    private static final String format = "%.0f%%";

    private TextView text_percent_walk, text_percent_cal;
    private DecoView graph_walk, graph_cal;

    public KidAcitivityViewHolder(View itemView) {
        super(itemView);

        text_percent_walk = (TextView) itemView.findViewById(R.id.text_percent_walk);
        text_percent_cal = (TextView) itemView.findViewById(R.id.text_percent_cal);
        graph_cal = (DecoView) itemView.findViewById(R.id.graph_cal);
        graph_walk = (DecoView) itemView.findViewById(R.id.graph_walk);

        int colorRedPink = Color.parseColor("#ff7473");
        int colorSkyBlue = Color.parseColor("#47b8e0");

        drawGraph(graph_walk, text_percent_walk, colorRedPink, 50);
        drawGraph(graph_cal, text_percent_cal, colorSkyBlue, 30);
    }

    public void addProgressListener(@NonNull final SeriesItem seriesItem, @NonNull final TextView textView) {
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

    public void drawGraph(DecoView graph, TextView textView, int color, int value) {
        SeriesItem seriesItem = new SeriesItem.Builder(color)
                .setRange(0, seriesMax, 0)
                .setInitialVisibility(false)
                .setLineWidth(40f)
                .setCapRounded(true)
                .setShowPointWhenEmpty(true)
                .build();

        int mSeriesIdx = graph.addSeries(seriesItem);
        addProgressListener(seriesItem, textView);

        graph.addEvent(new DecoEvent.Builder(value).setIndex(mSeriesIdx).setDelay(500).build());
    }
}
