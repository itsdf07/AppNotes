package com.itsdf07.module.common.http.http.impl;


import com.google.gson.Gson;
import com.itsdf07.module.common.http.http.bean.BaseBean;
import com.itsdf07.module.common.http.net.rtf2.callback.ISuccess;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/8
 */

public abstract class HttpCallbackImpl<T extends BaseBean> implements ISuccess {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        try {
            Class<?> clz = analysisClassInfo(this);
            T bean = (T) gson.fromJson(result, clz);
            if (null == bean) {
                new Exception("Gson解析失败");
            }
            onSuccess(bean);

        } catch (Exception e) {

        }
    }

    /**
     * @param object
     * @return
     */
    private Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    /**
     * 成功请求http并成功解析响应的Json结果的回调
     *
     * @param result gson解析对象
     */
    public abstract void onSuccess(T result);
}
