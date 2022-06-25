package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingMVPActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:09</pre>
 * <pre>@desc Activity MVP例子</pre>
 */
public class MvpDemoActivity extends BaseBindingMVPActivity<MvpDemoActivityPresenter, ActivityTestMvpBinding>
        implements AContract.MyActivityView {

    private static final String TAG = "MvpDemoActivity";

    @Override
    public String getPageTitle() {
        return "Activity MVP模式";
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        getBinding().btnTest.setText("btnTest Toast");
        ILFactory.getLoader().loadNet(getBinding().imageView1,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-06-29%2F5ef9b315417b8.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657890578&t=11177abaff83a7971b98f5a40b97d1b2",
                IImageLoader.HOptions.defaultOptions());

        findView(R.id.btn_test, v -> {
                    UIToast.showLong("测试Toast");
                }
        );
        UILog.e(TAG, "isVisible：" + isVisible(R.id.btn_test));

        getMvpPresenter().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}