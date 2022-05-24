package com.huangxiaoliang.mvplib.mvp;

import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.util.ClassLoadUtils;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 10:26
 * @Description : 可监听页面生命周期的Presenter基类
 */
public abstract class BasePresenter<M extends BaseModel, V extends IView> implements IPresenter<V>, DefaultLifecycleObserver {

    private static final String TAG = "BasePresenter";

    private WeakReference<V> v;
    private M m;

    /**
     * 绑定View和初始化Model
     *
     * @param view View实例
     */
    @Override
    public void attachV(V view) {
        //弱引用实例化View
        v = new WeakReference<>(view);
        //泛型实例化Model
        m = ClassLoadUtils.getT(this, 0);
        Objects.requireNonNull(m, "m can not be null");
    }

    /**
     * 获取V实例
     *
     * @return
     */
    protected V getMvpView() {
        if (v != null && v.get() != null) {
            return v.get();
        }
        throw new NullPointerException("v can not be null");
    }

    /**
     * 获取M实例
     *
     * @return
     */
    protected M getMvpModel() {
        return m;
    }

    /**
     * View实例是否存在
     *
     * @return
     */
    @Override
    public boolean isAttachV() {
        return v != null && v.get() != null;
    }

    /**
     * 解绑View
     */
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    @Override
    public void onResume(@NonNull @NotNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull @NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull @NotNull LifecycleOwner owner) {

    }

    /**
     * 生命周期与{@link BaseMVPActivity#onDestroy()} or {@link BaseMVPFragment#onDestroy()}绑定
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        detachV();
        m.onDestroy();
        m = null;
        UILog.d(TAG, TAG + " onDestroy被调用");
    }
}
