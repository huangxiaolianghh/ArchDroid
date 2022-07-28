package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.databinding.ActivityCustomLceBinding;
import com.huangxiaoliang.mvparchdemo.delegate.CLoadingViewDelegate;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

import androidx.annotation.Nullable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/23 10:39</pre>
 * <pre>@desc 自定义加载布局Demo</pre>
 */
public class CustomLCEActivity extends BaseBindingActivity<ActivityCustomLceBinding> {

    @Override
    public String getPageTitle() {
        return "自定义LCE";
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        getLoadingStateView().register(new CLoadingViewDelegate());
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