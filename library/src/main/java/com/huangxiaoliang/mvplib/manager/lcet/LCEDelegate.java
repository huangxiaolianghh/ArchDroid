package com.huangxiaoliang.mvplib.manager.lcet;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.dylanc.loadingstateview.LoadingStateView;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 17:48
 * @Description : LCE代理实现
 */
public class LCEDelegate implements ILCEView {

    private Context mContext = null;
    /**
     * 加载中、加载失败、空布局视图 https://github.com/DylanCaiCoding/LoadingStateView
     */
    private LoadingStateView mLoadingStateView = null;
    /**
     * 加载框 https://github.com/Kaopiz/KProgressHUD
     */
    private KProgressHUD mKProgressHUD = null;

    private LCEDelegate(View contentView) {
        mContext = contentView.getContext();
        mLoadingStateView = new LoadingStateView(contentView);
    }

    /**
     * 初始化ILCEView
     *
     * @param contentView layoutId
     * @return
     */
    public static ILCEView create(View contentView) {
        return new LCEDelegate(contentView);
    }

    /**
     * 获取DecorView
     *
     * @return DecorView
     */
    @Override
    public View getDecorView() {
        return mLoadingStateView.getDecorView();
    }

    /**
     * 空数据视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateEmptyView() {
        mLoadingStateView.showEmptyView();
    }

    /**
     * 错误视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateErrorView() {
        mLoadingStateView.showErrorView();
    }

    /**
     * 加载中视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateLoadingView() {
        mLoadingStateView.showLoadingView();
    }

    /**
     * 显示内容视图
     */
    @Override
    public void stateContentView() {
        mLoadingStateView.showContentView();
    }

    @Override
    public void loadingDialogShow() {
        loadingDialogShow(null);
    }

    @Override
    public void loadingDialogShow(boolean cancelable) {
        loadingDialogShow(null, cancelable);
    }

    @Override
    public void loadingDialogShow(String msg) {
        loadingDialogShow(msg, false);
    }

    @Override
    public void loadingDialogShow(String msg, boolean cancelable) {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(mContext);
        }
        if (!TextUtils.isEmpty(msg)) {
            mKProgressHUD.setLabel(msg);
        } else {
            mKProgressHUD.setLabel(null);
        }
        mKProgressHUD.setCancellable(cancelable);
        mKProgressHUD.show();
    }

    /**
     * 显示加载框的扩展
     *
     * @param msg
     * @param cancelable
     * @param extraData  拓展json字符串
     */
    @Override
    public void loadingDialogShow(String msg, boolean cancelable, JSONObject extraData) {

    }

    /**
     * 关闭加载框
     */
    @Override
    public void loadingDialogDismiss() {
        if (mKProgressHUD != null && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void onDecorateTitleBar(ITitleView titleView) {
        if (titleView != null) {
            mLoadingStateView.setHeaders(new GTitleBarViewDelegate(titleView));
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void release() {
        mKProgressHUD = null;
        mContext = null;
        mLoadingStateView = null;
    }

    public LoadingStateView getLoadingViewState() {
        return mLoadingStateView;
    }

    public KProgressHUD getLoadingDialog() {
        return mKProgressHUD;
    }
}
