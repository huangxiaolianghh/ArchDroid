package com.huangxiaoliang.mvparchdemo.util;

import com.huangxiaoliang.mvplib.mvp.IView;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 10:51
 * @Description : 描述
 */
public class HttpUtils {

    public static void requestNet(IView view, NetCallback<Long> netCallback) {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (view != null) {
                            view.addDispose(d);
                        }
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (netCallback != null) {
                            netCallback.onSuccess(aLong);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (netCallback != null) {
                            netCallback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        UILog.e("onComplete()");
                    }
                });
    }
}