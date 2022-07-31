package com.huangxiaoliang.mvplib.manager.lcet;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/8 17:53</pre>
 * <pre>@desc 数据加载UI相关接口</pre>
 */
public interface ILCEView extends ILoadingPopupView {

    /**
     * 空视图
     */
    void stateEmptyView();

    /**
     * 错误视图
     */
    void stateErrorView();

    /**
     * 加载中视图
     */
    void stateLoadingView();

    /**
     * 内容视图
     */
    void stateContentView();

}
