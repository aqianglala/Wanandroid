package com.example.wanandroid.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.library.app.BaseActivity;
import com.example.library.app.BasePresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.main.home.HomeFragment;
import com.example.wanandroid.main.knowledge.KnowledgeFragment;
import com.example.wanandroid.main.navigation.NavigationFragment;
import com.example.wanandroid.main.project.ProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    private Fragment mCurrentFragment;
    private HomeFragment mHomeFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHomeFragment = new HomeFragment();
        mCurrentFragment = mHomeFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                mCurrentFragment).commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        if (mHomeFragment == null) {
                            mHomeFragment = new HomeFragment();
                        }
                        mCurrentFragment = mHomeFragment;
                        break;
                    case R.id.navigation_knowledge:
                        if (mKnowledgeFragment == null) {
                            mKnowledgeFragment = new KnowledgeFragment();
                        }
                        mCurrentFragment = mKnowledgeFragment;
                        break;
                    case R.id.navigation_navigation:
                        if (mNavigationFragment == null) {
                            mNavigationFragment = new NavigationFragment();
                        }
                        mCurrentFragment = mNavigationFragment;
                        break;
                    case R.id.navigation_project:
                        if (mProjectFragment == null) {
                            mProjectFragment = new ProjectFragment();
                        }
                        mCurrentFragment = mProjectFragment;
                        break;
                }
                if (mCurrentFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                            mCurrentFragment).commitAllowingStateLoss();
                }
                return true;
            }
        });
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

}
