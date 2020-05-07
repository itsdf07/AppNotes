package com.itsdf07.module.common.mvp;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public interface IBaseMvpView<V> {

    /**
     * 此方法是为了当Presenter中需要获取上下文对象时，传递上下文对象，而不是让Presenter直接持有上下  文对象
     *
     * @return
     */
    V getSelfActivity();
}
