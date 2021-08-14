package com.littlejerk.mvparch.activity;

import android.content.Intent;
import android.os.Bundle;

import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.activity.testmvp.TestMvpActivity;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/8/14 15:09
 * @Description : 描述
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {

        findView(R.id.rtv_test_mvp, mView -> startActivity(new Intent(getContext(), TestMvpActivity.class)));
        findView(R.id.rtv_test_host, mView -> startActivity(new Intent(getContext(), TestHostActivity.class)));
        findView(R.id.rtv_test_normal, mView -> startActivity(new Intent(getContext(), TestNormalActivity.class)));
    }
}