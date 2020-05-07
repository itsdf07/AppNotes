package com.itsdf07.module.common.http.net.rtf2;


import com.itsdf07.module.common.http.net.ConfigKeys;
import com.itsdf07.module.common.http.net.NetInit;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */

public final class NetCreator {
    /**
     * 产生一个全局的Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = NetInit.getConfiguration(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)//不使用默认的OK3，那么就自行重新设置设置okhttp
                .build();
    }

    /**
     * 自定义OK3的内容
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(((ArrayList<Interceptor>) NetInit.getConfiguration(ConfigKeys.INTERCEPTOR.name())).get(0))
                .build();
    }

    //提供接口让调用者得到retrofit对象
    private static final class RestServiceHolder {
        private static final ApiService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    /**
     * 获取对象
     */
    public static ApiService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
