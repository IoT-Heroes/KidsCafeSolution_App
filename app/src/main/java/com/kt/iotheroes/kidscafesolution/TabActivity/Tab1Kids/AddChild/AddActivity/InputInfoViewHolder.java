package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class InputInfoViewHolder extends ViewHolderParent {

    public EditText edit_name, edit_birth, edit_height, edit_weight;
    public RadioGroup radio_group;
    public RadioButton radio_btn_girl, radio_btn_boy;

    public InputInfoViewHolder(View itemView) {
        super(itemView);

        edit_name = (EditText)itemView.findViewById(R.id.edit_name);
        edit_birth = (EditText)itemView.findViewById(R.id.edit_birth);
        edit_height = (EditText)itemView.findViewById(R.id.edit_height);
        edit_weight = (EditText)itemView.findViewById(R.id.edit_weight);
        radio_group = (RadioGroup)itemView.findViewById(R.id.radio_group);
        radio_btn_boy = (RadioButton)itemView.findViewById(R.id.radio_btn_boy);
        radio_btn_girl = (RadioButton)itemView.findViewById(R.id.radio_btn_girl);
    }

    public String getName() {
        return edit_name.getText().toString();
    }

    public String getHeight() {
        return edit_height.getText().toString();
    }

    public String getWeight() {
        return edit_weight.getText().toString();
    }
}
