package com.littlejerk.mvparch.activity.testmvp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
 * @Description : 描述
 */
public class TestMvpActivity extends BaseMVPActivity<TestActivityPresenter> implements AContract.MyActivityView {

    private static final String TAG = "TestMvpActivity";

    @BindView(R.id.imageView1)
    ImageView mImageView1;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_test_mvp, new TitleParam("TestMvpActivity")
                .setRightText("完成").setRightTextColor(Color.RED).setRightTextSize(17f)
                .setOnTitleBarListener(new TitleParam.SimpleTitleBarListener() {
                    @Override
                    public void onLeftClick(View view) {
                        finish();
                    }

                    @Override
                    public void onRightClick(View view) {
                        UIToast.showShort("点击完成");
                    }
                }));

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());

        findView(R.id.btn_test, v -> UIToast.showLong("点击Test"));
        UILog.e(TAG, "isVisible：" + isVisible(findView(R.id.btn_test)));

        getP().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}