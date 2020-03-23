package com.example.library.net;

import com.example.library.app.BaseActivity;
import com.example.library.app.BaseFragment;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxHelper<T> {
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> io_main(BaseActivity activity) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindActivity(activity.lifecycle()));
    }

    public static <T> ObservableTransformer<T, T> io_main(BaseFragment fragment) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindFragment(fragment.lifecycle()));
    }
}