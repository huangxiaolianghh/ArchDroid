package com.huangxiaoliang.mvplib.mvp;

import android.content.Context;

import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.util.ClassLoadUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * @author HHotHeart
 * @date 2021/6/14 23:16
 * @desc MVP模式Fragment基类
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment {
    private static final String TAG = "BaseMVPFragment";
    private P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mPresenter = null;
        mPresenter = ClassLoadUtils.getT(this, 0);
        Objects.requireNonNull(mPresenter, "p can not be null");
        mPresenter.attachV(this);
        mPresenter.getMvpModel().setContext(getContext());
        mPresenter.getMvpModel().setArgs(getArguments());
        mPresenter.getMvpModel().initData();
        //Activity与presenter生命周期绑定
        getLifecycle().addObserver(mPresenter);
    }

    /**
     * 获取Presenter实例
     *
     * @return see {@link BasePresenter}
     */
    public P getMvpPresenter() {
        return mPresenter;
    }

    /**
     * 在此之前会调用{@link BasePresenter#onDestroy(LifecycleOwner)}
     */
    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
        UILog.d(TAG, TAG + " onDestroy被调用");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        UILog.d(TAG, TAG + " onDetach被调用");
        getLifecycle().removeObserver(mPresenter);
    }


//    Fragment【onAttach】
//    Fragment【onCreate】
//    Observer【onCreate】
//
//    Fragment【onViewCreated】
//    Fragment【onActivityCreated】
//    Fragment【onStart】
//    Observer【onStart】
//
//    Fragment【onResume】
//    Observer【onResume】
//            ------------------------------
//    Observer【onPause】
//    Fragment【onPause】
//
//    Observer【onStop】
//    Fragment【onStop】
//    Fragment【onDestroyView】
//
//    Observer【onDestroy】
//    Fragment【onDestroy】
//    Fragment【onDetach】


}
