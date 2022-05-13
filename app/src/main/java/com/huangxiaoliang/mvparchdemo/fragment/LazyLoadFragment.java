package com.huangxiaoliang.mvparchdemo.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseFragment;

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
