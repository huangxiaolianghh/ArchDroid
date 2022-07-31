package com.huangxiaoliang.mvplib.mvp;

import androidx.lifecycle.LifecycleOwner;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/11 00:04</pre>
 * <pre>@desc model接口类</pre>
 */
public interface IModel {
    /**
     * 在框架中 {@link BasePresenter#onDestroy(LifecycleOwner)} 时会默认调用 {@link IModel#onDestroy()}
     */
    void onDestroy();
}
