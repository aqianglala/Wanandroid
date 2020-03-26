package com.example.wanandroid.main.navigation;

import com.example.wanandroid.beans.Navigation;

import java.util.List;

public interface NavigationContract {

    interface View {
        void showNavigation(List<Navigation> data);

        void showError();
    }

    interface Presenter {
        void getNavigation();
    }
}
