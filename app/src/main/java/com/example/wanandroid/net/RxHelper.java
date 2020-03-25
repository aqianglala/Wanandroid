package com.example.wanandroid.net;

import com.example.wanandroid.beans.Result;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


@SuppressWarnings("ALL")
public class RxHelper<T> {
    public static <T> ObservableTransformer<Result<T>, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new GetDataFunction());
    }

    public static <T> ObservableTransformer<Result<T>, T> io_main(RxAppCompatActivity activity) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindActivity(activity.lifecycle()))
                        .map(new GetDataFunction());
    }

    public static <T> ObservableTransformer<Result<T>, T> io_main(RxFragment fragment) {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycleAndroid.bindFragment(fragment.lifecycle()))
                        .map(new GetDataFunction());
    }

    static class GetDataFunction<T> implements Function<Result<T>, T> {

        @Override
        public T apply(Result<T> tResult) throws Exception {
            if (tResult.getErrorCode() == 0) {
                return tResult.getData();
            } else {
                throw new Exception("errorCode = " + tResult.getErrorCode());
            }
        }
    }

}