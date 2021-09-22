package com.littlejerk.mvparch.activity.mvp;


import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BasePresenter;
import com.littlejerk.mvparch.listener.NetCallback;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 11:53
 * @Description : Activity Presenter
 */
public class MvpDemoActivityPresenter extends BasePresenter<MvpDemoActivityModel, AContract.MyActivityView>
        implements AContract.MyActivityPresenter {
    private static final String TAG = "MvpDemoActivityPresenter";


    @Override
    public void loadData() {
        getV().stateLoadingView();
        getM().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getV().addDispose(d);
            }

            @Override
            public void onSuccess(Long o) {
                getV().stateContentView();
                getV().showToast();
            }

            @Override
            public void onFailure(String msg) {
                getV().stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public void onReload() {
        getV().stateLoadingView();
        getM().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getV().addDispose(d);
            }

            @Override
            public void onSuccess(Long aLong) {
                getV().stateContentView();

            }

            @Override
            public void onFailure(String msg) {
                getV().stateErrorView();
                UIToast.showShort(msg);
            }
        });
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
