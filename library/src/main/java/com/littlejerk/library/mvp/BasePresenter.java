package com.littlejerk.library.mvp;

import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.util.ClassLoadUtils;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 10:26
 * @Description : 可监听页面生命周期的Presenter基类
 */
public abstract class BasePresenter<M extends BaseModel, V extends IView> implements IPresenter<V>, DefaultLifecycleObserver {

    private static final String TAG = "BasePresenter";

    private WeakReference<V> v;
    private M m;

    private CompositeDisposable mCompositeDisposable;


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
    protected V getV() {
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
    protected M getM() {
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
        unDispose();
        this.mCompositeDisposable = null;
        UILog.d(TAG, TAG + " onDestroy被调用");
    }

    /**
     * 添加RxJava任务、
     * 已使用 {@link com.trello.rxlifecycle4.RxLifecycle} 避免内存泄漏,此方法可不用
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有 Disposable 放入容器集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    private void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void onStart() {
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void onResume() {
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    public void onPause() {
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    public void onStop() {
//    }
//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    public void onAny(LifecycleOwner source, Lifecycle.Event event) {
//    }

}
