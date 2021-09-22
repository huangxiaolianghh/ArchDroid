package com.littlejerk.mvparch.activity.mvp;

import android.os.Bundle;
import android.widget.ImageView;

import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.manager.lcet.TitleParam;
import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseMVPActivity;
import com.littlejerk.mvparch.R;

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
        setContentView(R.layout.activity_test_mvp, new TitleParam("TestMvpActivity"));
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());

        findView(R.id.btn_test, v -> UIToast.showLong("测试Toast"));
        UILog.e(TAG, "isVisible：" + isVisible(findView(R.id.btn_test)));

        getP().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}