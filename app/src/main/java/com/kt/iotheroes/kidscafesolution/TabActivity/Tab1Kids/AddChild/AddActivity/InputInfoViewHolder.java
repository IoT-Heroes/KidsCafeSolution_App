package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class InputInfoViewHolder extends ViewHolderParent {

    public EditText edit_name, edit_age, edit_height, edit_weight;
    public RadioGroup radio_group;

    public InputInfoViewHolder(View itemView) {
        super(itemView);

        edit_name = (EditText)itemView.findViewById(R.id.edit_name);
        edit_age = (EditText)itemView.findViewById(R.id.edit_age);
        edit_height = (EditText)itemView.findViewById(R.id.edit_height);
        edit_weight = (EditText)itemView.findViewById(R.id.edit_weight);
        radio_group = (RadioGroup)itemView.findViewById(R.id.radio_group);
    }

    public String getName() {
        return edit_name.getText().toString();
    }

    public int getHeight() {
        return Integer.parseInt(edit_height.getText().toString());
    }

    public int getWeight() {
        return Integer.parseInt(edit_weight.getText().toString());
    }

    public int getAge() {
        return Integer.parseInt(edit_age.getText().toString());
    }

    public String getSex(int selectedRadioButtonId) {
        if (selectedRadioButtonId == R.id.radio_btn_boy) return "M";
        else return "W";
    }
}
