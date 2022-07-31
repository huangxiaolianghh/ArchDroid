package com.huangxiaoliang.mvplib.mvp;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 0:03</pre>
 * <pre>@desc Fragment需要继承的接口</pre>
 */
public interface IFragment extends IView {

    /**
     * 是否使用懒加载
     *
     * @return 懒加载开关
     */
    boolean isUseLazyLoad();

    /**
     * 懒加载
     */
    void onLazyLoadData();

}
