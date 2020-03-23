package com.example.wanandroid;

import android.app.Application;

import com.example.library.app.GlobalConfig;


public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalConfig.init(this)
                .withApiHost("https://www.wanandroid.com/")
                .configure();
    }
}
