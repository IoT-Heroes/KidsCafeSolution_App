package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
import android.widget.Button;

import com.kt.iotheroes.kidscafesolution.ParentView.ViewHolderParent;
import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidNoBandViewHolder extends ViewHolderParent {

    Button button_connect_band;

    public KidNoBandViewHolder(View itemView) {
        super(itemView);

        button_connect_band = (Button)itemView.findViewById(R.id.button_connect_band);
    }

    public void initViewHolder() {
        button_connect_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : 키즈 밴드 연결 화면으로 넘어가기.
            }
        });
    }
}
