package com.example.wanandroid.main.project;

import com.example.wanandroid.beans.Chapter;

import java.util.List;

public interface ProjectContract {

    interface View {
        void onGetProjectSuccess(List<Chapter> data);

        void onGetProjectFailed();
    }

    interface Presenter {
        void getProject();
    }
}
