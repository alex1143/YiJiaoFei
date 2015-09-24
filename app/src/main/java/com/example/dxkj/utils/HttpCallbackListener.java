package com.example.dxkj.utils;

/**
 * HTTP请求回调
 * Created by sh on 2015/9/17.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
