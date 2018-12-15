package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.AddChild.AddActivity;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.kt.iotheroes.kidscafesolution.Model.Food;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class AddChildAdapter extends RecyclerView.Adapter<ViewHolderParent> {
    private static final int TYPE_ITEM_INPUT = 0;
    private static final int TYPE_ITEM_CHECKLIST = 1;

    private Context context;
    private AddChildActivity activity;
    private LinearLayout indicator;
    private List<Food> foods = new ArrayList<>();
    private Map<Integer, String> inputs = new HashMap<>();

    public Map<Integer, String> getInputs() {
        return inputs;
    }

    public List<Food> getSelectedFoods() {
        List<Food> selectedFoods = new ArrayList<>();
        for (Food food : foods) {
            if (food.isChecked())
                selectedFoods.add(food);
        }
        return selectedFoods;
    }

    public AddChildAdapter(AddChildActivity activity) {
        this.activity = activity;
        inputs.put(R.id.radio_group, "W");
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public ViewHolderParent onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_INPUT) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_add_child_input, parent, false);
            return new InputInfoViewHolder(v);
        } else if (viewType == TYPE_ITEM_CHECKLIST) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_add_child_select, parent, false);
            return new SelectInfoViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderParent holder, final int position) {
        if (holder instanceof InputInfoViewHolder) {
            InputInfoViewHolder viewHolderParent = (InputInfoViewHolder)holder;

            viewHolderParent.edit_name.addTextChangedListener(editListener(R.id.edit_name));
            viewHolderParent.edit_age.addTextChangedListener(editListener(R.id.edit_age));
            viewHolderParent.edit_height.addTextChangedListener(editListener(R.id.edit_height));
            viewHolderParent.edit_weight.addTextChangedListener(editListener(R.id.edit_weight));
            viewHolderParent.radio_group.setOnCheckedChangeListener(radioListener);
        }
        else if (holder instanceof SelectInfoViewHolder) {
            SelectInfoViewHolder viewHolderParent = (SelectInfoViewHolder)holder;

            viewHolderParent.text_food.setText(foods.get(position - 1).getName());
            viewHolderParent.checkBox.setOnClickListener(new CheckBox.OnClickListener() {

                @Override
                public void onClick(View view) {
                    foods.get(position - 1).setChecked(((CheckBox)view).isChecked());
                }
            });
        }
    }

    // editText 데이터 받아올 리스너
    private TextWatcher editListener(final int type) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                inputs.put(type, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    // radioGroup 데이터 받아올 리스너
    private RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.radio_btn_girl)
                inputs.put(R.id.radio_group, "W");
            else
                inputs.put(R.id.radio_group, "M");
        }
    };

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_ITEM_INPUT;
        else return TYPE_ITEM_CHECKLIST;
    }

    @Override
    public int getItemCount() {
        return 1 + foods.size();
    }
}
