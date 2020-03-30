package com.example.wanandroid.login;

public interface LoginContract {
    interface View {
//        void onLoginSuccess();
//
//        void onLoginFailed();
    }

    interface Presenter {
        void login(String userName, String password);
    }
}
