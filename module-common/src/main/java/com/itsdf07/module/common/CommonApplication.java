package com.itsdf07.module.common;

import android.app.Application;
import android.util.Log;

import com.itsdf07.lib.alog.ALog;
import com.itsdf07.lib.alog.ALogLevel;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/17
 */

public class CommonApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ASO", "onCreate: CommonApplication");
        initALog();
    }

    private void initALog() {
        ALog.init().setShowThreadInfo(false)
                .setLog2Local(false)
                .setTag("AppNotesLog")
                .setLogLevel(BuildConfig.isDebug ? ALogLevel.FULL : ALogLevel.NONE);
    }
}
