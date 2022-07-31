package com.huangxiaoliang.mvplib.mvp;

import android.content.Context;

import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.util.ClassUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/14 23:16</pre>
 * <pre>@desc MVP模式Fragment基类</pre>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment {
    private P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mPresenter = null;
        mPresenter = ClassUtils.getSuperClassGenericType(this, 0);
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
        UILog.i("BaseMVPFragment onDestroy()被调用");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        UILog.i("BaseMVPFragment onDetach()被调用");
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
