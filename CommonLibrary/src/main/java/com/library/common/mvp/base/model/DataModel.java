package com.library.common.mvp.base.model;

/**
 * 日期：2019/3/9 13:32
 * 创建：李加蒙
 * 描述：
 */
public class DataModel {
    /**
     * @param token
     * @return
     */
    public static BaseModel request(String token) {
        BaseModel model = null;//声明一个空的BaseModel
        try {
            model = (BaseModel) Class.forName(token).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * @param aClass
     * @return
     */
    public static BaseModel request(Class aClass) {
        BaseModel model = null;//声明一个空的BaseModel
        try {
            model = (BaseModel) aClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return model;
    }
}
