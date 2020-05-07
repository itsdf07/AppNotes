package com.itsdf07.module.common.http.net.rtf2.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;


import com.itsdf07.module.common.http.net.NetInit;
import com.itsdf07.module.common.http.net.rtf2.callback.IRequest;
import com.itsdf07.module.common.http.net.rtf2.callback.ISuccess;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/5
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        ResponseBody body = (ResponseBody) params[2];
        String fileName = (String) params[3];
        InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null) {
            extension = "";
        }
        if (fileName == null) {
            return FileUtils.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtils.writeToDisk(is, downloadDir, fileName);
        }

    }

    //如果文件已经下完了
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        //下到了APK直接安装
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtils.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            NetInit.getApplicationContext().startActivity(install);
        }
    }
}
