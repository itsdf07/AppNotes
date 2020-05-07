package com.itsdf07.module.common.http.net.rtf2;



import com.itsdf07.module.common.http.net.rtf2.callback.IError;
import com.itsdf07.module.common.http.net.rtf2.callback.IFailure;
import com.itsdf07.module.common.http.net.rtf2.callback.IRequest;
import com.itsdf07.module.common.http.net.rtf2.callback.ISuccess;
import com.itsdf07.module.common.http.net.rtf2.callback.RequestCallbacks;
import com.itsdf07.module.common.http.net.rtf2.download.DownloadHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Description: 网络可用框架接口，提供给框架外使用的
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */

public class NetClient {
    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    //上传下载
    private final File FILE;

    private final String DOWNLOAD_DIR;//下载的文件存放目录
    private final String EXTENSION;//文件的扩展名
    private final String FILENAME;//文件名

    public NetClient(HashMap<String, Object> params,
                     String url,
                     IRequest request,
                     ISuccess success,
                     IFailure failure,
                     IError error,
                     RequestBody body,
                     File file, String downloadDir, String extension, String filename) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;

        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;  ///sdcard/XXXX.ext
        this.EXTENSION = extension;
        this.FILENAME = filename;
    }

    public static NetClientBuilder create() {
        return new NetClientBuilder();
    }

    //开始实现真实的网络操作
    private void request(HttpMethod method) {
        final ApiService service = NetCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                if (null == PARAMS || PARAMS.size() == 0) {
                    call = service.get(URL);
                } else {
                    call = service.get(URL, PARAMS);
                }
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "file", FILE.getName(), requestBody);
                if (null != PARAMS) {
                    Map<String, RequestBody> params = new HashMap<>();
                    Iterator<Map.Entry<String, Object>> it = PARAMS.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        params.put(entry.getKey(), RequestBody.create(MediaType.parse("text/plain"), entry.getValue().toString()));
                    }
                    call = service.upload(URL, body, params);
                } else {
                    call = service.upload(URL, body);
                }

                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getReqeustCallback());
        }
    }

    private Callback<String> getReqeustCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }

    //各种请求
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void postRaw() {
        request(HttpMethod.POST_RAW);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(PARAMS, URL, REQUEST,
                SUCCESS, FAILURE, ERROR, DOWNLOAD_DIR,
                EXTENSION, FILENAME)
                .handleDownload();
    }

}







