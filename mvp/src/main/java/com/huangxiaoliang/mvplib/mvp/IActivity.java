package com.huangxiaoliang.mvplib.mvp;

import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 00:03</pre>
 * <pre>@desc Activity需要继承的接口</pre>
 */
public interface IActivity extends IView {

    /**
     * 是否装饰状态栏
     *
     * @return 是否装饰状态栏
     */
    boolean isDecorateStatusBar();

    /**
     * 拦截返回按键
     *
     * @return true 拦截 ，false 不拦截
     */
    boolean onInterceptBackPressed();

    /**
     * 获取标题
     *
     * @return String
     */
    String getPageTitle();

    /**
     * 获取标题属性对象
     *
     * @return ITitleView
     */
    ITitleView getPageTitleView();

}
