package com.library.common.mvp.base.model;

import java.util.Map;

/**
 * 日期：2019/3/9 11:41
 * 创建：李加蒙
 * 描述：
 */
public abstract class BaseModel<T> {
    private int errCode;
    private String errMsg;
    private T result;

    /**
     * 数据请求参数
     */
    protected String[] mParams;

    /**
     * @param args 参数数组  设置数据请求参数
     */
    public BaseModel params(String... args) {
        mParams = args;
        return this;
    }

    /**
     * @param callback 添加Callback并执行数据请求  具体的数据请求由子类实现
     */
    public abstract void execute(Callback<T> callback);

    /**
     * @param url
     * @param callback 执行Get网络请求，此类看需求由自己选择写与不写
     */
    protected void requestGet(String url, Callback<T> callback) {
    }

    /**
     * @param url
     * @param params
     * @param callback 执行Post网络请求，此类看需求由自己选择写与不写
     */
    protected void requestPost(String url, Map params, Callback<T> callback) {
    }


    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
