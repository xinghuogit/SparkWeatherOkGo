package com.spark.sparkweatherokgo.app;

import com.library.common.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 日期：2019/4/3 16:56
 * 创建：李加蒙
 * 描述：应用程序异常类：用于捕获异常和提示错误信息
 */
public class AppException extends Exception implements Thread.UncaughtExceptionHandler {

    /**
     * 定义异常类型
     */
    public final static byte TYPE_NETWORK = 0x01;
    public final static byte TYPE_SOCKET = 0x02;
    public final static byte TYPE_HTTP_CODE = 0x03;
    public final static byte TYPE_HTTP_ERROR = 0x04;
    public final static byte TYPE_XML = 0x05;
    public final static byte TYPE_IO = 0x06;
    public final static byte TYPE_RUN = 0x07;
    public final static byte TYPE_JSON = 0x08;

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private AppException() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    /**
     * @return 获取APP异常崩溃处理对象
     */
    public static AppException getAppExceptionHandler() {
        return new AppException();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(t, e);
        }
    }

    private boolean handleException(Throwable e) {
        if (e == null) return false;
        this.saveErrorLog(e);
        return true;
    }

    private void saveErrorLog(Throwable throwable) {
        String errorLog = "errorlog.txt";
        String logFilePath = "";
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            File file = new File(AppConfig.DIR_LOG);
            if (!file.exists()) file.mkdirs();
            logFilePath = AppConfig.DIR_LOG + errorLog;
            if (logFilePath == "") return; //没有挂载SD卡，无法写文件
            File logFile = new File(logFilePath);
            if (!logFile.exists()) logFile.createNewFile();
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.print("start------------" + DateUtils.getDateTime(System.currentTimeMillis()) + "------------start");
            throwable.printStackTrace(pw);
            pw.print("end------------" + DateUtils.getDateTime(System.currentTimeMillis()) + "------------end\n\n\n\n");
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) pw.close();
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
