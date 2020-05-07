package com.itsdf07.module.common.http.net.rtf2.callback;

/**
 * @Description: 访问服务器的时候，服务器发生了错误并有错误响应码返回的情况使用该回调
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */
public interface IError {
    void onError(int code, String msg);
}
