package com.littlejerk.mvparch.activity;

import android.os.Bundle;

import com.dylanc.loadinghelper.LoadingHelper;
import com.dylanc.loadinghelper.ViewType;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.adapter.CLoadingAdapter;
import com.littlejerk.mvparch.listener.NetCallback;
import com.littlejerk.mvparch.util.CustomLCEDelegate;
import com.littlejerk.mvparch.util.HttpUtils;

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
    public void doPreBusiness() {
        LoadingHelper loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingHelper();
        loadingHelper.register(ViewType.LOADING, new CLoadingAdapter());
//        loadingHelper.register(ViewType.ERROR, "自定义的错误布局");
//        loadingHelper.register(ViewType.EMPTY, "自定义的空布局");

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
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