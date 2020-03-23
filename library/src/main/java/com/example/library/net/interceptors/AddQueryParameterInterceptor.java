package com.example.library.net.interceptors;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设置公共参数
 */
public class AddQueryParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request;
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter("phoneSystem", "")
                .addQueryParameter("phoneModel", "")
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
