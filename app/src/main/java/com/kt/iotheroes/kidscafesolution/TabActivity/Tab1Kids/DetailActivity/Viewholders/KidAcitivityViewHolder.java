package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.kt.iotheroes.kidscafesolution.Model.KidInfo;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidAcitivityViewHolder extends ViewHolderParent {

    private static final String format = "%.0f%%";

    private TextView text_kid_walk, text_walk_goal, text_kid_cal, text_percent;
    private DecoView graph;

    public KidAcitivityViewHolder(View itemView) {
        super(itemView);

        text_kid_walk = (TextView) itemView.findViewById(R.id.text_kid_walk);
        text_walk_goal = (TextView) itemView.findViewById(R.id.text_walk_goal);
        text_kid_cal = (TextView) itemView.findViewById(R.id.text_kid_cal);
        text_percent = (TextView) itemView.findViewById(R.id.text_percent);
        graph = (DecoView) itemView.findViewById(R.id.graph);
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

    public void drawGraph(float value, float seriesMax) {
        SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#ff7473"))
                .setRange(0, seriesMax, 0)
                .setInitialVisibility(false)
                .setLineWidth(40f)
                .setCapRounded(true)
                .setShowPointWhenEmpty(true)
                .build();

        int mSeriesIdx = graph.addSeries(seriesItem);
        addProgressListener(seriesItem, text_percent);

        graph.addEvent(new DecoEvent.Builder(value).setIndex(mSeriesIdx).setDelay(500).build());
    }

    public void initViewHolder(KidInfo kidInfo) {
        // 누적 걸음수를 표시한다.
        drawGraph(kidInfo.getKidTotalWalk(), kidInfo.getKidGoalWalk());
        // 누적 칼로리 소모량을 표시한다.
        text_kid_walk.setText(kidInfo.getKidTotalWalk() + "걸음");
        text_walk_goal.setText(kidInfo.getKidGoalWalk() + "걸음");
        text_kid_cal.setText(kidInfo.getCalorie() + "kcal");
    }
}
