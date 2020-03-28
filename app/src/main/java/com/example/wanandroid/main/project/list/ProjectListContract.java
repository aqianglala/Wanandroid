package com.example.wanandroid.main.project.list;

import com.example.wanandroid.beans.Page;

public interface ProjectListContract {

    interface View {
        void showArticles(Page page);

        void showArticlesError();
    }

    interface Presenter {
        void getArticles(int page, int cid);
    }
}
