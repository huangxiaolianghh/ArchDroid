package com.littlejerk.mvparch.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.dylanc.loadinghelper.LoadingHelper;
import com.dylanc.loadinghelper.ViewType;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.littlejerk.library.manager.lcet.ITitleView;
import com.littlejerk.library.manager.lcet.TitleBarAdapter;
import com.littlejerk.library.manager.lcet.ILCEView;

import org.json.JSONObject;

/**
 * @author : HHotHeart
 * @date : 2021/7/9 17:36
 * @desc : 自定义LCE代理类，需在清单文件注册meta
 */
public class CustomLCEDelegate implements ILCEView {

    private Context mContext = null;
    private View mRealRootView = null;
    //加载中、加载失败、空布局视图 https://github.com/DylanCaiCoding/LoadingHelper
    private LoadingHelper mLoadingHelper = null;
    //加载框 https://github.com/Kaopiz/KProgressHUD
    private KProgressHUD mKProgressHUD = null;


    public CustomLCEDelegate(View rootView) {
        mContext = rootView.getContext();
        mLoadingHelper = new LoadingHelper(rootView);
        mRealRootView = mLoadingHelper.getDecorView();
    }

    /**
     * 获取真正的RootView
     *
     * @return
     */
    @Override
    public View getRealRootView() {
        return mRealRootView;
    }

    /**
     * 设置标题
     * 如果页面滑动对标题有动作，不建议使用LoadingHelper设置标题
     *
     * @param titleParam
     */
    @Override
    public void setTitleBar(ITitleView titleParam) {
        mLoadingHelper.register(ViewType.TITLE, new TitleBarAdapter(titleParam));
        mLoadingHelper.setDecorHeader(ViewType.TITLE);
    }

    /**
     * 空数据视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateEmptyView() {
        mLoadingHelper.showEmptyView();
    }

    /**
     * 错误视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateErrorView() {
        mLoadingHelper.showErrorView();
    }

    /**
     * 加载中视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateLoadingView() {
        mLoadingHelper.showLoadingView();
    }

    /**
     * 显示内容视图
     */
    @Override
    public void stateContentView() {
        mLoadingHelper.showContentView();
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

    /**
     * 释放资源
     */
    @Override
    public void release() {
        mKProgressHUD = null;
        mContext = null;
        mLoadingHelper = null;
        mRealRootView = null;
    }

    public LoadingHelper getLoadingHelper() {
        return mLoadingHelper;
    }

    public KProgressHUD getKProgressHUD() {
        return mKProgressHUD;
    }
}
