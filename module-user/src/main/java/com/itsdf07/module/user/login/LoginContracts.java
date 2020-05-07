package com.itsdf07.module.user.login;

import android.app.Activity;

import com.itsdf07.module.common.mvp.IBaseMvpModel;
import com.itsdf07.module.common.mvp.IBaseMvpPresenter;
import com.itsdf07.module.common.mvp.IBaseMvpView;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public interface LoginContracts {
    interface ILoginView extends IBaseMvpView<Activity> {
        void enterMainUI();
    }

    interface ILoginPresenter extends IBaseMvpPresenter {
        /**
         * 本地校验账号的合法性，比如账号格式
         *
         * @param loginId
         * @return
         */
        boolean isLoginIdValid(String loginId);

        /**
         * 本地校验密码的合法性，比如密码长度与组合规则
         *
         * @param pwd
         * @return
         */
        boolean isPasswordValid(String pwd);

        /**
         * 执行用户登录
         *
         * @param loginId
         * @param pwd
         */
        void onLogin(String loginId, String pwd);
    }

    interface ILoginModel extends IBaseMvpModel {
        /**
         * 执行用户登录
         *
         * @param loginId
         * @param pwd
         * @param callback
         */
        void login(String loginId, String pwd, IModelCallback callback);
    }
}
