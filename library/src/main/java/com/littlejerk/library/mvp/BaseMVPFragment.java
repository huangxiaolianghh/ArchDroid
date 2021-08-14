package com.littlejerk.library.mvp;

import android.content.Context;

import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.util.ClassLoadUtils;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * @author : HHotHeart
 * @date : 2021/6/14 23:16
 * @desc : MVP模式Fragment基类
 */
public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment {
    private static final String TAG = "BaseMVPFragment";
    private P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mPresenter = null;
        mPresenter = ClassLoadUtils.getT(this, 0);
        Objects.requireNonNull(mPresenter, "p can not be null");
        mPresenter.attachV(this);
        mPresenter.getM().setContext(getContext());
        mPresenter.getM().setArgs(getArguments());
        //Activity与presenter生命周期绑定
        getLifecycle().addObserver(mPresenter);
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
     * 在此之前会调用{@link BasePresenter#onDestroy()}
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
