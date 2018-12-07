package com.kt.iotheroes.kidscafesolution.TabActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.Tab1KidsFargment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Tab2ZoneFargment;

import java.util.ArrayList;

public class BottomTabActivity extends AppCompatActivity {

    private ArrayList<TabParentFragment> fragments = new ArrayList<>();
    private FragmentManager fm = getSupportFragmentManager();
    TabParentFragment currentFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_kids:
                    loadFragment(fragments.get(0));
                    currentFragment = fragments.get(0);
                    break;
                case R.id.navigation_zone:
                    loadFragment(fragments.get(1));
                    currentFragment = fragments.get(1);
                    break;
            }

            return loadFragment(currentFragment);
        }

    };

    /*
    transaction : 액티비티에 커밋한 변경 내용의 집합
     */
    //switching fragment
    private boolean loadFragment(TabParentFragment newFragment) {
        //switching fragment
        if (newFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(currentFragment).show(newFragment).commit();

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragments.add(Tab1KidsFargment.newInstance(R.id.navigation_kids));
        fragments.add(Tab2ZoneFargment.newInstance(R.id.navigation_zone));

        fm.beginTransaction().add(R.id.content, fragments.get(1)).hide(fragments.get(1)).commit();
        fm.beginTransaction().add(R.id.content, fragments.get(0)).commit();
        currentFragment = fragments.get(0);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        currentFragment.reload();
    }
}
