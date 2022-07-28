package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityLoadingDialogBinding;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.lcet.ILoadingPopupView;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/21 23:32</pre>
 * <pre>@desc 加载Dialog样式Activity Demo</pre>
 */
public class LoadingPopupActivity extends BaseBindingActivity<ActivityLoadingDialogBinding> {

    /**
     * App内的弹窗样式通过{@link com.huangxiaoliang.mvparchdemo.util.CustomLoadingPopupDelegate}实现<br/>
     * 页面的弹窗样式可以重写{@link ILoadingPopupView}接口所有方法
     */

    @Override
    public String getPageTitle() {
        return "弹窗加载样式";
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        testLoadData();
        findView(R.id.btn_show_dialog, view -> {
            testLoadData();
        });
    }

    private void testLoadData() {
        showLoadingPopup("加载中");
        HttpUtils.requestNet(this, new NetCallback<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                dismissLoadingPopup();
            }

            @Override
            public void onFailure(String msg) {
                dismissLoadingPopup();
                UIToast.showShort(msg);
            }
        });
    }

}