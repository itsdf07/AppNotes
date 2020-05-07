package com.itsdf07.module.common.http.http.bean;

import java.util.List;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/8
 */

public class BaseBean {

    /**
     * code : 41501
     * msg : ["No data","无数据","无数据"]
     * version : 当前版本信息：1.0.0403(dev)
     */

    private int code;
    private String version;
    private List<String> msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
