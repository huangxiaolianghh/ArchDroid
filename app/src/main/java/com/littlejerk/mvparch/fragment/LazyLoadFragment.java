package com.littlejerk.mvparch.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseFragment;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.listener.NetCallback;
import com.littlejerk.mvparch.util.HttpUtils;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/7 12:42
 * @Description : 懒加载的Fragment Demo
 */
public class LazyLoadFragment extends BaseFragment {
    private static final String TAG = "TestFragment";

    @BindView(R.id.ll_content)
    LinearLayout mLlContent;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_lazy_load);
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        UILog.d(TAG, TAG + " lazyLoadData");
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

    @Override
    public boolean useLazyLoad() {
        return true;

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
    }
}
