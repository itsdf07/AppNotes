package com.itsdf07.module.user.login;

import android.text.TextUtils;

import com.itsdf07.module.common.mvp.BaseMvpPresenter;
import com.itsdf07.module.common.mvp.IBaseMvpModel;


/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public class LoginPresenter extends BaseMvpPresenter<LoginContracts.ILoginView> implements LoginContracts.ILoginPresenter {
    private LoginContracts.ILoginModel loginModel;

    public LoginPresenter(LoginContracts.ILoginView view) {
        super(view);
        loginModel = new LoginModel();
    }

    @Override
    public boolean isLoginIdValid(String loginId) {
        return true;
    }

    @Override
    public boolean isPasswordValid(String pwd) {
        if (!TextUtils.isEmpty(pwd) && pwd.length() > 4) {
            return true;
        }
        return false;
    }

    @Override
    public void onLogin(String loginId, String pwd) {
        loginModel.login(loginId, pwd, new IBaseMvpModel.IModelCallback<Boolean>() {
            @Override
            public void onComplete(Boolean data) {
                if (data) {
                    if (isViewAttach()) {
                        view.get().getSelfActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.get().enterMainUI();
                            }
                        });
                    }
                }
            }
        });
    }
}
