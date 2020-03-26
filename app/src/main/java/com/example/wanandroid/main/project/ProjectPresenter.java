package com.example.wanandroid.main.project;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import java.util.List;

import io.reactivex.functions.Consumer;

public class ProjectPresenter extends BasePresenter<ProjectFragment> implements ProjectContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void getProject() {
        DataRepository.getInstance()
                .getProject()
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> chapters) throws Exception {
                        getIView().onGetProjectSuccess(chapters);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getIView().onGetProjectFailed();
                    }
                });
    }
}
