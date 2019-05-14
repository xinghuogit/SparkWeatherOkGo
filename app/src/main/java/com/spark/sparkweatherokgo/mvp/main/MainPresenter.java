package com.spark.sparkweatherokgo.mvp.main;

import com.google.gson.reflect.TypeToken;
import com.library.common.api.LzyResponse;
import com.library.common.api.ServerApi;
import com.library.common.api.ServerModel;
import com.library.common.api.Urls;
import com.library.common.mvp.base.view.BasePresenter;
import com.library.common.utils.LogUtils;
import com.lzy.okgo.model.HttpMethod;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 日期：2019/4/25 15:23
 * 作者：李加蒙
 * 描述：
 **/
public class MainPresenter extends BasePresenter<MainView> {
    private static final String TAG = "MainPresenter";

    public MainPresenter() {
    }
}
