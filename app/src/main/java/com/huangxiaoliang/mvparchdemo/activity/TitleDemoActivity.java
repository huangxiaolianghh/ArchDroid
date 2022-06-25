package com.huangxiaoliang.mvparchdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTitleDemoBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:42</pre>
 * <pre>@desc 标题属性Demo</pre>
 */
public class TitleDemoActivity extends BaseBindingActivity<ActivityTitleDemoBinding> {

    @Override
    public ITitleView getPageTitleView() {
        return new TitleParam("Title Demo")
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
                });
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(findViewById(R.id.imageView1),
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-06-29%2F5ef9b315417b8.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657890578&t=11177abaff83a7971b98f5a40b97d1b2",
                IImageLoader.HOptions.defaultOptions());
    }
}