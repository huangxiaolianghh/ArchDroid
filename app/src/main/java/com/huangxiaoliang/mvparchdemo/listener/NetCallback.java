package com.huangxiaoliang.mvparchdemo.listener;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/22 19:57</pre>
 * <pre>@desc 模拟网络请求回调</pre>
 */
public interface NetCallback<T> {
    default void onSubscribe(Disposable d) {
    }

    void onSuccess(T t);

    void onFailure(String msg);
}
