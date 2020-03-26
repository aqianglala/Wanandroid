package com.example.wanandroid.main.navigation;

import android.annotation.SuppressLint;

import com.example.library.app.BasePresenter;
import com.example.wanandroid.beans.Navigation;
import com.example.wanandroid.data.DataRepository;
import com.example.wanandroid.net.RxHelper;

import java.util.List;

import io.reactivex.functions.Consumer;

public class NavigationPresenter extends BasePresenter<NavigationFragment> implements NavigationContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void getNavigation() {
        DataRepository.getInstance()
                .getNavigation()
                .compose(RxHelper.io_main(getIView()))
                .subscribe(new Consumer<List<Navigation>>() {
                    @Override
                    public void accept(List<Navigation> articles) throws Exception {
                        getIView().showNavigation(articles);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getIView().showError();
                    }
                });
    }
}
