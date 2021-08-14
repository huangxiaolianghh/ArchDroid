package com.littlejerk.mvparch.activity;

import android.os.Bundle;

import com.littlejerk.library.manager.UIStatusBar;
import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.manager.lcet.TitleParam;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 15:42
 * @Description : 描述
 */
public class TestNormalActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_test_narmal);
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        //        UIStatusBar.setBgColor(this, getResources().getColor(R.color.white));

        UIStatusBar.setTransparentForImageView(this, findViewById(R.id.ll_content));
        ILFactory.getLoader().loadNet(findViewById(R.id.imageView1),
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());
    }
}