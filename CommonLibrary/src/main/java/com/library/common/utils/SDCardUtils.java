package com.library.common.utils;

import android.os.Environment;

/**
 * 日期：2019/4/3 17:12
 * 创建：李加蒙
 * 描述：
 */
public class SDCardUtils {
    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
