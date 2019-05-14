package com.spark.sparkweatherokgo.mvp.test;

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
 * 日期：2019/5/14 17:49
 * 作者：李加蒙
 * 描述：
 **/
public class TestPresenter extends BasePresenter<TestView> {
    private static final String TAG = "TestPresenter";

    public TestPresenter() {
    }

    /**
     * 转成bean对象
     */
    public void jsonListObject() {
        Type type = new TypeToken<LzyResponse<List<ServerModel>>>() {
        }.getType();
        ServerApi.<LzyResponse<List<ServerModel>>>getData(HttpMethod.GET, type, Urls.URL_GET_WXARTICLE_CHAPTERS_JSON, "", "")//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        if (getView() != null)
                            getView().showLoading();
                    }
                })//
                .map(new Function<LzyResponse<List<ServerModel>>, LzyResponse>() {
                    @Override
                    public LzyResponse apply(@NonNull LzyResponse<List<ServerModel>> response) throws Exception {
                        return response;
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<LzyResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull LzyResponse response) {
                        if (getView() != null && response != null) {
                            LogUtils.i(TAG, "onNext: " + response.toString());
                            if (response.data != null) {
                                List<ServerModel> serverModels = (List<ServerModel>) response.data;
                                getView().setText(serverModels.toString());
                            } else {
                                getView().setText(response.toString());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        LogUtils.i(TAG, "onError: " + e.getMessage());
                        getView().showToast("请求失败");
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null)
                            getView().hideLoading();
                    }
                });
    }


    /**
     * 转成bean对象
     */
    public void jsonObject() {
        Type type = new TypeToken<LzyResponse<ServerModel>>() {
        }.getType();
        ServerApi.<LzyResponse<ServerModel>>getData(HttpMethod.POST, type, Urls.URL_POST_LG_TODO_ADD_JSON, "", "")//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        if (getView() != null)
                            getView().showLoading();
                    }
                })//
                .map(new Function<LzyResponse<ServerModel>, LzyResponse>() {
                    @Override
                    public LzyResponse apply(@NonNull LzyResponse<ServerModel> response) throws Exception {
                        return response;
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())  //
                .subscribe(new Observer<LzyResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull LzyResponse response) {
                        if (getView() != null && response != null) {
                            LogUtils.i(TAG, "onNext: " + response.toString());
                            if (response.data != null) {
                                ServerModel serverModel = (ServerModel) response.data;
                                getView().setText(serverModel.toString());
                            } else {
                                getView().setText(response.toString());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        LogUtils.i(TAG, "onError: " + e.getMessage());
                        getView().showToast("请求失败");
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null)
                            getView().hideLoading();
                    }
                });
    }

    /**
     * string
     */
    public void jsonString() {
        ServerApi.getString(HttpMethod.POST, "", Urls.URL_POST_LG_TODO_ADD_JSON, "")//
                .subscribeOn(Schedulers.io())//
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        if (getView() != null)
                            getView().showLoading();
                    }
                })//
                .observeOn(AndroidSchedulers.mainThread())  //
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        LogUtils.i(TAG, "onNext: " + s.toString());
                        if (getView() != null)
                            getView().setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();            //请求失败
                        LogUtils.i(TAG, "onError: " + e.getMessage());
                        getView().showToast("请求失败");
                    }

                    @Override
                    public void onComplete() {
                        if (getView() != null)
                            getView().hideLoading();
                    }
                });
    }
}
