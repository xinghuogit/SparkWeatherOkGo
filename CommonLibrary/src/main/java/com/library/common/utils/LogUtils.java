package com.library.common.utils;

import com.orhanobut.logger.Logger;


/**
 * 日期：2019/4/1 15:40
 * 创建：李加蒙
 * 描述：
 */
public class LogUtils {
    private static final String tag = LogUtils.class.getSimpleName();

    /**
     * 是否需要日志打印配置信息
     */
    private static boolean logSwitch = true;
//    private static boolean logSwitch = AppConfig.APP_LOG_SWITCH;

    /**
     * Info日志
     *
     * @param message 信息
     */
    public static void i(Object message) {
        i(tag, message);
    }

    /**
     * Info日志
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void i(String tag, Object message) {
        i(tag, message, null);
    }

    /**
     * Info日志
     *
     * @param tag     标签
     * @param message 信息
     * @param tr      异常
     */
    public static void i(String tag, Object message, Throwable tr) {
        log(tag, message.toString(), 'i', tr);
    }

    /**
     * Warn日志
     *
     * @param message 信息
     */
    public static void w(Object message) {
        w(tag, message);
    }

    /**
     * Warn日志
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void w(String tag, Object message) {
        w(tag, message, null);
    }

    /**
     * Warn日志
     *
     * @param tag     标签
     * @param message 信息
     * @param tr      异常
     */
    public static void w(String tag, Object message, Throwable tr) {
        log(tag, message.toString(), 'w', tr);
    }

    /**
     * Error日志
     *
     * @param message 信息
     */
    public static void e(Object message) {
        e(tag, message);
    }

    /**
     * Error日志
     *
     * @param tag     标签
     * @param message 信息
     */
    public static void e(String tag, Object message) {
        e(tag, message, null);
    }

    /**
     * Error日志
     *
     * @param tag     标签
     * @param message 信息
     * @param tr      异常
     */
    public static void e(String tag, Object message, Throwable tr) {
        log(tag, message.toString(), 'e', tr);
    }

    /**
     * 异常打印
     *
     * @param tag 标签
     * @param e   异常
     */
    public static void e(String tag, Exception e) {
        if (logSwitch) {
            Logger.t(tag).e(e, "Exception");
        }
    }


    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag     标签
     * @param message 信息
     * @param type    类型
     * @param tr      异常
     */
    private static void log(String tag, String message, char type, Throwable tr) {
        if (logSwitch) {
            if ('v' == type || 'd' == type || 'i' == type) {
                Logger.t(tag).i(message);
            } else if ('w' == type) {
                Logger.t(tag).w(message);
            } else if ('e' == type) {
                Logger.t(tag).e(message);
            }
        }
    }

    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }
}
