package com.example.wanandroid.login;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.User;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void login(String userName, String password) {
        DataRepository.getInstance()
                .login(userName, password)
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
