package com.example.wanandroid.search;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.HotWord;
import com.example.wanandroid.beans.Website;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.data.HistoryRepository;
import com.example.wanandroid.net.RxHelper;

import java.util.List;

import io.reactivex.functions.Consumer;

public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.Presenter {
    @Override
    public void getHistory() {
        List<String> searchHistory = HistoryRepository.getInstance().getSearchHistory();
        getIView().showHistory(searchHistory);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getWebsites() {
        DataRepository.getInstance()
                .getWebsites()
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<List<Website>>() {
                    @Override
                    public void accept(List<Website> websites) throws Exception {
                        getIView().showWebsites(websites);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getHotWords() {
        DataRepository.getInstance()
                .getHotWords()
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<List<HotWord>>() {
                    @Override
                    public void accept(List<HotWord> hotWords) throws Exception {
                        getIView().showHotWords(hotWords);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
