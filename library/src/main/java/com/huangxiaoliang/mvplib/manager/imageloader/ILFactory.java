package com.huangxiaoliang.mvplib.manager.imageloader;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;

/**
 * @author HHotHeart
 * @time 2021/6/1 16:37
 * @description 图片加载工厂单例
 */
public class ILFactory {

    private static IImageLoader sLoader;

    public static IImageLoader getLoader() {
        if (sLoader == null) {
            synchronized (ILFactory.class) {
                if (sLoader == null) {
                    if (MVPArchConfig.getInstance().getImageLoader() == null) {
                        //框架实现的Loader
                        sLoader = GlideLoader.get();
                    } else {
                        //调用者实现的Loader
                        sLoader = MVPArchConfig.getInstance().getImageLoader();
                    }
                }
            }
        }
        return sLoader;
    }

}
