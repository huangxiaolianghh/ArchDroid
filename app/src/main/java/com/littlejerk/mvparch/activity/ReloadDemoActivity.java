package com.littlejerk.mvparch.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.dylanc.loadinghelper.LoadingHelper;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.listener.NetCallback;
import com.littlejerk.mvparch.util.CustomLCEDelegate;
import com.littlejerk.mvparch.util.HttpUtils;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/9/21 22:56
 * @Description : 内容重新加载Demo
 */
public class ReloadDemoActivity extends BaseActivity implements LoadingHelper.OnReloadListener {


    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_reload_demo, "Reload Demo");

    }

    @Override
    public void doPreBusiness() {
        super.doPreBusiness();
        LoadingHelper loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingHelper();
        loadingHelper.setOnReloadListener(this::onReload);
        findView(R.id.tv_content, v -> UIToast.showLong("重新加载的内容"));

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
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