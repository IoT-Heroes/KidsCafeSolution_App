package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.Model.VisitingRecord;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.TimeFormatter.TimeFormmater;

import java.util.Date;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidTimeViewHolder extends ViewHolderParent {

    TextView text_entrance, text_exit, text_cost;

    public KidTimeViewHolder(View itemView) {
        super(itemView);

        text_entrance = (TextView)itemView.findViewById(R.id.text_entrance);
        text_exit = (TextView)itemView.findViewById(R.id.text_exit);
        text_cost = (TextView)itemView.findViewById(R.id.text_cost);
    }

    public void initViewHolder(VisitingRecord visitingRecord) {
        Date startDate = TimeFormmater.getDateFromString(visitingRecord.getStartDate());
        String startTime = TimeFormmater.getTime(startDate);
        text_entrance.setText(startTime);
        Date endDate = TimeFormmater.getDateFromString(visitingRecord.getEndDate());
        String endTime = TimeFormmater.getTime(endDate);
        text_exit.setText(endTime);
        text_cost.setText(Integer.toString(visitingRecord.getAmountPrice()));
    }
}
