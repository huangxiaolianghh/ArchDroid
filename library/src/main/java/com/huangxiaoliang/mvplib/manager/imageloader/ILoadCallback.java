package com.huangxiaoliang.mvplib.manager.imageloader;


/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/7/27 19:54</pre>
 * <pre>@desc 图片加载回调</pre>
 */
public interface ILoadCallback<Type> {

    /**
     * 图片加载失败
     *
     * @param msg 失败信息
     */
    void onLoadFailed(String msg);


    /**
     * 图片加载完成
     *
     * @param type 加载类型
     */
    void onLoadReady(Type type);
}
