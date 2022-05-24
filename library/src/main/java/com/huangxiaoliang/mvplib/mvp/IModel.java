package com.huangxiaoliang.mvplib.mvp;

import androidx.lifecycle.LifecycleOwner;

/**
 * @author : HHotHeart
 * @date : 2021/6/11 00:04
 * @desc : 描述
 */
public interface IModel {
    /**
     * 在框架中 {@link BasePresenter#onDestroy(LifecycleOwner)} 时会默认调用 {@link IModel#onDestroy()}
     */
    void onDestroy();
}
