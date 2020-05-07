package com.itsdf07.module.common.mvp;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public interface IBaseMvpModel {
    /**
     * 因为Model数据模型是用于处理数据层事务，其中会包含网络、服务等后台进程的业务，顾接口化
     */
    interface IModelCallback<T> {
        void onComplete(T data);
    }
}
