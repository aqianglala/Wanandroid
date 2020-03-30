package com.example.wanandroid.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.library.app.BaseActivity;
import com.example.wanandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.textInputLayout)
    TextInputLayout mTextInputLayout;
    @BindView(R.id.textInputLayout2)
    TextInputLayout mTextInputLayout2;

    @OnClick(R.id.btn_login)
    void login() {
        String userName = mTextInputLayout.getEditText().getText().toString().trim();
        String password = mTextInputLayout2.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            mTextInputLayout.setError("用户名为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mTextInputLayout2.setError("用户名为空");
            return;
        }
        mPresenter.login(userName, password);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected LoginPresenter loadPresenter() {
        return null;
    }

}
