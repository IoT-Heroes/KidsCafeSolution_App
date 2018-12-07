package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.Model.Kid;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class KidInfoViewHolder extends ViewHolderParent {
    TextView text_name, text_band, text_age, text_height, text_weight;
    ImageView image_sex;

    public KidInfoViewHolder(View itemView) {
        super(itemView);

        text_name = (TextView) itemView.findViewById(R.id.text_name);
        text_band = (TextView) itemView.findViewById(R.id.text_band);
        text_age = (TextView) itemView.findViewById(R.id.text_age);
        text_height = (TextView) itemView.findViewById(R.id.text_height);
        text_weight = (TextView) itemView.findViewById(R.id.text_weight);
        image_sex = (ImageView) itemView.findViewById(R.id.image_sex);
    }

    public void initViewHolder(Kid kid) {
        text_name.setText(kid.getName());
        text_age.setText(kid.getAge() + "세");
        text_weight.setText(kid.getWeight() + "kg");
        text_height.setText(kid.getHeight() + "cm");

        if (!kid.isWearingBand()) text_band.setVisibility(View.GONE);
    }

    public void setSexImage(Drawable image) {
        image_sex.setImageDrawable(image);
    }
}
