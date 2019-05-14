package com.library.common.base;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：长按事件接口
 */
public interface IItemLongClickCallback<T> {

    /**
     * @param o
     * @param clickedPosition
     */
    void onItemClicked(T o, int clickedPosition);
}
