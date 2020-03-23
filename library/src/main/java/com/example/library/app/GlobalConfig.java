package com.example.library.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by tank on 2019/5/12
 */

public final class GlobalConfig {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}