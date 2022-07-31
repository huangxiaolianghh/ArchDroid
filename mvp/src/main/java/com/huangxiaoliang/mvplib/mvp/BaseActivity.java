package com.huangxiaoliang.mvplib.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.blankj.utilcode.util.ObjectUtils;
import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.huangxiaoliang.mvplib.manager.MVPConst;
import com.huangxiaoliang.mvplib.manager.UIStatusBar;
import com.huangxiaoliang.mvplib.manager.event.EventManager;
import com.huangxiaoliang.mvplib.manager.lcet.GTitleBarViewDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.ILoadingPopupView;
import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;
import com.huangxiaoliang.mvplib.manager.lcet.LoadingPopupDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.util.ClassUtils;
import com.huangxiaoliang.mvplib.util.MvpArchUtils;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import static com.huangxiaoliang.mvplib.manager.UIStatusBar.STATUS_BAR_DARK_FONT;
import static com.huangxiaoliang.mvplib.manager.UIStatusBar.STATUS_BAR_WHITE_FONT;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/5 16:31</pre>
 * <pre>@desc 拥有Lifecycle特性的Activity基类</pre>
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IActivity {

    /**
     * 订阅关系Disposable组合
     */
    private CompositeDisposable mCompositeDisposable;
    /**
     * 弹窗代理对象
     */
    private ILoadingPopupView mLoadingPopupDelegate;
    /**
     * LCE-T
     */
    private LoadingStateView mLoadingStateView;
    /**
     * ContentView
     */
    private View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isNeedPreventScreenCapture()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        ObjectUtils.requireNonNull(mContentView, "must set contentViw");
        super.setContentView(mContentView);
        if (isUseEventBus()) {
            EventManager.getBus().register(this);
        }
        // LCE-T
        mLoadingStateView = new LoadingStateView(this);
        ITitleView titleView = null;
        if (!TextUtils.isEmpty(getPageTitle())) {
            titleView = new TitleParam(getPageTitle());
        }
        if (getPageTitleView() != null) {
            titleView = getPageTitleView();
        }
        if (titleView != null) {
            mLoadingStateView.setHeaders(new GTitleBarViewDelegate(titleView));
        }
        if (isDecorateStatusBar()) {
            UIStatusBar.setBgColor(this, MVPArchConfig.get().getStatusBarColor());
            UIStatusBar.setStatusBarMode(this, MVPArchConfig.get().isLightStatusBar()
                    ? STATUS_BAR_DARK_FONT : STATUS_BAR_WHITE_FONT);
        }
        onInitLoadingPopupDelegate();
        onBeforeBusiness(savedInstanceState);
        onBusiness(savedInstanceState);
    }

    /**
     * 初始化布局
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void initContentView(@Nullable Bundle savedInstanceState);

    /**
     * 处理业务逻辑
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void onBusiness(@Nullable Bundle savedInstanceState);

    /**
     * 处理业务逻辑之前，可在这做一些其它逻辑，如重新设置LoadingView等
     */
    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 初始化loading popup的代理
     */
    private void onInitLoadingPopupDelegate() {
        // 初始化弹窗代理对象
        if (mLoadingPopupDelegate == null) {
            String metaStr = MvpArchUtils.getManifestsMetaStr(MVPConst.LOADING_POPUP_DELEGATE);
            if (!TextUtils.isEmpty(metaStr)) {
                mLoadingPopupDelegate = ClassUtils.instanceByClasspath(metaStr, getContext());
            }
            if (mLoadingPopupDelegate == null) {
                mLoadingPopupDelegate = LoadingPopupDelegate.create(getContext());
            }
        }
    }

    /**
     * 默认使用框架装饰状态栏
     *
     * @return 是否使用框架装饰状态栏
     */
    @Override
    public boolean isDecorateStatusBar() {
        return true;
    }

    /**
     * 设置布局
     *
     * @param layoutId 布局id
     */
    @Override
    public void setContentView(@LayoutRes int layoutId) {
        mContentView = LayoutInflater.from(this).inflate(layoutId, null);
    }

    /**
     * 设置Activity contentView
     *
     * @param view Activity contentView
     */
    @Override
    public void setContentView(View view) {
        mContentView = view;
    }

    /**
     * 禁止使用LayoutParams参数
     *
     * @param view   Activity的View
     * @param params LayoutParams
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalArgumentException("forbid set LayoutParams");
    }

    /**
     * 标题
     *
     * @return 标题
     */
    @Override
    public String getPageTitle() {
        return null;
    }

    @Override
    public ITitleView getPageTitleView() {
        return null;
    }

    /**
     * 获取LoadingStateView
     *
     * @return LoadingStateView
     */
    @Override
    public LoadingStateView getLoadingStateView() {
        return mLoadingStateView;
    }

    /**
     * 获取弹窗代理对象
     *
     * @return 弹窗代理对象
     */
    @Override
    public ILoadingPopupView getLoadingPopupDelegate() {
        return mLoadingPopupDelegate;
    }

    /**
     * 获取当前Activity实例
     *
     * @return 当前Activity
     */
    @Override
    public Activity getContext() {
        return this;
    }

    /**
     * 默认不使用EventBus
     *
     * @return 是否使用EventBus
     */
    @Override
    public boolean isUseEventBus() {
        return false;
    }

    /**
     * 空数据视图
     */
    @Override
    public void stateEmptyView() {
        getLoadingStateView().showEmptyView();
    }

    /**
     * 错误视图
     */
    @Override
    public void stateErrorView() {
        getLoadingStateView().showErrorView();
    }

    /**
     * 加载中视图
     */
    @Override
    public void stateLoadingView() {
        getLoadingStateView().showLoadingView();
    }

    /**
     * 内容视图
     */
    @Override
    public void stateContentView() {
        getLoadingStateView().showContentView();
    }

    /**
     * Loading Dialog的显示
     */
    @Override
    public void showLoadingPopup() {
        getLoadingPopupDelegate().showLoadingPopup();
    }

    /**
     * Loading Dialog的显示
     *
     * @param cancelable 是否可关闭
     */
    @Override
    public void showLoadingPopup(boolean cancelable) {
        getLoadingPopupDelegate().showLoadingPopup(cancelable);
    }

    /**
     * Loading Dialog的显示
     *
     * @param msg dialog的显示文字
     */
    @Override
    public void showLoadingPopup(String msg) {
        getLoadingPopupDelegate().showLoadingPopup(msg);
    }

    /**
     * Loading Dialog的显示
     *
     * @param msg        dialog的显示文字
     * @param cancelable 是否可关闭
     */
    @Override
    public void showLoadingPopup(String msg, boolean cancelable) {
        getLoadingPopupDelegate().showLoadingPopup(msg, cancelable);
    }

    /**
     * Loading Dialog的隐藏
     */
    @Override
    public void dismissLoadingPopup() {
        getLoadingPopupDelegate().dismissLoadingPopup();
    }

    /**
     * 控件的隐藏（占用空间）
     *
     * @param views 控件View集合
     */
    @Override
    public void inVisible(View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 控件的显示
     *
     * @param views 控件View集合
     */
    @Override
    public void visible(View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 控件的隐藏
     *
     * @param views 控件View
     */
    @Override
    public void gone(View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setVisibility(View.GONE);
        }
    }

    /**
     * View是否显示
     *
     * @param view 控件View
     * @return 是否Visible
     */
    @Override
    public boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * View是否显示
     *
     * @param viewId 控件View ID
     * @return 是否Visible
     */
    @Override
    public boolean isVisible(int viewId) {
        return isVisible(findView(viewId));
    }

    /**
     * View是否隐藏
     *
     * @param view 控件View
     * @return 是否Gone
     */
    @Override
    public boolean isGone(View view) {
        return view.getVisibility() == View.GONE;
    }

    /**
     * View是否隐藏
     *
     * @param viewId 控件View ID
     * @return 是否Gone
     */
    @Override
    public boolean isGone(int viewId) {
        return isGone(findView(viewId));
    }

    /**
     * 根据id获取View实例
     *
     * @param viewId 控件id
     * @param <V>    类型
     * @return 返回V实例
     */
    @Override
    public <V extends View> V findView(int viewId) {
        V v = findViewById(viewId);
        return v;
    }

    /**
     * 根据id获取View实例，且实现了点击监听事件
     *
     * @param viewId 控件id
     * @param l      点击监听
     * @param <V>    类型
     * @return 返回V实例
     */
    @Override
    public <V extends View> V findView(int viewId, View.OnClickListener l) {
        V v = findView(viewId);
        v.setOnClickListener(l);
        return v;
    }

    /**
     * 释放资源
     */
    @Override
    public void release() {
        mLoadingPopupDelegate.release();
        mLoadingPopupDelegate = null;
    }

    /**
     * 添加RxJava任务，在页面销毁时注销，避免内存泄漏<br/>
     * 也可使用{@link com.trello.rxlifecycle4.RxLifecycle}进行任务和页面的绑定
     *
     * @param disposable Disposable
     */
    @Override
    public void addDispose(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止集合中正在执行的 RxJava 任务，保证 Activity 结束时取消所有正在执行的订阅
     */
    @Override
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 是否需要防止截屏功能  默认不需要
     *
     * @return 是否需要防截屏
     */
    @Override
    public boolean isNeedPreventScreenCapture() {
        return false;
    }

    /**
     * 拦截返回事件
     *
     * @return true 拦截，false 不拦截
     */
    @Override
    public boolean onInterceptBackPressed() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (onInterceptBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unDispose();
        release();
        if (isUseEventBus()) {
            EventManager.getBus().unregister(this);
        }
    }
}