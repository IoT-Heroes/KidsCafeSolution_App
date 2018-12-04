package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class KidsViewHolder extends ViewHolderParent {
    TextView text_name, text_band, text_age, text_height, text_weight;
    ImageView image_sex;

    public KidsViewHolder(View itemView) {
        super(itemView);

        text_name = (TextView) itemView.findViewById(R.id.text_name);
        text_band = (TextView) itemView.findViewById(R.id.text_band);
        text_age = (TextView) itemView.findViewById(R.id.text_age);
        text_height = (TextView) itemView.findViewById(R.id.text_height);
        text_weight = (TextView) itemView.findViewById(R.id.text_weight);
        image_sex = (ImageView) itemView.findViewById(R.id.image_sex);
    }
}
