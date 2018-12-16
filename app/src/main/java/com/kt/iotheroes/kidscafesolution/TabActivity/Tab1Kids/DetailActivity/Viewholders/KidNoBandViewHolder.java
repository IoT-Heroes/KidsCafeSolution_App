package com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.DetailActivity.Viewholders;

import android.view.View;
<<<<<<< Updated upstream
=======
import android.widget.Button;
>>>>>>> Stashed changes

import com.kt.iotheroes.kidscafesolution.Util.ParentView.ViewHolderParent;

/**
 * Created by mijeong on 2018. 12. 5..
 */

public class KidNoBandViewHolder extends ViewHolderParent {
    public KidNoBandViewHolder(View itemView) {
        super(itemView);
<<<<<<< Updated upstream
=======

        button_connect_band = (Button)itemView.findViewById(R.id.button_connect_band);
    }

    public void initViewHolder(final Activity activity, final String id) {
        button_connect_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UnityPlayerActivity.class);
                intent.putExtra("page", activity.getString(R.string.AR_CONNECT));
                intent.putExtra("kidId", id);
                activity.startActivity(intent);
            }
        });
>>>>>>> Stashed changes
    }
}
