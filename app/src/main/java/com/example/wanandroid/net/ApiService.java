package com.example.wanandroid.net;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by tank on 2019/5/14
 */
public interface ApiService {

    @GET
    Observable<String> getData(@Url String url);
}
