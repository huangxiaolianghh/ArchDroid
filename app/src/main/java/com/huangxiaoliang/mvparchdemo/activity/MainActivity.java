package com.huangxiaoliang.mvparchdemo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.activity.mvp.MvpDemoActivity;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityMainBinding;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:09</pre>
 * <pre>@desc 主页</pre>
 */
public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    @Override
    protected void onBusiness(Bundle savedInstanceState) {

        findView(R.id.btn_mvp_activity, mView -> {
            Intent intent = new Intent(getContext(), MvpDemoActivity.class);
            intent.putExtra("MVP_DATA", "MvpDemoActivity");
            startActivity(intent);
        });
        findView(R.id.btn_mvp_fragment, mView -> startActivity(new Intent(getContext(), FragmentMvpHostActivity.class)));

        findView(R.id.btn_reload_demo, mView -> startActivity(new Intent(getContext(), ReloadDemoActivity.class)));
        findView(R.id.btn_title_demo, mView -> startActivity(new Intent(getContext(), TitleDemoActivity.class)));

        findView(R.id.btn_lazy_load, mView -> startActivity(new Intent(getContext(), LazyLoadActivity.class)));
        findView(R.id.btn_dialog_loading, mView -> startActivity(new Intent(getContext(), LoadingDialogActivity.class)));

        findView(R.id.btn_content_loading, mView -> startActivity(new Intent(getContext(), CustomLCEActivity.class)));


    }
}