package com.huangxiaoliang.mvplib.manager.lcet;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/7/12 22:55</pre>
 * <pre>@desc 弹窗Loading接口</pre>
 */
public interface ILoadingPopupView {

    /**
     * 显示弹窗
     */
    void showLoadingPopup();

    /**
     * 显示弹窗
     *
     * @param cancelable 返回键是否可关闭弹窗
     */
    void showLoadingPopup(boolean cancelable);

    /**
     * 显示弹窗
     *
     * @param msg 弹窗信息
     */
    void showLoadingPopup(String msg);

    /**
     * 显示弹窗
     *
     * @param msg        弹窗信息
     * @param cancelable 返回键是否可关闭弹窗
     */
    void showLoadingPopup(String msg, boolean cancelable);

    /**
     * 关闭弹窗
     */
    void dismissLoadingPopup();

    /**
     * 释放资源
     */
    void release();

}
