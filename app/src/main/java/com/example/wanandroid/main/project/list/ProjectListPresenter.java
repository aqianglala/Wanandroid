package com.example.wanandroid.main.project.list;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import io.reactivex.functions.Consumer;

public class ProjectListPresenter extends BasePresenter<ProjectListFragment> implements ProjectListContract.Presenter {


    @SuppressLint("CheckResult")
    @Override
    public void getArticles(int page, int cid) {
        DataRepository.getInstance()
                .getProjectList(page, cid)
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
