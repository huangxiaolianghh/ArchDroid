package com.huangxiaoliang.mvplib.mvp;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/11 00:05</pre>
 * <pre>@desc presenter接口类</pre>
 */
public interface IPresenter<V> {

    /**
     * 绑定View
     *
     * @param view View实例
     */
    void attachV(V view);

    /**
     * 当前Presenter是否有View的实例
     *
     * @return
     */
    boolean isAttachV();

}
