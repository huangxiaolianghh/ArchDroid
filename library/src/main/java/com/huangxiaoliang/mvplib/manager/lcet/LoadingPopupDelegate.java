package com.huangxiaoliang.mvplib.manager.lcet;

import android.app.Activity;
import android.text.TextUtils;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/8 17:48</pre>
 * <pre>@desc LCE代理实现</pre>
 */
public class LoadingPopupDelegate implements ILoadingPopupView {

    private Activity mActivity;
    /**
     * 加载框 https://github.com/Kaopiz/KProgressHUD
     */
    private KProgressHUD mKProgressHUD;

    private LoadingPopupDelegate(Activity activity) {
        mActivity = activity;
    }

    /**
     * 初始化 ILoadingPopupView
     *
     * @param activity Activity
     * @return ILoadingPopupView
     */
    public static ILoadingPopupView create(Activity activity) {
        return new LoadingPopupDelegate(activity);
    }

    @Override
    public void showLoadingPopup() {
        showLoadingPopup(null);
    }

    @Override
    public void showLoadingPopup(boolean cancelable) {
        showLoadingPopup(null, cancelable);
    }

    @Override
    public void showLoadingPopup(String msg) {
        showLoadingPopup(msg, false);
    }

    @Override
    public void showLoadingPopup(String msg, boolean cancelable) {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(mActivity);
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
     * 关闭加载框
     */
    @Override
    public void dismissLoadingPopup() {
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
        mActivity = null;
    }
}
