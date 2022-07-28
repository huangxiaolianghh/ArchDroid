package com.huangxiaoliang.mvparchdemo.util;

import android.app.Activity;
import android.text.TextUtils;

import com.huangxiaoliang.mvplib.manager.lcet.ILoadingPopupView;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/7/9 17:36</pre>
 * <pre>@desc 自定义LoadingPopupView代理类，需在清单文件注册meta</pre>
 */
public class CustomLoadingPopupDelegate implements ILoadingPopupView {

    private Activity mActivity;
    /**
     * 加载框 https://github.com/Kaopiz/KProgressHUD
     */
    private KProgressHUD mKProgressHUD;

    /**
     * 必不可少的构造函数，实例化时用到
     *
     * @param activity Activity
     */
    public CustomLoadingPopupDelegate(Activity activity) {
        mActivity = activity;
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
