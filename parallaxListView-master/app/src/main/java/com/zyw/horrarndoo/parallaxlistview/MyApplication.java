package com.zyw.horrarndoo.parallaxlistview;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Horrarndoo on 2017/3/29.
 */

public class MyApplication extends Application {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}