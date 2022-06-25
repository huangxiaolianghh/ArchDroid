package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityLazyLoadBinding;
import com.huangxiaoliang.mvparchdemo.fragment.LazyLoadFragment;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

import androidx.fragment.app.FragmentTransaction;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/21 23:21</pre>
 * <pre>@desc 懒加载Fragment 宿主Activity</pre>
 */
public class LazyLoadActivity extends BaseBindingActivity<ActivityLazyLoadBinding> {


    @Override
    public String getPageTitle() {
        return "懒加载Fragment Demo";
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_content, new LazyLoadFragment());
        transaction.commit();
    }

}