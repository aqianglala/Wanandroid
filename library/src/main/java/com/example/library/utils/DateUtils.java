package com.example.library.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

    public static String formatTimestamp(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(timestamp);
    }
}
