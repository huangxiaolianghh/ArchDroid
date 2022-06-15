package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.os.Bundle;
import android.widget.ImageView;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseMVPActivity;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 15:09
 * @Description : Activity MVP例子
 */
public class MvpDemoActivity extends BaseMVPActivity<MvpDemoActivityPresenter> implements AContract.MyActivityView {

    private static final String TAG = "MvpDemoActivity";

    @BindView(R.id.imageView1)
    ImageView mImageView1;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        ActivityTestMvpBinding binding = ActivityTestMvpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot(), new TitleParam("Activity MVP模式"));
        binding.btnTest.setText("btnTest Toast");
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());

        findView(R.id.btn_test, v -> UIToast.showLong("测试Toast"));
        UILog.e(TAG, "isVisible：" + isVisible(R.id.btn_test));

        getMvpPresenter().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}