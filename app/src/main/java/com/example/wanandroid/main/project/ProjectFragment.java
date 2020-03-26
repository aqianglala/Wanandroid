package com.example.wanandroid.main.project;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library.app.BaseFragment;
import com.example.wanandroid.R;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.main.project.list.ProjectListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_project;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTabLayout.setupWithViewPager(mViewPager);
        mPresenter.getProject();
    }

    @Override
    protected ProjectPresenter loadPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public void onGetProjectSuccess(List<Chapter> data) {
        List<Fragment> mFragments = new ArrayList<>();
        for (Chapter chapter : data) {
            ProjectListFragment fragment = new ProjectListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("cid", chapter.getId());
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return data.get(position).getName();
            }
        });
    }

    @Override
    public void onGetProjectFailed() {

    }
}
