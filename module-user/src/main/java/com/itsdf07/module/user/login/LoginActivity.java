package com.itsdf07.module.user.login;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itsdf07.lib.alog.ALog;
import com.itsdf07.module.common.dialog.DialogLoading;
import com.itsdf07.module.common.mvp.BaseMvpActivity;
import com.itsdf07.module.user.R;

/**
 * @Description: App登录页面
 * 1、账号密码登录
 * 2、账号注册
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/03/27
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContracts.ILoginView {

    /**
     * 账号编辑框
     */
    private AutoCompleteTextView mLoginIdView;
    /**
     * 密码编辑框
     */
    private EditText mPasswordView;

    /**
     * 登录过程对话框
     */
    private Dialog mDialogLogging;

    @Override
    public int getLayoutId() {
        ALog.vTag(TAG, "....");
        return R.layout.activity_login;
    }

    @Override
    public void onInitView() {
        ALog.vTag(TAG, "....");
        mLoginIdView = (AutoCompleteTextView) findViewById(R.id.loginId);
        mPasswordView = (EditText) findViewById(R.id.password);
        //监听软键盘的按钮事件，比如输完密码之后，可点击软键盘上的登录按钮（需要使用imeOptions属性进行设置成登录事件）
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_GO) {
                    attemptLogin();
                }
                return false;//true表示不关闭软键盘，false表示关闭软键盘
            }
        });

        Button btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    @Override
    public LoginPresenter initPresenter() {
        ALog.vTag(TAG, "....");
        return new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        ALog.vTag(TAG, "....");
        super.onDestroy();
    }

    @Override
    public void onPresenter() {
        ALog.vTag(TAG, "....");
    }


    /**
     * 尝试登录
     */
    private void attemptLogin() {
        ALog.vTag(TAG, "....");
        // Reset errors.
        mLoginIdView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String loginId = mLoginIdView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!presenter.isPasswordValid(password)) {
            mPasswordView.setError("密码长度不够");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(loginId)) {
            mLoginIdView.setError("账号不能为空");
            focusView = mLoginIdView;
            cancel = true;
        } else if (!presenter.isLoginIdValid(loginId)) {
            mLoginIdView.setError("无效账号");
            focusView = mLoginIdView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);
            mDialogLogging = DialogLoading.createLoadingDialog(this, "登录中，请稍候");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            presenter.onLogin(loginId, password);
        }
    }


    @Override
    public LoginActivity getSelfActivity() {
        ALog.vTag(TAG, "....");
        return this;
    }


    @Override
    public void enterMainUI() {
        ALog.vTag(TAG, "....");
        DialogLoading.closeDialog(mDialogLogging);
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        finish();
    }
}