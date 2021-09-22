package com.littlejerk.mvparch.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.fragment.mvp.MVPDemoFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 19:18
 * @Description : 描述
 */
public class FragmentMvpHostActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_fmvp_host,"Fragment MVP模式");
    }

    @Override
    public void doPreBusiness() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, new MVPDemoFragment());
        transaction.commit();
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
//        UIStatusBar.setLightMode(this);
//        UIStatusBar.setBgColor(this, getResources().getColor(R.color.white));
//        UIStatusBar.setTransparentForImageView(this,mView);

//        UIStatusBar.setTransparentForImageView(this, findView(R.id.fragment_content));

    }
}