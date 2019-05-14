package com.spark.sparkweatherokgo.mvp.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.library.common.mvp.base.view.MvpBaseFragment;
import com.library.common.utils.FileUtils;
import com.library.common.utils.LogUtils;
import com.spark.sparkweatherokgo.BuildConfig;
import com.spark.sparkweatherokgo.R;

import java.io.File;

/**
 * 日期：2019/4/25 15:41
 * 作者：李加蒙
 * 描述：
 **/
public class HomeFragment extends MvpBaseFragment<HomePresenter> implements HomeView {
    private static final String TAG = "HomeFragment";

    private SwipeRefreshLayout swipeRL;
    private RecyclerView rv;
    private TextView tvMy;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        return v;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView(View view) {
        swipeRL = (SwipeRefreshLayout) view.findViewById(R.id.swipeRL);
        rv = (RecyclerView) view.findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        LogUtils.i(TAG, "initData: ");
    }
}
