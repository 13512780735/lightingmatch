package com.likeits.lightingmatch.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        Logger.init("MyTag").methodCount(3).hideThreadInfo().logLevel(LogLevel.FULL);
    }
}
