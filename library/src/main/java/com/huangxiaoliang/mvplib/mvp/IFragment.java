package com.huangxiaoliang.mvplib.mvp;

import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;

/**
 * @author HHotHeart
 * @time 2021/6/7 0:03
 * @description Fragment需要继承的接口
 */
public interface IFragment extends IView {

    ILCEView getLCEDelegate();

    boolean useLazyLoad();

    void lazyLoadData();

    boolean useEventBus();

    boolean needPreventScreenCapture();

}
