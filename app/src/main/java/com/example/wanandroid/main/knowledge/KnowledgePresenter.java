package com.example.wanandroid.main.knowledge;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import java.util.List;

import io.reactivex.functions.Consumer;

public class KnowledgePresenter extends BasePresenter<KnowledgeFragment> implements KnowledgeContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void getTree() {
        DataRepository.getInstance()
                .getTree()
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> chapters) throws Exception {
                        getIView().showChapters(chapters);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getIView().showError();
                    }
                });
    }
}
