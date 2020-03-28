package com.example.wanandroid.search.result;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.data.HistoryRepository;
import com.example.wanandroid.net.RxHelper;

import io.reactivex.functions.Consumer;

public class SearchResultsPresenter extends BasePresenter<SearchResultsActivity> implements SearchResultsContract.Presenter {
    @Override
    public void saveSearchHistory(String input) {
        HistoryRepository.getInstance()
                .saveSearchHistory(input);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getArticles(int page) {
        DataRepository.getInstance()
                .getArticles(page)
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<Page>() {
                    @Override
                    public void accept(Page page) throws Exception {
                        getIView().showArticles(page);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getIView().showArticlesError();
                    }
                });
    }
}
