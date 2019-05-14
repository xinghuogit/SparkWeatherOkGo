package com.library.common.mvp.base.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.library.common.base.BaseActivity;

/**
 * 日期：2019/3/9 10:57
 * 创建：李加蒙
 * 描述：Activity基类
 */
public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity implements BaseView {
    private ProgressDialog progressDialog;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);//因为之后所有的子类都要实现对应的View接口
        initView();
        initData();
    }

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract P createPresenter();

    @Override
    public void showLoading() {
        showLoading("数据请求");
    }

    @Override
    public void showLoading(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(message);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureMessage(String msg) {
        showToast(msg);
    }


    @Override
    public void onProgress(long totalSize, long downSize) {

    }

    @Override
    public Context getContext() {
        return MvpBaseActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
