package com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public abstract class TabParentFragment extends Fragment {
    // reload : 웹서버와 다시 접속 / refresh : 캐쉬된 내용 보여준다.
    public abstract void reload();

    public void willBeDisplayed() {
        fadeInAnim(this.getView());
    }

    void fadeInAnim(final View view) {
        final Animation animationFadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        view.startAnimation(animationFadeOut);
    }
}
