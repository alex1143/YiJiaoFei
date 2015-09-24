package com.example.dxkj.utils;

import android.app.Application;

/**
 * Created by sh on 2015/9/9.
 */
public class ContextUtil extends Application {
    private static ContextUtil instance;
    public static ContextUtil getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }
}
