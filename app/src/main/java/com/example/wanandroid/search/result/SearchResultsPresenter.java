package com.example.wanandroid.search.result;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.data.HistoryRepository;

public class SearchResultsPresenter extends BasePresenter<SearchResultsActivity> implements SearchResultsContract.Presenter {
    @Override
    public void saveSearchHistory(String input) {
        HistoryRepository.getInstance()
                .saveSearchHistory(input);
    }
}
