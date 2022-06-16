package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseMVPActivity;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 15:09
 * @Description : Activity MVP例子
 */
public class MvpDemoActivity extends BaseMVPActivity<MvpDemoActivityPresenter> implements AContract.MyActivityView {

    private static final String TAG = "MvpDemoActivity";

    private ActivityTestMvpBinding binding;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        binding = ActivityTestMvpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot(), new TitleParam("Activity MVP模式"));
        binding.btnTest.setText("btnTest Toast");
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(binding.imageView1,
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