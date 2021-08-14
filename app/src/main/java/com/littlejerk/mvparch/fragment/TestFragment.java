package com.littlejerk.mvparch.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.dylanc.loadinghelper.LoadingHelper;
import com.littlejerk.library.manager.lcet.LCEDelegate;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseFragment;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.util.HttpUtils;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/7 12:42
 * @Description : 描述
 */
public class TestFragment extends BaseFragment implements LoadingHelper.OnReloadListener {
    private static final String TAG = "TestFragment";

    @BindView(R.id.ll_content)
    LinearLayout mLlContent;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);
    }

    @Override
    public void doPreBusiness() {
        super.doPreBusiness();
//        mLoadingHelper.register(ViewType.LOADING,new LoadingAdapter());
        LoadingHelper loadingHelper = ((LCEDelegate) getLCEDelegate()).getLoadingHelper();
        loadingHelper.setOnReloadListener(this::onReload);
        findView(R.id.tv_test, v -> UIToast.showLong("TestFragment测试哈哈哈哈"));

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        loadingDialogShow();
        stateLoadingView();
        HttpUtils.requestSuccess(new HttpUtils.Callback() {
            @Override
            public void onSuccess() {
                stateErrorView();
                loadingDialogDismiss();

            }

            @Override
            public void onFailure() {
                stateErrorView();
                loadingDialogDismiss();

            }
        });

    }

    @Override
    public void onReload() {
        loadingDialogShow();
        stateLoadingView();
        HttpUtils.requestSuccess(new HttpUtils.Callback() {
            @Override
            public void onSuccess() {
                stateContentView();
                loadingDialogDismiss();
            }

            @Override
            public void onFailure() {
                stateErrorView();
                loadingDialogDismiss();
            }
        });
    }
}
