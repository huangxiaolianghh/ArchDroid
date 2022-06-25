package com.huangxiaoliang.mvplib.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.blankj.utilcode.util.ObjectUtils;
import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.huangxiaoliang.mvplib.manager.MVPConst;
import com.huangxiaoliang.mvplib.manager.UIStatusBar;
import com.huangxiaoliang.mvplib.manager.event.EventManager;
import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;
import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;
import com.huangxiaoliang.mvplib.manager.lcet.LCEDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.util.ClassLoadUtils;
import com.huangxiaoliang.mvplib.util.CommonUtils;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import org.json.JSONObject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/5 16:31</pre>
 * <pre>@desc 拥有Lifecycle特性的Activity基类</pre>
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IActivity {

    /**
     * 加载中（L）、内容（C）、错误与空视图（E）代理
     */
    private ILCEView mLceDelegate = null;

    /**
     * ContentView
     */
    private View mContentView = null;

    /**
     * 标题相关属性
     */
    private ITitleView mTitleView = null;

    /**
     * 订阅关系Disposable组合
     */
    private CompositeDisposable mCompositeDisposable;

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
        getLCEDelegate();
        ObjectUtils.requireNonNull(mLceDelegate, "must set LCE delegate");
        if (!TextUtils.isEmpty(getPageTitle())) {
            mTitleView = new TitleParam(getPageTitle());
        }
        if (getPageTitleView() != null) {
            mTitleView = getPageTitleView();
        }
        onDecorateTitleBar(mTitleView);
        onDecorateStatusBar();
        onBeforeBusiness(savedInstanceState);
        onBusiness(savedInstanceState);
    }

    /**
     * 设置布局
     *
     * @param savedInstanceState
     */
    protected abstract void initContentView(@Nullable Bundle savedInstanceState);

    /**
     * 处理业务逻辑
     *
     * @param savedInstanceState
     */
    protected abstract void onBusiness(@Nullable Bundle savedInstanceState);


    /**
     * 处理业务逻辑之前，可在这做一些其它逻辑，如重新设置LoadingView等
     */
    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 设置标题
     *
     * @param titleView 标题属性配置
     */
    @Override
    public final void onDecorateTitleBar(ITitleView titleView) {
        if (titleView != null) {
            getLCEDelegate().onDecorateTitleBar(titleView);
        }
    }

    /**
     * 装饰状态栏
     */
    private void onDecorateStatusBar() {
        if (isDecorateStatusBar()) {
            //注意：如果要设置状态栏相关元素，需在设置标题之后
            if (MVPArchConfig.getInstance().isLightStatusBar()) {
                UIStatusBar.setLightMode(this);
            } else {
                UIStatusBar.setDarkMode(this);
            }
            UIStatusBar.setBgColor(this, MVPArchConfig.getInstance().getStatusBarColor());
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
     * @return
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
     * 获取LEC代理
     *
     * @return LCE接口类
     */
    @Override
    public ILCEView getLCEDelegate() {
        if (mLceDelegate == null) {
            View rootView = ((ViewGroup) findView(android.R.id.content)).getChildAt(0);
            String metaStr = CommonUtils.getManifestsMetaStr(MVPConst.LCE_DELEGATE);
            if (!TextUtils.isEmpty(metaStr)) {
                //初始化项目注册的LCE代理器
                mLceDelegate = ClassLoadUtils.newLCEDelegate(metaStr, rootView);
            }
            //默认使用框架的LEC代理器
            if (mLceDelegate == null) {
                mLceDelegate = LCEDelegate.create(rootView);
            }
        }
        return mLceDelegate;
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

    @Override
    public View getDecorView() {
        return findView(android.R.id.content);
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
     * 空数据视图，调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateEmptyView() {
        getLCEDelegate().stateEmptyView();
    }

    /**
     * 错误视图，调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateErrorView() {
        getLCEDelegate().stateErrorView();
    }

    /**
     * 加载中视图，调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateLoadingView() {
        getLCEDelegate().stateLoadingView();
    }

    /**
     * 内容视图
     */
    @Override
    public void stateContentView() {
        getLCEDelegate().stateContentView();
    }

    /**
     * Loading Dialog的显示
     */
    @Override
    public void loadingDialogShow() {
        getLCEDelegate().loadingDialogShow();
    }


    /**
     * Loading Dialog的显示
     *
     * @param cancelable 是否可关闭
     */
    @Override
    public void loadingDialogShow(boolean cancelable) {
        getLCEDelegate().loadingDialogShow(cancelable);
    }

    /**
     * Loading Dialog的显示
     *
     * @param msg dialog的显示文字
     */
    @Override
    public void loadingDialogShow(String msg) {
        getLCEDelegate().loadingDialogShow(msg);
    }

    /**
     * Loading Dialog的显示
     *
     * @param msg        dialog的显示文字
     * @param cancelable 是否可关闭
     */
    @Override
    public void loadingDialogShow(String msg, boolean cancelable) {
        getLCEDelegate().loadingDialogShow(msg, cancelable);
    }

    /**
     * Loading Dialog的显示
     *
     * @param msg        dialog的显示文字
     * @param cancelable 是否可关闭
     * @param extraData  扩展参数
     */
    @Override
    public void loadingDialogShow(String msg, boolean cancelable, JSONObject extraData) {
        getLCEDelegate().loadingDialogShow(msg, cancelable, extraData);
    }

    /**
     * Loading Dialog的隐藏
     */
    @Override
    public void loadingDialogDismiss() {
        getLCEDelegate().loadingDialogDismiss();
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
        getLCEDelegate().release();
        mLceDelegate = null;
    }

    /**
     * 添加RxJava任务、
     * 已使用 {@link com.trello.rxlifecycle4.RxLifecycle} 避免内存泄漏,此方法可不用
     *
     * @param disposable
     */
    @Override
    public void addDispose(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        //将所有 Disposable 放入容器集中处理
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    @Override
    public void unDispose() {
        if (mCompositeDisposable != null) {
            //保证 Activity 结束时取消所有正在执行的订阅
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
        mTitleView = null;
        unDispose();
        release();
        if (isUseEventBus()) {
            EventManager.getBus().unregister(this);
        }
    }
}