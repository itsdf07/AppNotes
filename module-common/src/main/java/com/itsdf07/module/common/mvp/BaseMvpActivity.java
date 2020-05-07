package com.itsdf07.module.common.mvp;

import android.app.Activity;
import android.os.Bundle;

import com.itsdf07.module.common.BaseActivity;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public abstract class BaseMvpActivity<P extends IBaseMvpPresenter> extends BaseActivity
        implements IBaseMvpView<Activity> {
    public P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        onPresenter();
    }

    @Override
    protected void onDestroy() {
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        if (null != presenter) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 实例化Presenter对象，会自动在Activity中持有它
     * 备注:如果需要在初始化Presenter之前执行某些事情，可以在onAfterView中进行
     *
     * @return
     */
    public abstract P initPresenter();

    public abstract void onPresenter();
}
