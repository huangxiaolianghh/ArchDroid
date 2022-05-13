package com.huangxiaoliang.mvparchdemo.fragment.mvp;


import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BasePresenter;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.manager.log.UILog;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 11:53
 * @Description : Fragment Presenter
 */
public class MVPDemoFragmentPresenter extends BasePresenter<MVPDemoFragmentModel, FContract.MyFragmentView>
        implements FContract.MyFragmentPresenter {
    private static final String TAG = "MVPDemoFragmentPresenter";

    @Override
    public void loadData() {
        UILog.d(TAG, TAG + " onResume被调用");
        getV().stateLoadingView();
        getM().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getV().addDispose(d);
            }

            @Override
            public void onSuccess(Long aLong) {
                getV().stateErrorView();
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
