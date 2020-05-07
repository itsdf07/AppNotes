package com.itsdf07.module.common.http.http;


import com.itsdf07.module.common.http.http.impl.HttpCallbackImpl;
import com.itsdf07.module.common.http.net.rtf2.NetClient;
import com.itsdf07.module.common.http.net.rtf2.callback.IError;
import com.itsdf07.module.common.http.net.rtf2.callback.IFailure;

import java.util.HashMap;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/8
 */

public class HttpUtils {
    public static HttpUtils getInstance() {
        return HttpUtilsHolder.instance;
    }

    private static class HttpUtilsHolder {
        private static final HttpUtils instance = new HttpUtils();
    }

    public void post(String url, HashMap<String, Object> params, HttpCallbackImpl success, IError error, IFailure failure) {
        NetClient.create()
                .url(url)
                .params(params)
                .success(success)
                .error(error)
                .failure(failure)
                .build().post();
    }

    public void postRaw(String url, String json, HttpCallbackImpl success, IError error, IFailure failure) {
        NetClient.create()
                .url(url)
                .raw(json)
                .success(success)
//                .error(error)
//                .failure(failure)
                .build().postRaw();
    }
}
