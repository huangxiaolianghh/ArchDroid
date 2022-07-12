package com.huangxiaoliang.mvparchdemo.activity.mvp;


import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BasePresenter;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/11 11:53</pre>
 * <pre>@desc MVP Presenter</pre>
 */
public class MvpDemoActivityPresenter extends BasePresenter<MvpDemoActivityModel, MvpDemoActivity>
        implements AContract.MyActivityPresenter {
    private static final String TAG = "MvpDemoActivityPresenter";

    @Override
    public void loadData() {
        getMvpView().stateLoadingView();
        getMvpModel().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getMvpView().addDispose(d);
            }

            @Override
            public void onSuccess(Long o) {
                getMvpView().stateContentView();
                getMvpView().showToast();
            }

            @Override
            public void onFailure(String msg) {
                getMvpView().stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public void onReload() {
        getMvpView().stateLoadingView();
        getMvpModel().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getMvpView().addDispose(d);
            }

            @Override
            public void onSuccess(Long aLong) {
                getMvpView().stateContentView();

            }

            @Override
            public void onFailure(String msg) {
                getMvpView().stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public boolean onInterceptBackPressed() {
        UIToast.showCustomViewShort("你已经重写拦截了返回按键事件");
        return true;
    }

    @Override
    public void onResume(@NonNull @NotNull LifecycleOwner owner) {
        super.onResume(owner);
    }


    /**
     * BasePresenter实现了和Activity或Fragment生命周期绑定，重写即可
     *
     * @param owner
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        UILog.d(TAG, TAG + " onDestroy被调用");

    }

}
