package com.library.common.utils;


/**
 * 日期：2019/3/26 17:41
 * 创建：李加蒙
 * 描述：
 */
public class ObjectUtils {

    public static <T> T checkNotNull(T content, Object errorMessage) {
        if (content == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return content;
    }
}
