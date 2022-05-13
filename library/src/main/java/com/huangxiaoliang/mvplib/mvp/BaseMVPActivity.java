package com.huangxiaoliang.mvplib.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.util.ClassLoadUtils;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

/**
 * @uthor HHotHeart
 * @time 2021/6/11 11:54
 * @description MVP模式Activity基类
 */
@SuppressWarnings("rawtypes")
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {
    private static final String TAG = "BaseMVPActivity";
    private P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = null;
        mPresenter = ClassLoadUtils.getT(this, 0);
        Objects.requireNonNull(mPresenter, "p can not be null");
        mPresenter.attachV(this);
        mPresenter.getM().setContext(getContext());
        mPresenter.getM().setIntent(getIntent());
        //Activity与presenter生命周期绑定
        getLifecycle().addObserver(mPresenter);
        super.onCreate(savedInstanceState);

    }

    /**
     * 获取Presenter实例
     *
     * @return see {@link BasePresenter}
     */
    public P getP() {
        return mPresenter;
    }


    /**
     * 在此之前会调用{@link BasePresenter#onDestroy(LifecycleOwner)}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mPresenter);
        mPresenter = null;
        UILog.d(TAG, TAG + " onDestroy被调用");
    }


//    Activity【onCreate】
//    Observer【onCreate】
//    Activity【onStart】
//    Observer【onStart】
//    Activity【onResume】
//    Observer【onResume】
//            ------------------------------
//    Observer【onPause】
//    Activity【onPause】
//    Observer【onStop】
//    Activity【onStop】
//    Observer【onDestroy】
//    Activity【onDestroy】

}
