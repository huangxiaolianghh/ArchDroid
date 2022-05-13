package com.huangxiaoliang.mvplib.manager.lcet;

import android.view.View;

import com.huangxiaoliang.mvplib.mvp.BaseActivity;
import com.huangxiaoliang.mvplib.mvp.BaseFragment;

import org.json.JSONObject;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 17:53
 * @Description : 数据加载UI相关接口
 */
public interface ILCEView {

    void stateEmptyView();

    void stateErrorView();

    void stateLoadingView();

    void stateContentView();

    void loadingDialogShow();

    void loadingDialogShow(boolean cancelable);

    void loadingDialogShow(String msg);

    void loadingDialogShow(String msg, boolean cancelable);

    void loadingDialogShow(String msg, boolean cancelable, JSONObject extraData);

    void loadingDialogDismiss();

    /**
     * 获取页面真正RootView
     *
     * @return see {@link BaseActivity#getRealRootView()} or {@link BaseFragment#getRealRootView()}
     */
    View getRealRootView();

    /**
     * 设置标题
     *
     * @param iTitleView 标题属性类{@link ITitleView}
     */
    void setTitleBar(ITitleView iTitleView);

    /**
     * 释放资源
     */
    void release();

}
