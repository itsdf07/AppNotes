package com.itsdf07.module.common.http.net.rtf2.download;

import android.os.AsyncTask;


import com.itsdf07.module.common.http.net.rtf2.NetCreator;
import com.itsdf07.module.common.http.net.rtf2.callback.IError;
import com.itsdf07.module.common.http.net.rtf2.callback.IFailure;
import com.itsdf07.module.common.http.net.rtf2.callback.IRequest;
import com.itsdf07.module.common.http.net.rtf2.callback.ISuccess;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description: 文件下载
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/5
 */

public class DownloadHandler {
    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String FILENAME;

    public DownloadHandler(HashMap<String, Object> params, String url,
                           IRequest request, ISuccess success,
                           IFailure failure, IError error,
                           String downloadDir, String extension, String filename) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public final void handleDownload() {
        NetCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            //开始保存文件,开一个异步任务来做
                            SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, EXTENSION, response.body(), FILENAME);
                            //如果下载完成
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
