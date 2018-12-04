package com.kt.iotheroes.kidscafesolution.Util.Dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mijeong on 2018. 12. 4..
 */

public class ViewHolderParent extends RecyclerView.ViewHolder {
    public View container;

    public ViewHolderParent(View itemView) {
        super(itemView);
        container = itemView;
    }
}
