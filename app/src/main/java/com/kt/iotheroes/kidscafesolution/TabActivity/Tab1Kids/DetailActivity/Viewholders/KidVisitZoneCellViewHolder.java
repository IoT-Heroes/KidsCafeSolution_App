package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.Model.UsingZone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidVisitZoneCellViewHolder extends ViewHolderParent {

    ImageView image_color;
    TextView text_room_name, text_room_count;

    public KidVisitZoneCellViewHolder(View itemView) {
        super(itemView);

        image_color = (ImageView)itemView.findViewById(R.id.image_color);
        text_room_name = (TextView)itemView.findViewById(R.id.text_room_name);
        text_room_count = (TextView)itemView.findViewById(R.id.text_room_count);
    }

    public void initViewHolder(UsingZone zoneData, int color) {
        image_color.setColorFilter(color);
        text_room_name.setText(zoneData.getZoneName());
        text_room_count.setText(zoneData.getCount() + "회");
    }
}
