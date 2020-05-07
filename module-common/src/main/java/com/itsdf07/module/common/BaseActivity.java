package com.itsdf07.module.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/3/27
 */
public abstract class BaseActivity extends AppCompatActivity {
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resLayoutId = this.getLayoutId();
        if (resLayoutId > 0) {
            setContentView(resLayoutId);
        } else {
            new Throwable("onCreate->请在getLayoutId中设置您的UI布局");
        }
        onInitView();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化UI控件
     */
    public abstract void onInitView();

}
