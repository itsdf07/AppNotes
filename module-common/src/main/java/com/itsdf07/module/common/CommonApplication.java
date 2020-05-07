package com.itsdf07.module.common;

import android.app.Application;
import android.util.Log;

import com.itsdf07.lib.alog.ALog;
import com.itsdf07.lib.alog.ALogLevel;
import com.itsdf07.module.common.http.net.NetInit;

import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/17
 */

public class CommonApplication extends Application {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    public final String BASE_URL = "";
    public final String BASE_URL_LOCAL = "http://192.168.3.3:8080/";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ASO", "onCreate: CommonApplication");
        initALog();
        initNetCore();
    }

    private void initALog() {
        ALog.init().setShowThreadInfo(false)
                .setLog2Local(false)
                .setTag("AppNotesLog")
                .setLogLevel(BuildConfig.isDebug ? ALogLevel.FULL : ALogLevel.NONE);
    }

    private void initNetCore() {

        ArrayList<Interceptor> interceptors = new ArrayList<>();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptors.add(loggingInterceptor);
        NetInit.init(this)
                .withApiHost(BASE_URL_LOCAL)
                .withInterceptors(interceptors)
                .configure();
    }
}
