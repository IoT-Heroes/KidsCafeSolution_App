package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

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
}
