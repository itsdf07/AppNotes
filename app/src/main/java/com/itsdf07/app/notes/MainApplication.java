package com.itsdf07.app.notes;

import android.app.Application;

import com.itsdf07.lib.alog.ALog;
import com.itsdf07.lib.alog.ALogLevel;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/17
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initALog();
    }

    private void initALog() {
        ALog.init().setShowThreadInfo(false)
                .setLog2Local(false)
                .setTag("AppNotesLog")
                .setLogLevel(ALogLevel.FULL);
    }
}
