package com.littlejerk.mvparch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.littlejerk.library.manager.UIStatusBar;
import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.fragment.TestMVPFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 19:18
 * @Description : 描述
 */
public class TestHostActivity extends BaseActivity {

    @BindView(R.id.imageView1)
    ImageView mImageView1;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_test_host);
    }

    @Override
    public void doPreBusiness() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, new TestMVPFragment());
        transaction.commit();
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
//        UIStatusBar.setLightMode(this);
//        UIStatusBar.setBgColor(this, getResources().getColor(R.color.white));
//        UIStatusBar.setTransparentForImageView(this,mView);

        UIStatusBar.setTransparentForImageView(this, findView(R.id.fragment_content));
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());
    }
}