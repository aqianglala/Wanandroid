package com.example.wanandroid.main.knowledge.list;

import com.example.wanandroid.beans.Page;

public interface KnowledgeListContract {

    interface View {
        void showArticles(Page page);

        void showArticlesError();
    }

    interface Presenter {
        void getArticles(int page, int cid);
    }
}
