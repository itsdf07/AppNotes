package com.itsdf07.module.common.http.net.rtf2;


import com.itsdf07.module.common.http.net.rtf2.callback.IError;
import com.itsdf07.module.common.http.net.rtf2.callback.IFailure;
import com.itsdf07.module.common.http.net.rtf2.callback.IRequest;
import com.itsdf07.module.common.http.net.rtf2.callback.ISuccess;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Description: 提供给框架外使用时进行网络请求参数设置
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */

public class NetClientBuilder {
    /**
     * 表单提交方式时设置的网络请求参数内容
     */
    private HashMap<String, Object> mParams;
    /**
     * 网络请求的api接口，如 api/test/test2PostWithParam2
     */
    private String mUrl;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;

    //上传
    private File mFile;

    /**
     * 下载时本地存储的文件后缀
     */
    private String mExtension;
    /**
     * 文件下载路径
     */
    private String mDownloadDir;
    private String mFilename;

    NetClientBuilder() {

    }

    /**
     * 网络请求的api接口
     *
     * @param url 如 api/test/test2PostWithParam2
     * @return
     */
    public final NetClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 表单提交方式时设置的网络请求参数内容
     *
     * @param params
     * @return
     */
    public final NetClientBuilder params(HashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    /**
     * 添加请求成功的回调
     *
     * @param success
     * @return
     */
    public final NetClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    /**
     * 添加请求过程中的回调，比如请求开始、请求结束
     *
     * @param request
     * @return
     */
    public final NetClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    /**
     * 添加属于网络请求错误返回时的回调，如服务器错误码的返回
     *
     * @param error
     * @return
     */
    public final NetClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    /**
     * 添加属于网络请求失败返回时的回调，如没有网络的失败返回
     *
     * @param failure
     * @return
     */
    public final NetClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    /**
     * Json格式的请求数据
     * MediaType.parse("application/json;charset=UTF-8")
     *
     * @param raw
     * @return
     */
    public final NetClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 需要上传的文件
     *
     * @param file 上传的文件
     * @return
     */
    public final NetClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 需要上传的文件
     *
     * @param file 文件路径
     * @return
     */
    public final NetClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 设置文件下载的路径:从/sdcard开始
     *
     * @param dir 下载的文件存放路径
     * @return
     */
    public final NetClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 使用Core框架中默认提供的文件名格式 + 标注后缀格式进行文件存储，与filename()同时使用时优先级较低
     *
     * @param extension
     * @return
     */
    public final NetClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 下载文件时保持的文件名，extension()同时使用时优先级较高
     *
     * @param filename
     * @return
     */
    public final NetClientBuilder filename(String filename) {
        this.mFilename = filename;
        return this;
    }


    public final NetClient build() {
        return new NetClient(mParams, mUrl, mRequest, mSuccess, mFailure, mError, mBody, mFile, mDownloadDir, mExtension, mFilename);
    }
}
