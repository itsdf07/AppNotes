package com.itsdf07.module.common.http.net.rtf2.interceptor;


import com.itsdf07.lib.alog.ALog;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/4
 */

public class LoggingLogger implements HttpLoggingInterceptor.Logger {
    private static final String TAG = "OkHttp";

    @Override
    public void log(String message) {
        ALog.eTag(TAG, "message:%s", message);
    }
}
