package com.example.wanandroid.main.knowledge.list;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import io.reactivex.functions.Consumer;

public class KnowledgeListPresenter extends BasePresenter<KnowledgeListFragment> implements KnowledgeListContract.Presenter {


    @SuppressLint("CheckResult")
    @Override
    public void getArticles(int page, int cid) {
        DataRepository.getInstance()
                .getArticleList(page, cid)
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
