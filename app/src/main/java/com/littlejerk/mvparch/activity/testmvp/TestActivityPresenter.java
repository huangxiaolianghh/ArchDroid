package com.littlejerk.mvparch.activity.testmvp;


import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.mvp.BasePresenter;
import com.littlejerk.mvparch.util.HttpUtils;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 11:53
 * @Description : 描述
 */
public class TestActivityPresenter extends BasePresenter<TestActivityModel, AContract.MyActivityView>
        implements AContract.MyActivityPresenter {
    private static final String TAG = "TestPresenter";


    @Override
    public void loadData() {
        UILog.d(TAG, TAG + " onResume被调用");
        getV().stateLoadingView();
        HttpUtils.requestSuccess(new HttpUtils.Callback() {
            @Override
            public void onSuccess() {
                getV().stateContentView();
                getV().loadingDialogDismiss();
                getV().showToast();

            }

            @Override
            public void onFailure() {
                getV().stateErrorView();
                getV().loadingDialogDismiss();

            }
        });
    }

    @Override
    public void onReload() {
        getV().loadingDialogShow();
        getV().stateLoadingView();
        HttpUtils.requestSuccess(new HttpUtils.Callback() {
            @Override
            public void onSuccess() {
                getV().stateContentView();
                getV().loadingDialogDismiss();
            }

            @Override
            public void onFailure() {
                getV().stateErrorView();
                getV().loadingDialogDismiss();
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
