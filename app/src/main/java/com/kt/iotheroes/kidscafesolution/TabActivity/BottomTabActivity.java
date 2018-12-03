package com.kt.iotheroes.kidscafesolution.TabActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.Tab1KidsFargment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Tab2ZoneFargment;

import java.util.ArrayList;

public class BottomTabActivity extends AppCompatActivity {

    private ArrayList<TabParentFragment> fragments = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        TabParentFragment currentFragment;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_kids:
                    currentFragment = fragments.get(0);
                    break;
                case R.id.navigation_zone:
                    currentFragment = fragments.get(1);
                    break;
            }

            Log.i("tag", "click id : " + item.getItemId());
            return loadFragment(currentFragment);
        }

    };

    private boolean loadFragment(TabParentFragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();

            Log.i("tag", "change id : " + fragment.getId());

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        fragments.add(Tab1KidsFargment.newInstance(R.id.navigation_kids));
        fragments.add(Tab2ZoneFargment.newInstance(R.id.navigation_zone));

        loadFragment(fragments.get(0));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
