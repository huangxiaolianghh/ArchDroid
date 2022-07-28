package com.huangxiaoliang.mvparchdemo.util;

import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.mvp.IView;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/8 10:51</pre>
 * <pre>@desc 模拟网络请求工具类</pre>
 */
public class HttpUtils {

    public static final String IMG = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000" +
            ".com%2Fwallpaper%2F2020-06-29%2F5ef9b315417b8" +
            ".jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999," +
            "10000&q=a80&n=0&g=0n&fmt=auto?sec=1657890578&t" +
            "=11177abaff83a7971b98f5a40b97d1b2";

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