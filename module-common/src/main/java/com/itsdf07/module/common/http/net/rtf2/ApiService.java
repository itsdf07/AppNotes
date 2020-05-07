package com.itsdf07.module.common.http.net.rtf2;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description: 网络访问业务接口
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/1
 */

public interface ApiService {
    /**
     * 简单的get接口（无参）请求
     *
     * @param url http://192.168.3.3:8080/api/test/test2GetNoParam
     * @return
     */
    @GET
    Call<String> get(@Url String url);

    /**
     * 带参的get接口请求，如 http://192.168.3.3:8080/api/test/test2GetWithParam1?param1=77&param2=123456
     *
     * @param url    http://192.168.3.3:8080/api/test/test2GetWithParam1
     * @param params HashMap 存入的key-value
     * @return
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);//，如

    /**
     * 表单请求方式
     *
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * 文件下载
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming//下载是直接到内存,所以需要 @Streaming,即及时存入磁盘中
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 文件上传
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


    /**
     * 文件上传
     *
     * @param url
     * @param file
     * @param params 携带的参数内容
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file, @PartMap Map<String, RequestBody> params);


    /**
     * Json格式数据请求
     *
     * @param url
     * @param body
     * @return
     */
    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

}
