package com.example.library.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView {

    protected P mPresenter;

    protected abstract int getLayoutId();

    protected abstract void onBindView(@Nullable Bundle savedInstanceState);

    protected abstract P loadPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mPresenter = loadPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        onBindView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
