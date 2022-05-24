package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.content.Intent;

import com.blankj.utilcode.util.StringUtils;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 15:46
 * @Description : 描述
 */
public class MvpDemoActivityModel extends BaseModel<Intent> implements AContract.MyActivityModel {


    @Override
    protected void initData() {
        String data = getIntent().getStringExtra("MVP_DATA");
        UIToast.showLong(StringUtils.isEmpty(data) ? "测试TestModel" : data);
    }

    @Override
    public void requestNet(NetCallback<Long> netCallback) {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (netCallback != null) {
                            netCallback.onSubscribe(d);
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
