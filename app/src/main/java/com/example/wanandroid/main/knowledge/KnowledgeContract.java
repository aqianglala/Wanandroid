package com.example.wanandroid.main.knowledge;

import com.example.wanandroid.beans.Chapter;

import java.util.List;

public interface KnowledgeContract {
    interface View {
        void showChapters(List<Chapter> data);

        void showError();
    }

    interface Presenter {
        void getTree();
    }
}
