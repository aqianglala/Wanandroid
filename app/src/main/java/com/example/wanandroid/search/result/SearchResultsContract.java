package com.example.wanandroid.search.result;

import com.example.wanandroid.beans.Page;

public interface SearchResultsContract {
    interface View {
        void showArticles(Page page);

        void showArticlesError();
    }

    interface Presenter {
        void saveSearchHistory(String input);

        void getArticles(int page);
    }
}
