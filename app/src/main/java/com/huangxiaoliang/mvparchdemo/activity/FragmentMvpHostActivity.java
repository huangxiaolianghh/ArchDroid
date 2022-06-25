package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityFmvpHostBinding;
import com.huangxiaoliang.mvparchdemo.fragment.mvp.MVPDemoFragment;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 19:18</pre>
 * <pre>@desc Fragment 宿主Activity</pre>
 */
public class FragmentMvpHostActivity extends BaseBindingActivity<ActivityFmvpHostBinding> {

    @Override
    public String getPageTitle() {
        return "Fragment MVP模式";
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, MVPDemoFragment.newInstance("MVPDemoFragment"));
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