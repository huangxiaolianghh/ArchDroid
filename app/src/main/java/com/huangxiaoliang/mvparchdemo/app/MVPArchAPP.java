package com.huangxiaoliang.mvparchdemo.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvparchdemo.BuildConfig;
import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.util.CustomLogDelegate;
import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.huangxiaoliang.mvplib.manager.lcet.GEmptyViewDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.GErrorViewDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.GLoadingViewDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 19:02</pre>
 * <pre>@desc Application</pre>
 */
public class MVPArchAPP extends Application {

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });

//        //设置代理对象
//        UILog.setDelegate(new UILogDelegate().init());
//        UIToast.setDelegate(new UIToastDelegate());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MVPArchConfig.getInstance()
                .setLogDelegate(new CustomLogDelegate().init())
                .setLoggable(BuildConfig.DEBUG)
                .setLightStatusBar(false)
                .setStatusBarColor(Color.BLACK)
                .setTitleParam(new TitleParam()
                        .setLeftIcon(R.drawable.ic_mvparch_arrow_back_black)
                        .setMiddleTextSize(17f)
                        .setMiddleTextColor(Color.BLACK)
                        .setTitleBarHeight(R.dimen.title_bar_height1)
                        .setTittleBarBgColor(Color.WHITE)
                        .setRightIconVisible(false)
                        .setBottomLineColor(Color.LTGRAY)
                        .setBottomLineHeight(0.5f));

        LoadingStateView.setViewDelegatePool(pool ->
                pool.register(new GLoadingViewDelegate(), new GErrorViewDelegate(), new GEmptyViewDelegate()));

//        UIToast.setDelegate(new CustomToastDelegate().init());
//        MVPArchConfig.getInstance().setImageLoader(CustomGlideLoader.get());
//        MVPArchConfig.getInstance().setEventBus(CustomEventBusImpl.get());

        //设置全局sp文件名
        SPStaticUtils.setDefaultSPUtils(SPUtils.getInstance(MVPArchConfig.SP_TAG));

    }
}
