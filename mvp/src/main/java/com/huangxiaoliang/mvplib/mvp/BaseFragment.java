package com.huangxiaoliang.mvplib.mvp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvplib.manager.MVPConst;
import com.huangxiaoliang.mvplib.manager.event.EventManager;
import com.huangxiaoliang.mvplib.manager.lcet.ILoadingPopupView;
import com.huangxiaoliang.mvplib.manager.lcet.LoadingPopupDelegate;
import com.huangxiaoliang.mvplib.util.ClassUtils;
import com.huangxiaoliang.mvplib.util.MvpArchUtils;
import com.trello.rxlifecycle4.components.support.RxFragment;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 00:05</pre>
 * <pre>@desc 拥有Lifecycle特性的Fragment基类</pre>
 */
public abstract class BaseFragment extends RxFragment implements IFragment {
    /**
     * Fragment绑定的Activity
     */
    private Activity mContext;
    /**
     * 订阅关系Disposable组合
     */
    private CompositeDisposable mCompositeDisposable;
    /**
     * 弹窗代理对象
     */
    private ILoadingPopupView mLoadingPopupDelegate;
    /**
     * LCE
     */
    private LoadingStateView mLoadingStateView;
    /**
     * 布局id
     */
    private int mLayoutId = 0;
    /**
     * 内容View
     */
    private View mContentView;
    private View mDecorView;

    /**
     * *************** 懒加载相关属性说明 *************** <br/><br/>
     * 是否已加载数据，懒加载实现参考 https://github.com/AndyJennifer/AndroidxLazyLoad
     */
    private boolean isDataLoaded = false;
    /**
     * 当前Fragment是否对用户可见
     */
    private boolean isVisibleToUser = false;
    /**
     * 控制ViewPager+Fragment形式下setUserVisibleHint方法导致的问题
     */
    private boolean isCallResume = false;
    /**
     * 是否调用了setUserVisibleHint方法，处理show+add+hide模式下，默认可见 Fragment 不调用onHiddenChanged 方法，进而不执行懒加载方法的问题。
     */
    private boolean isCallUserVisibleHint = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNeedPreventScreenCapture()) {
            requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initContentView(savedInstanceState);
        if (mDecorView != null) {
            ViewGroup parent = (ViewGroup) mDecorView.getParent();
            if (parent != null) {
                parent.removeView(mDecorView);
            }
        }
        if (mLayoutId != 0) {
            mContentView = inflater.inflate(mLayoutId, container, false);
        } else if (mContentView == null) {
            throw new IllegalArgumentException("must set layoutId or contentView");
        }
        // LCE
        mLoadingStateView = new LoadingStateView(mContentView);
        mDecorView = mLoadingStateView.getDecorView();
        onInitLoadingPopupDelegate();
        onBeforeBusiness(savedInstanceState);
        return mDecorView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isUseEventBus()) {
            EventManager.getBus().register(this);
        }
        doBusiness(savedInstanceState);
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract void initContentView(@Nullable Bundle savedInstanceState);

    /**
     * 处理业务逻辑
     */
    protected abstract void doBusiness(Bundle savedInstanceState);


    /**
     * 处理业务逻辑之前，可在这做一些其它逻辑，如重新设置LoadingView等
     */
    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 设置布局、标题相关属性
     *
     * @param layoutId 布局id
     */
    public void setContentView(@LayoutRes int layoutId) {
        this.mLayoutId = layoutId;
    }

    /**
     * 设置contentView、标题相关属性
     *
     * @param view Fragment contentView
     */
    public void setContentView(View view) {
        this.mContentView = view;
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
     * 是否需要防止截屏功能  默认不需要
     *
     * @return 是否需要防截屏
     */
    @Override
    public boolean isNeedPreventScreenCapture() {
        return false;
    }

    /**
     * 获取当前Activity实例
     *
     * @return 当前Activity
     */
    @NonNull
    @Override
    public Activity getContext() {
        if (mContext == null) {
            throw new IllegalArgumentException("Fragment must attach to Activity");
        }
        return mContext;
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
     * 释放资源
     */
    @Override
    public void release() {
        mLoadingPopupDelegate.release();
        mLoadingPopupDelegate = null;
    }

    /**
     * 添加RxJava任务、
     * 已使用 {@link com.trello.rxlifecycle4.RxLifecycle} 避免内存泄漏,此方法可不用
     *
     * @param disposable 订阅关系
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
        V v = mDecorView.findViewById(viewId);
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
     * 默认不使用懒加载
     *
     * @return 是否使用懒加载
     */
    @Override
    public boolean isUseLazyLoad() {
        return false;
    }

    /**
     * 懒加载空实现，如果想实现懒加载可重写此方法
     */
    @Override
    public void onLazyLoadData() {
    }

    /**
     * 判断是否懒加载
     */
    private void tryLazyLoadData() {
        if (!isDataLoaded && isVisibleToUser && isCallResume) {
            onLazyLoadData();
            isDataLoaded = true;
        }
    }

    /**
     * api已废弃，替代为{@link FragmentTransaction#setMaxLifecycle(Fragment, androidx.lifecycle.Lifecycle.State)}
     * Viewpager 适配器构造方法中可选择使用哪个api，现在ViewPager2采用了最新方案实现，
     *
     * @param isVisibleToUser 是否对用户可见
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isUseLazyLoad()) {
            this.isVisibleToUser = isVisibleToUser;
            isCallUserVisibleHint = true;
            tryLazyLoadData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUseLazyLoad()) {
            isCallResume = true;
            if (!isCallUserVisibleHint) {
                isVisibleToUser = !isHidden();
            }
            tryLazyLoadData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isUseLazyLoad()) {
            isVisibleToUser = !hidden;
            tryLazyLoadData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isUseLazyLoad()) {
            isDataLoaded = false;
            isVisibleToUser = false;
            isCallUserVisibleHint = false;
            isCallResume = false;
        }
        mLayoutId = 0;
        mContentView = null;
        unDispose();
        release();
        if (isUseEventBus()) {
            EventManager.getBus().unregister(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
