package com.huangxiaoliang.mvparchdemo.listener;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @author : HHotHeart
 * @date : 2021/9/22 19:57
 * @desc : 描述
 */
public interface NetCallback<T> {
    default void onSubscribe(Disposable d) {
    }

    void onSuccess(T t);

    void onFailure(String msg);
}
