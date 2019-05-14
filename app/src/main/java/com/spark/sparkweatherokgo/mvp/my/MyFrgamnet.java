package com.spark.sparkweatherokgo.mvp.my;

import android.support.v4.app.Fragment;
import android.view.View;

import com.library.common.mvp.base.view.MvpBaseFragment;
import com.library.common.utils.LogUtils;
import com.spark.sparkweatherokgo.R;
import com.spark.sparkweatherokgo.mvp.home.HomePresenter;
import com.spark.sparkweatherokgo.mvp.home.HomeView;

/**
 * 日期：2019/4/25 15:41
 * 作者：李加蒙
 * 描述：
 **/
public class MyFrgamnet extends MvpBaseFragment<HomePresenter> implements HomeView {
    private static final String TAG = "MyFrgamnet";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_my;
    }

    @Override
    public HomePresenter createPresenter() {
        return null;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        LogUtils.i(TAG, "initData: ");
    }
}
