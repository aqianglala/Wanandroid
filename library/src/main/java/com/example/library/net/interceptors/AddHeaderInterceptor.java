package com.example.library.net.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设置头
 */
public class AddHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = "";
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder()
                // Provide your custom header here
                .header("token", token)
                .method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
