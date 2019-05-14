package com.library.common.utils;

import java.io.File;

/**
 * 日期：2019/3/11 11:37
 * 创建：李加蒙
 * 描述：
 */
public class FileUtils {

    /**
     * @param filePath 文件地址
     * @return file 文件对象
     */
    public static File getFile(String filePath) {
        File file = null;
        if (!StringUtils.isEmpty(filePath)) {
            file = new File(filePath);
        }
        return file;
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(String filePath) {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath 文件路径
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {// 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

//    public static File saveFile(String filePath, ResponseBody body) {
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        File file = null;
//        try {
//            if (StringUtils.isEmpty(filePath)) {
//                return null;
//            }
//            file = new File(filePath);
//            if (file == null || !file.exists()) {
//                file.createNewFile();
//            }
//            long fileSize = body.contentLength();
//            long fileSizeDownload = 0;
//            byte[] fileReader = new byte[4096];
//            inputStream = body.byteStream();
//            outputStream = new FileOutputStream(file);
//            while (true) {
//                int read = inputStream.read(fileReader);
//                if (read == -1) break;
//                outputStream.write(fileReader, 0, read);
//                fileSizeDownload += read;
//            }
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return file;
//    }

    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

}
