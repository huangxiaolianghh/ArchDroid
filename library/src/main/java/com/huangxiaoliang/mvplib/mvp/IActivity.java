package com.huangxiaoliang.mvplib.mvp;

import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;

/**
 * @author HHotHeart
 * @date 2021/6/7 00:03
 * @desc Activity需要继承的接口
 */
public interface IActivity extends IView {

    ILCEView getLCEDelegate();

    boolean useEventBus();

    boolean needPreventScreenCapture();

    boolean isDecorateStatusBar();

}
