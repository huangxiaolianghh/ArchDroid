package com.huangxiaoliang.mvplib.manager.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;

import java.io.File;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:37</pre>
 * <pre>@desc 图片加载工厂单例</pre>
 */
public final class ImageLoaderFactory {

    /**
     * 图片加载器
     */
    private static IImageLoader sLoader;

    private ImageLoaderFactory() {
    }

    /**
     * 获取图片加载器，如果框架设置了图片加载器，优先取{@link MVPArchConfig}的
     *
     * @return IImageLoader
     */
    public static IImageLoader get() {
        if (sLoader == null) {
            synchronized (ImageLoaderFactory.class) {
                if (sLoader == null) {
                    sLoader = MVPArchConfig.get().getImageLoader() == null ?
                            GlideLoader.get() :
                            MVPArchConfig.get().getImageLoader();
                }
            }
        }
        return sLoader;
    }

    /**
     * 默认为Drawable类型配置
     *
     * @return ImageOptions<Drawable>
     */
    public static ImageOptions<Drawable> asDefault() {
        return asDrawable();
    }

    /**
     * Drawable类型配置
     *
     * @return ImageOptions<Drawable>
     */
    public static ImageOptions<Drawable> asDrawable() {
        return new DrawableOptions();
    }

    /**
     * Bitmap类型配置
     *
     * @return ImageOptions<Bitmap>
     */
    public static ImageOptions<Bitmap> asBitmap() {
        return new BitmapOptions();
    }

    /**
     * File类型配置
     *
     * @return ImageOptions<File>
     */
    public static ImageOptions<File> asFile() {
        return new FileOptions();
    }

}
