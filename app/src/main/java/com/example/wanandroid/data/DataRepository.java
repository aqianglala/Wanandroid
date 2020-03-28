package com.example.wanandroid.data;

import com.example.wanandroid.beans.Banner;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.beans.HotWord;
import com.example.wanandroid.beans.Navigation;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.beans.Result;
import com.example.wanandroid.beans.Website;
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

    public Observable<Result<List<Banner>>> getBanner() {
        return HttpHelper.getRxRestService()
                .getBanner();
    }

    public Observable<Result<List<Chapter>>> getTree() {
        return HttpHelper.getRxRestService()
                .getTree();
    }

    public Observable<Result<List<Navigation>>> getNavigation() {
        return HttpHelper.getRxRestService()
                .getNavigation();
    }

    public Observable<Result<List<Chapter>>> getProject() {
        return HttpHelper.getRxRestService()
                .getProject();
    }

    public Observable<Result<Page>> getProjectList(int page, int cid) {
        return HttpHelper.getRxRestService()
                .getProjectList(page, cid);
    }

    public Observable<Result<Page>> getArticleList(int page, int cid) {
        return HttpHelper.getRxRestService()
                .getArticleList(page, cid);
    }

    public Observable<Result<List<Website>>> getWebsites() {
        return HttpHelper.getRxRestService()
                .getWebsites();
    }

    public Observable<Result<List<HotWord>>> getHotWords() {
        return HttpHelper.getRxRestService()
                .getHotWords();
    }

    public Observable<Result<Page>> getSearchResults(int page, String key) {
        return HttpHelper.getRxRestService()
                .getSearchResults(page, key);
    }

}
