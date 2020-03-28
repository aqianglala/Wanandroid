package com.example.wanandroid.search.result;

public interface SearchResultsContract {
    interface View {

    }

    interface Presenter {
        void saveSearchHistory(String input);
    }
}
