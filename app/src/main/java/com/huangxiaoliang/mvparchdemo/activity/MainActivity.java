package com.huangxiaoliang.mvparchdemo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvplib.mvp.BaseActivity;
import com.huangxiaoliang.mvparchdemo.activity.mvp.MvpDemoActivity;

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
    protected void onBusiness(Bundle savedInstanceState) {

        findView(R.id.btn_mvp_activity, mView -> startActivity(new Intent(getContext(), MvpDemoActivity.class)));
        findView(R.id.btn_mvp_fragment, mView -> startActivity(new Intent(getContext(), FragmentMvpHostActivity.class)));

        findView(R.id.btn_reload_demo, mView -> startActivity(new Intent(getContext(), ReloadDemoActivity.class)));
        findView(R.id.btn_title_demo, mView -> startActivity(new Intent(getContext(), TitleDemoActivity.class)));

        findView(R.id.btn_lazy_load, mView -> startActivity(new Intent(getContext(), LazyLoadActivity.class)));
        findView(R.id.btn_dialog_loading, mView -> startActivity(new Intent(getContext(), LoadingDialogActivity.class)));

        findView(R.id.btn_content_loading, mView -> startActivity(new Intent(getContext(), CustomLCEActivity.class)));


    }
}