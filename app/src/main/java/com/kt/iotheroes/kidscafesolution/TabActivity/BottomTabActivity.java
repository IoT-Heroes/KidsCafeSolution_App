package com.kt.iotheroes.kidscafesolution.TabActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.AR.UnityPlayerActivity;
import com.kt.iotheroes.kidscafesolution.Model.Zone;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Settings.Admin.SettingsAdminActivity;
import com.kt.iotheroes.kidscafesolution.Settings.Parent.SettingsParentActivity;
import com.kt.iotheroes.kidscafesolution.TabActivity.ParentFragment.TabParentFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab1Kids.Tab1KidsFargment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Tab2List.ZoneTab2ListFragment;
import com.kt.iotheroes.kidscafesolution.TabActivity.Tab2ZoneFragment.Tab2ZoneFargment;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.SharedManager;

import java.util.ArrayList;

public class BottomTabActivity extends AppCompatActivity implements ZoneTab2ListFragment.OnListFragmentInteractionListener{

    private ArrayList<TabParentFragment> fragments = new ArrayList<>();
    private FragmentManager fm = getSupportFragmentManager();
    TabParentFragment currentFragment;
    private LinearLayout indicator;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            TabParentFragment beforeFm = currentFragment;

            switch (item.getItemId()) {
                case R.id.navigation_kids:
                    currentFragment = fragments.get(0);
                    break;
                case R.id.navigation_zone:
                    currentFragment = fragments.get(1);
                    break;
            }

            return loadFragment(beforeFm);
        }

    };

    /*
    transaction : 액티비티에 커밋한 변경 내용의 집합
     */
    private boolean loadFragment(TabParentFragment beforeFragment) {
        //switching fragment
        if (currentFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(beforeFragment).show(currentFragment).commit();
            currentFragment.reload();

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        initView();

        // TODO : visitingRecord 호출 다 되기 전까지 indicator 돌리기.
    }

    private void initView() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        indicator = (LinearLayout)findViewById(R.id.indicator);

        // 액션바 왼쪽(홈 버튼) 생성 - android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.vr);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragments.add(Tab1KidsFargment.newInstance(R.id.navigation_kids));
        fragments.add(Tab2ZoneFargment.newInstance(R.id.navigation_zone));

        fm.beginTransaction().add(R.id.content, fragments.get(1)).hide(fragments.get(1)).commit();
        fm.beginTransaction().add(R.id.content, fragments.get(0)).commit();
        currentFragment = fragments.get(0);
    }

    // toolbar menu 등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    // toolbar item 선택 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return moveToSettingActivity();
            case android.R.id.home: // ar
                return moveToARActivity();
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
    }

    private boolean moveToARActivity() {
        Intent intent = new Intent(BottomTabActivity.this, UnityPlayerActivity.class);
        intent.putExtra("page", getString(R.string.AR_INFO));
        startActivity(intent);
        return true;
    }

    private boolean moveToSettingActivity() {
        Intent intent = new Intent(BottomTabActivity.this,
                SharedManager.getInstance().getUser().getIsAuthor() ? SettingsAdminActivity.class : SettingsParentActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Zone item) {
        Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_SHORT).show();
        // TODO : zone detail 구현 (3순위)
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentFragment.reload();
    }
}
