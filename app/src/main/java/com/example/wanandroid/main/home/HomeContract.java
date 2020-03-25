package com.example.wanandroid.main.home;

import com.example.wanandroid.beans.Banner;
import com.example.wanandroid.beans.Page;

import java.util.List;

public interface HomeContract {

    interface View {
        void showArticles(Page page);

        void showArticlesError();

        void showBanner(List<Banner> data);
    }

    interface Presenter {
        void getArticles(int page);

        void getBanner();
    }
}
