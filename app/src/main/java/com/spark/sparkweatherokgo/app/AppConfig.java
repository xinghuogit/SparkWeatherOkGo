package com.spark.sparkweatherokgo.app;

import android.os.Environment;

import com.library.common.BuildConfig;
import com.library.common.utils.FileUtils;
import com.library.common.utils.SDCardUtils;

import java.io.File;

/**
 * 日期：2019/4/3 16:36
 * 创建：李加蒙
 * 描述：
 */
public class AppConfig {

    /**
     * 是否是调试模式
     */
    public static final boolean isDebug = BuildConfig.DEBUG;

    /**
     * 是否需要日志打印配置信息
     */
    public static final boolean APP_LOG_SWITCH = isDebug;

    /************************
     * 文件目录配置信息
     ***************************/
    public static final String APP_DIR = "aaSparkWeatherOkGo";//程序根目录
    public static final String BASE_SD_URL = Environment.getExternalStorageDirectory().getPath() + File.separator + APP_DIR + File.separator;
    public static final String BASE_INNER_URL = BaseApplication.getBaseApplication().getCacheDir().getPath() + File.separator + APP_DIR + File.separator;
    public static final String BASE_URL = SDCardUtils.isSDCardEnable() ? BASE_SD_URL : BASE_INNER_URL;


    public static final String DIR_LOG = BASE_URL + "Log" + File.separator;
    public static final String DIR_DOWN = BASE_URL + "Down" + File.separator;
    public static final String DIR_BOOK = BASE_URL + "Book" + File.separator;
    public static final String DIR_Voice = BASE_URL + "Voice" + File.separator;
    public static final String DIR_Video = BASE_URL + "Video" + File.separator;
    public static final String DIR_IMAGE = BASE_URL + "Image" + File.separator;
    public static final String DIR_CACHE_SD = BASE_URL + ".Cache" + File.separator;

    public static void initFilesDir() {
//        if (!isSecondRun()) {
//            setSecondRun(true);
//            FileUtils.deleteFilesInDir(BASE_URL);
//        }
//
//        if (!PermissionsUtils.checkWriteStoargePermissions(AppContext.getAppContext())) {
//            return;
//        }
        FileUtils.createOrExistsDir(DIR_LOG);
        FileUtils.createOrExistsDir(DIR_DOWN);
        FileUtils.createOrExistsDir(DIR_BOOK);
        FileUtils.createOrExistsDir(DIR_Voice);
        FileUtils.createOrExistsDir(DIR_Video);
        FileUtils.createOrExistsDir(DIR_IMAGE);
        FileUtils.createOrExistsDir(DIR_CACHE_SD);
    }
}
