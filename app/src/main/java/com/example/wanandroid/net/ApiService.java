package com.example.wanandroid.net;


import com.example.wanandroid.beans.Banner;
import com.example.wanandroid.beans.Page;
import com.example.wanandroid.beans.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("article/list/{page}/json")
    Observable<Result<Page>> getArticles(@Path("page") int page);

    @GET("banner/json")
    Observable<Result<List<Banner>>> getBanner();
}
