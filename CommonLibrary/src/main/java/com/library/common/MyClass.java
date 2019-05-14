package com.library.common;

import com.library.common.utils.DateUtils;

import java.text.DecimalFormat;
import java.util.TimeZone;



/**
 * 日期：2019/4/3 16:12
 * 创建：李加蒙
 * 描述：
 */
public class MyClass {

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(0.25555));
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(TimeZone.getDefault().getRawOffset());
        System.out.println(DateUtils.getTodayStartTime(0));
        System.out.println(DateUtils.getDateTime(DateUtils.getTodayStartTime(0), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtils.getTodayStartTime(1));
        System.out.println(DateUtils.getDateTime(DateUtils.getTodayStartTime(1), "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtils.getTodayStartTime(2));
        System.out.println(DateUtils.getDateTime(DateUtils.getTodayStartTime(2), "yyyy-MM-dd HH:mm:ss"));


        System.out.println(DateUtils.getDateTime(1553443200, "MM-dd"));
    }
}
