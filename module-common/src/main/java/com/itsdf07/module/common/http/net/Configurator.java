package com.itsdf07.module.common.http.net;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * @Description: 统一配置的全局信息
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/1
 */

public class Configurator {
    /**
     * 统一配置全局相关信息，如url、intercept、全局Application等，其中对应的Key定义在ConfigKeys.java中
     */
    private HashMap<String, Object> CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//OkHttp3的拦截器，监控网络请求过程中的情况

    //静态内部类单例模式，java并发中推荐的单例模式
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private Configurator() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    /**
     * 获取全局配置相关信息的集合
     *
     * @return
     */
    final HashMap<String, Object> getConfigs() {
        return CONFIGS;
    }

    /**
     * 针对全局配置的信息获取对应单个信息内容，比如根据URL的key取出url
     *
     * @param key
     * @param <T>
     * @return
     */
    final <T> T getConfiguration(String key) {
        checkConfiguration();//一切可获取的全局配置信息都需要基于全局配置过程已经完成才可以
        final Object value = CONFIGS.get(key);
        if (null == value) {
            //没有配置对应信息时强行获取，则抛出空指针异常
            throw new NullPointerException(key.toString() + "is null");
        }
        return (T) CONFIGS.get(key);
    }

    /**
     * 设置主机地址
     *
     * @param host http://192.168.3.3:8080/
     * @return
     */
    public final Configurator withApiHost(String host) {
        CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    /**
     * 设置拦截器
     *
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    /**
     * 检查配置是否完成
     * 全局Net框架初始化时，配置状态是处于配置中，该值在Configurator()构造器中初始化，
     * 当配置完全局信息时，需要调用configure()进行设置已经配置好了全局信息
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 标志Net全局配置信息已完成
     */
    public final void configure() {
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }
}
