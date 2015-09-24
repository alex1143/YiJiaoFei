package com.example.dxkj.utils;

import android.app.Activity;
import android.content.Context;

import com.lidroid.xutils.ViewUtils;

/**
 * 初始化控件
 * Created by dxkj on 2015/9/10.
 */
public class InitView {

    private Context context;

    public static void initView(Context context) {
        ViewUtils.inject((Activity) context);
    }
}
