package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.dylanc.loadingstateview.OnReloadListener;
import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityReloadDemoBinding;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

import androidx.annotation.Nullable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/21 22:56</pre>
 * <pre>@desc 内容重新加载Demo</pre>
 */
public class ReloadDemoActivity extends BaseBindingActivity<ActivityReloadDemoBinding> implements OnReloadListener {

    @Override
    public String getPageTitle() {
        return "内容重新加载Demo";
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        super.onBeforeBusiness(savedInstanceState);
        getLoadingStateView().setOnReloadListener(this);
        findView(R.id.tv_content, v -> UIToast.showLong("重新加载的内容"));
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        stateLoadingView();
        HttpUtils.requestNet(this, new NetCallback<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                stateErrorView();
            }

            @Override
            public void onFailure(String msg) {
                stateErrorView();
                UIToast.showShort(msg);

            }
        });
    }

    @Override
    public void onReload() {
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