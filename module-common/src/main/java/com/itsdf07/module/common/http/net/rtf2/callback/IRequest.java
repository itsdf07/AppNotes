package com.itsdf07.module.common.http.net.rtf2.callback;

/**
 * @Description: 网络请求过程可以实现的事情
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */
public interface IRequest {
    void onRequestStart();

    void onRequestEnd();
}
