package com.huangxiaoliang.mvparchdemo.fragment;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.databinding.FragmentLazyLoadBinding;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingFragment;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 12:42</pre>
 * <pre>@desc 懒加载的Fragment Demo</pre>
 */
public class LazyLoadFragment extends BaseBindingFragment<FragmentLazyLoadBinding> {
    private static final String TAG = "LazyLoadFragment";

    @Override
    public void onLazyLoadData() {
        stateLoadingView();
        HttpUtils.requestNet(this, new NetCallback<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                stateContentView();
                getBinding().tvContent.setText("懒加载：只有页面对用户可见时，才会加载数据，兼容ViewPager、ViewPager2");
                UIToast.showCustomViewShort("懒加载成功");
          }

            @Override
            public void onFailure(String msg) {
                stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public boolean isUseLazyLoad() {
        return true;

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
    }
}
