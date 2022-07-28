package com.huangxiaoliang.mvparchdemo.util;

import android.content.Context;
import android.widget.ImageView;

import com.huangxiaoliang.mvplib.manager.imageloader.GlideLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ImageOptions;

import java.io.File;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/27 18:56</pre>
 * <pre>@desc 自定义图片加载器{@link GlideLoader}</pre>
 */
public class CustomGlideLoader implements IImageLoader {

    private CustomGlideLoader() {
    }

    public static CustomGlideLoader get() {
        return Holder.instance;
    }

    private static class Holder {
        private static final CustomGlideLoader instance = new CustomGlideLoader();
    }

    @Override
    public void loadImage(ImageView target, Object model) {

    }

    @Override
    public <T> void loadImage(ImageView target, Object model, ImageOptions<T> options) {

    }

    @Override
    public void loadNet(ImageView target, String url) {

    }

    @Override
    public <T> void loadNet(ImageView target, String url, ImageOptions<T> options) {

    }

    @Override
    public void loadResource(ImageView target, int resId) {

    }

    @Override
    public <T> void loadResource(ImageView target, int resId, ImageOptions<T> options) {

    }

    @Override
    public void loadAssets(ImageView target, String assetName) {

    }

    @Override
    public <T> void loadAssets(ImageView target, String assetName, ImageOptions<T> options) {

    }

    @Override
    public void loadFile(ImageView target, File file) {

    }

    @Override
    public <T> void loadFile(ImageView target, File file, ImageOptions<T> options) {

    }

    @Override
    public void clearMemoryCache(Context context) {

    }

    @Override
    public void clearDiskCache(Context context) {

    }

    @Override
    public void resume(Context context) {

    }

    @Override
    public void pause(Context context) {

    }
}
