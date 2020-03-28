package com.example.wanandroid.net;


import com.example.wanandroid.beans.Banner;
import com.example.wanandroid.beans.Chapter;
import com.example.wanandroid.beans.HotWord;
import com.example.wanandroid.beans.Navigation;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.beans.Result;
import com.example.wanandroid.beans.Website;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("article/list/{page}/json")
    Observable<Result<Page>> getArticles(@Path("page") int page);

    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();

    @GET("tree/json")
    Observable<Result<List<Chapter>>> getTree();

    @GET("navi/json")
    Observable<Result<List<Navigation>>> getNavigation();

    @GET("project/tree/json")
    Observable<Result<List<Chapter>>> getProject();

    @GET("project/list/{page}/json")
    Observable<Result<Page>> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("article/list/{page}/json")
    Observable<Result<Page>> getArticleList(@Path("page") int page, @Query("cid") int cid);

    @GET("friend/json")
    Observable<Result<List<Website>>> getWebsites();

    @GET("hotkey/json")
    Observable<Result<List<HotWord>>> getHotWords();

    @POST("article/query/{page}/json")
    Observable<Result<Page>> getSearchResults(@Path("page") int page, @Field("k") String key);
}
