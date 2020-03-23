package com.example.wanandroid.net;


import com.example.library.net.BaseHttpHelper;

public class HttpHelper {

    private static final class RxRestServiceHolder {
        private static final ApiService REST_SERVICE =
                BaseHttpHelper.RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    public static ApiService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }
}
