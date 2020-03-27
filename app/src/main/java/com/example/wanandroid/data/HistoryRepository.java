package com.example.wanandroid.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.library.app.GlobalConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryRepository {

    private static HistoryRepository INSTANCE;
    private static final Object sLock = new Object();
    private Context mContext;

    private HistoryRepository() {
        mContext = GlobalConfig.getApplicationContext();
    }

    public static HistoryRepository getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = new HistoryRepository();
                }
                return INSTANCE;
            }
        }
    }

    private static final String PREFERENCE_NAME = "user_info";
    private static final String SEARCH_HISTORY = "history";
    private static final int MAX_SIZE = 8;// 最多保存8条

    // 保存搜索记录
    public void saveSearchHistory(String inputText) {
        SharedPreferences sp = mContext.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        String longHistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longHistory.split(",");
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory));
        SharedPreferences.Editor editor = sp.edit();
        if (historyList.size() > 0) {
            //1.移除之前重复添加的元素
            for (int i = 0; i < historyList.size(); i++) {
                if (inputText.equals(historyList.get(i))) {
                    historyList.remove(i);
                    break;
                }
            }
            historyList.add(0, inputText); //将新输入的文字添加集合的第0位也就是最前面(2.倒序)
            if (historyList.size() > MAX_SIZE) {
                historyList.remove(historyList.size() - 1); //3.最多保存8条搜索记录 删除最早搜索的那一项
            }
            //逗号拼接
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historyList.size(); i++) {
                sb.append(historyList.get(i) + ",");
            }
            //保存到sp
            editor.putString(SEARCH_HISTORY, sb.toString());
            editor.commit();
        } else {
            //之前未添加过
            editor.putString(SEARCH_HISTORY, inputText + ",");
            editor.commit();
        }
    }

    //获取搜索记录
    public List<String> getSearchHistory() {
        SharedPreferences sp = mContext.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        String longHistory = sp.getString(SEARCH_HISTORY, "");
        String[] tmpHistory = longHistory.split(","); //split后长度为1有一个空串对象
        List<String> historyList = new ArrayList<String>(Arrays.asList(tmpHistory));
        if (historyList.size() == 1 && historyList.get(0).equals("")) { //如果没有搜索记录，split之后第0
            // 位是个空串的情况下
            historyList.clear();
        }
        return historyList;
    }

}
