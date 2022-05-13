package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.fragment.mvp.MVPDemoFragment;
import com.huangxiaoliang.mvplib.mvp.BaseActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 19:18
 * @Description : 描述
 */
public class FragmentMvpHostActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_fmvp_host, "Fragment MVP模式");
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, new MVPDemoFragment());
        transaction.commit();
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
//        UIStatusBar.setLightMode(this);
//        UIStatusBar.setBgColor(this, getResources().getColor(R.color.white));
//        UIStatusBar.setTransparentForImageView(this,mView);

//        UIStatusBar.setTransparentForImageView(this, findView(R.id.fragment_content));

    }
}