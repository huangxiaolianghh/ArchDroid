package com.littlejerk.mvparch.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.manager.lcet.TitleParam;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 15:42
 * @Description : 标题属性Demo
 */
public class TitleDemoActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_title_demo, new TitleParam("Title Demo")
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
//        UIStatusBar.setTransparentForImageView(this, findViewById(R.id.ll_content));
        ILFactory.getLoader().loadNet(findViewById(R.id.imageView1),
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());
    }
}