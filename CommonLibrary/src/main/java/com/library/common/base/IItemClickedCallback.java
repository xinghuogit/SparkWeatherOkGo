package com.library.common.base;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：点击事件接口
 */
public interface IItemClickedCallback<T> {
    /**
     * 列表点击事件
     *
     * @param o
     * @param clickedPosition
     */
    public void onItemClicked(T o, int clickedPosition);
}
