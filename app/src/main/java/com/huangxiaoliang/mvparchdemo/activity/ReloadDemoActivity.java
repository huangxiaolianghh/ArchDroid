package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.OnReloadListener;
import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityReloadDemoBinding;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.CustomLCEDelegate;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseActivity;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/9/21 22:56
 * @Description : 内容重新加载Demo
 */
public class ReloadDemoActivity extends BaseActivity implements OnReloadListener {

    private ActivityReloadDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        binding = ActivityReloadDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot(), "Reload Demo");

    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        super.onBeforeBusiness(savedInstanceState);
        LoadingStateView loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingViewState();
        loadingHelper.setOnReloadListener(this);
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