package com.littlejerk.library.mvp;

import com.littlejerk.library.manager.lcet.ILCEView;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/7 0:03
 * @Description : Fragment需要继承的接口
 */
public interface IFragment extends IView {

    ILCEView getLCEDelegate();

    boolean useLazyLoad();

    void lazyLoadData();

    boolean useEventBus();

    void bindUI();

    boolean needPreventScreenCapture();

    void doPreBusiness();

}
