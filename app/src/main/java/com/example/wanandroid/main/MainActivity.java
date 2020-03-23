package com.example.wanandroid.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.library.app.BaseActivity;
import com.example.library.app.IView;
import com.example.wanandroid.R;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected MainPresenter loadPresenter() {
        return new MainPresenter();
    }
}
