package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.delegate.CLoadingViewDelegate;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.CustomLCEDelegate;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseActivity;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/9/23 10:39
 * @Description : 自定义加载布局Demo
 */
public class CustomLCEActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_lce, "自定义LCE");
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        LoadingStateView loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingViewState();
        loadingHelper.register(new CLoadingViewDelegate());
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        stateLoadingView();
        HttpUtils.requestNet(this, new NetCallback<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                stateContentView();
            }

            @Override
            public void onFailure(String msg) {
                stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }
}