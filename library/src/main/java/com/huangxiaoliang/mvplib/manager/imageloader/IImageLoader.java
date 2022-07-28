package com.huangxiaoliang.mvplib.manager.imageloader;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:36</pre>
 * <pre>@desc 图片加载器接口</pre>
 */
public interface IImageLoader {

    void loadImage(ImageView target, Object model);

    <T> void loadImage(ImageView target, Object model, ImageOptions<T> options);

    void loadNet(ImageView target, String url);

    <T> void loadNet(ImageView target, String url, ImageOptions<T> options);

    void loadResource(ImageView target, int resId);

    <T> void loadResource(ImageView target, int resId, ImageOptions<T> options);

    void loadAssets(ImageView target, String assetName);

    <T> void loadAssets(ImageView target, String assetName, ImageOptions<T> options);

    void loadFile(ImageView target, File file);

    <T> void loadFile(ImageView target, File file, ImageOptions<T> options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void resume(Context context);

    void pause(Context context);

}
