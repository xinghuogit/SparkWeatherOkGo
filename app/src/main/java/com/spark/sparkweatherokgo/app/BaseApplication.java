package com.spark.sparkweatherokgo.app;

import android.app.Application;
import android.util.Log;

import com.library.common.utils.LogUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：全局Application
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;

    public static BaseApplication getBaseApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
        AppConfig.initFilesDir();
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        LogUtils.i(TAG, "onCreate: ");
    }
}
