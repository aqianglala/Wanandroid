package com.example.wanandroid.search;

import com.example.wanandroid.beans.HotWord;
import com.example.wanandroid.beans.Website;

import java.util.List;

public interface SearchContract {

    interface View {
        void showHistory(List<String> data);

        void showWebsites(List<Website> data);

        void showHotWords(List<HotWord> data);
    }

    interface Presenter {
        void getHistory();

        void getWebsites();

        void getHotWords();
    }
}
