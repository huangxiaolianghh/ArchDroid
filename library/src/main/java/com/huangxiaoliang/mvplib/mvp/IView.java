package com.huangxiaoliang.mvplib.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/6 0:57</pre>
 * <pre>@desc 页面相关对外属性接口</pre>
 */
public interface IView extends ILCEView {

    /**
     * 获取上下文Activity对象
     *
     * @return Activity
     */
    Activity getContext();

    /**
     * inVisible view
     *
     * @param view view集合
     */
    void inVisible(View... view);

    /**
     * visible view
     *
     * @param view view集合
     */
    void visible(View... view);

    /**
     * gone view
     *
     * @param view view集合
     */
    void gone(View... view);

    /**
     * view 是否可见（visible）
     *
     * @param view 目标view
     * @return 是否visible
     */
    boolean isVisible(View view);

    /**
     * view 是否可见（visible）
     *
     * @param viewId 目标view id
     * @return 是否visible
     */
    boolean isVisible(@IdRes int viewId);

    /**
     * view 是否隐藏（gone）
     *
     * @param view 目标view
     * @return 是否gone
     */
    boolean isGone(View view);

    /**
     * view 是否隐藏（gone）
     *
     * @param viewId 目标view id
     * @return 是否gone
     */
    boolean isGone(@IdRes int viewId);

    /**
     * 根据id获取view实例
     *
     * @param viewId view id
     * @param <V>    view类型
     * @return view实例
     */
    <V extends View> V findView(@IdRes int viewId);

    /**
     * 根据id获取view实例，且可传递点击监听器
     *
     * @param viewId view id
     * @param l      view 点击事件监听器
     * @param <V>    view实例
     * @return view实例
     */
    <V extends View> V findView(@IdRes int viewId, View.OnClickListener l);

    /**
     * 添加任务的Disposable到管理集合中
     *
     * @param disposable 任务
     */
    void addDispose(Disposable disposable);

    /**
     * 将管理集合中的任务取消
     */
    void unDispose();

    /**
     * 处理逻辑前的方法
     *
     * @param savedInstanceState Bundle
     */
    void onBeforeBusiness(@Nullable Bundle savedInstanceState);

    /**
     * 获取LCE代理对象
     *
     * @return LCE代理对象
     */
    ILCEView getLCEDelegate();

    /**
     * 是否开启EventBus
     *
     * @return EventBus开关
     */
    boolean isUseEventBus();

    /**
     * 是否需要保护屏幕，不允许截屏
     *
     * @return 截屏保护开关
     */
    boolean isNeedPreventScreenCapture();


}
