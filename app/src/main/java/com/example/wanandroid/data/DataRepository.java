package com.example.wanandroid.data;

import com.example.wanandroid.beans.Article;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.beans.Result;
import com.example.wanandroid.net.HttpHelper;

import java.util.List;

import io.reactivex.Observable;

public class DataRepository {

    private static DataRepository INSTANCE;
    private static final Object sLock = new Object();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
                return INSTANCE;
            }
        }
    }

    public Observable<Result<Page>> getArticles(int page) {
        return HttpHelper.getRxRestService()
                .getArticles(page);
    }

}
