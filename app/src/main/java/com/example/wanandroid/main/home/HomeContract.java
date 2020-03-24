package com.example.wanandroid.main.home;

import com.example.wanandroid.beans.Page;

public interface HomeContract {

    interface View {
        void showArticles(Page page);

        void showError();
    }

    interface Presenter {
        void getArticles(int page);
    }
}
