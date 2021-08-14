package com.littlejerk.mvparch.fragment;

import android.os.Bundle;

import com.dylanc.loadinghelper.LoadingHelper;
import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseMVPFragment;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.util.CustomLCEDelegate;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/15 17:35
 * @Description : 描述
 */
public class TestMVPFragment extends BaseMVPFragment<TestFragmentPresenter>
        implements FContract.MyFragmentView, LoadingHelper.OnReloadListener {
    private static final String TAG = "TestMVPFragment";

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_mvp);
    }

    @Override
    public void doPreBusiness() {
        super.doPreBusiness();
        LoadingHelper loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingHelper();
        loadingHelper.setOnReloadListener(this::onReload);
        findView(R.id.tv_content, v -> UIToast.showLong("TestMVPFragment内容"));

    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        UILog.d(TAG, TAG + " lazyLoadData");
        getP().loadData();
    }

    @Override
    public boolean useLazyLoad() {
        return true;

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
    }

    @Override
    public void onReload() {
        getP().onReload();
    }

    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}
