package com.itsdf07.module.common.http.net;

/**
 * @Description: Net框架全局配置内容，当全局内容不变时，使用enum进行定义是最安全的
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/1
 */

public enum ConfigKeys {
    /**
     * 接口主机,如http://192.168.3.3:8080/
     */
    API_HOST,
    APPLICATION_CONTEXT,//全局上下文
    INTERCEPTOR,//拦截器
    /**
     * 初始化开始时标记为false，配置完成之后需标注为true，标明已经配置好全局信息
     */
    CONFIG_READY

}
