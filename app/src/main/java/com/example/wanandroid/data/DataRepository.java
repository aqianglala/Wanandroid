package com.example.wanandroid.data;

public class DataRepository {

    private static DataRepository INSTANCE;
    private static final Object sLock = new Object();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
                return INSTANCE;
            }
        }
    }

}
