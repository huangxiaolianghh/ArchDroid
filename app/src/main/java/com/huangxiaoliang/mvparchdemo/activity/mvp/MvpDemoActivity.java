package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTestMvpBinding;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.imageloader.ILoadCallback;
import com.huangxiaoliang.mvplib.manager.imageloader.ImageLoaderFactory;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingMVPActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:09</pre>
 * <pre>@desc Activity MVP例子</pre>
 */
public class MvpDemoActivity
        extends BaseBindingMVPActivity<MvpDemoActivityPresenter, ActivityTestMvpBinding>
        implements AContract.MyActivityView {

    private static final String TAG = "MvpDemoActivity";

    @Override
    public String getPageTitle() {
        return "Activity MVP模式";
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        getBinding().btnTest.setText("btnTest Toast");
        ImageLoaderFactory.get().loadNet(getBinding().imageView1, HttpUtils.IMG);
        findView(R.id.btn_test, v -> UIToast.showLong("测试Toast"));
        UILog.e(TAG, "isVisible：" + isVisible(R.id.btn_test));
        getMvpPresenter().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}