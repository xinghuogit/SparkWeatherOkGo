package com.library.common.mvp.base.view;

import android.content.Context;

import com.library.common.mvp.base.model.BaseModel;

/**
 * 日期：2019/3/9 10:51
 * 创建：李加蒙
 * 描述：View基类
 */
public interface BaseView {
    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 显示正在加载进度框
     */
    void showLoading(String messgae);

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * @param msg 弹出Toast
     */
    void showToast(String msg);

    /**
     * @param msg 当数据请求失败后，调用此接口提示 失败原因
     */
    void showFailureMessage(String msg);


    /**
     * @param totalSize 文件总进度
     * @param downSize  下载进度
     */
    void onProgress(long totalSize, long downSize);

    /**
     * @return 上下文 获取上下文
     */
    Context getContext();
}
