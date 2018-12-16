package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.kt.iotheroes.kidscafesolution.AR.UnityPlayerActivity;
import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;
import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidNoBandViewHolder extends ViewHolderParent {
    static final int PICK_CONTACT_REQUEST = 1;

    Button button_connect_band;

    public KidNoBandViewHolder(View itemView) {
        super(itemView);

        button_connect_band = (Button)itemView.findViewById(R.id.button_connect_band);
    }

    public void initViewHolder(final Activity activity, final String id) {
        button_connect_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UnityPlayerActivity.class);
                intent.putExtra("page", activity.getString(R.string.AR_CONNECT));
                intent.putExtra("kidId", id);
                activity.startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });
    }
}
