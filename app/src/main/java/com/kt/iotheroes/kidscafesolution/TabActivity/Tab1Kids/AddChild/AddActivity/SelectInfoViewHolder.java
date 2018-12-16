package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class SelectInfoViewHolder extends ViewHolderParent {

    public TextView text_food;
    public CheckBox checkBox;

    public SelectInfoViewHolder(View itemView) {
        super(itemView);

        text_food = (TextView) itemView.findViewById(R.id.text_food);
        checkBox = (CheckBox) itemView.findViewById(R.id.check_box);
    }
}
