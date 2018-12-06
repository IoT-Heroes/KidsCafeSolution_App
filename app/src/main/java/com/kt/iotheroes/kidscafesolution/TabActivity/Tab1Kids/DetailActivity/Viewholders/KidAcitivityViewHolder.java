package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidAcitivityViewHolder extends ViewHolderParent {

    private TextView text_walk, text_cal;
    private DecoView graph_walk, graph_cal;

    public KidAcitivityViewHolder(View itemView) {
        super(itemView);

        text_walk = (TextView) itemView.findViewById(R.id.text_walk);
        text_cal = (TextView) itemView.findViewById(R.id.text_cal);
        graph_cal = (DecoView) itemView.findViewById(R.id.graph_cal);
        graph_walk = (DecoView) itemView.findViewById(R.id.graph_walk);
    }
}
