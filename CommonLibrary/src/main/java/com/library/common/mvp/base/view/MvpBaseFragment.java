package com.library.common.mvp.base.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.library.common.base.BaseFragment;

/**
 * 日期：2019/3/9 11:25
 * 创建：李加蒙
 * 描述：
 */
public abstract class MvpBaseFragment<P extends BasePresenter> extends BaseFragment implements BaseView {
    protected Context mContext;
    protected View mRootView;

    private ProgressDialog progressDialog;

    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), null);
        this.mContext = getActivity();
        return mRootView;
    }


    public abstract int getContentViewId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) mPresenter.attachView(this);//因为之后所有的子类都要实现对应的View接口
        initViews(view, savedInstanceState);
        initView(view);
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
            progressDialog = new ProgressDialog(getContext());
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
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureMessage(String msg) {
        showToast(msg);
    }


    @Override
    public void onProgress(long totalSize, long downSize) {
        if (progressDialog != null) {
            progressDialog.setMessage(downSize + "/" + totalSize);
        }
    }

    @Override
    public Context getContext() {
        checkActivityAttached();
        return getActivity();
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
