package com.littlejerk.mvparch.util;

import android.content.Context;
import android.widget.ImageView;

import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILoadCallback;

import java.io.File;

/**
 * @author : HHotHeart
 * @date : 2021/9/27 18:56
 * @desc : 自定义图片加载器{@link com.littlejerk.library.manager.imageloader.GlideLoader}
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
    public void loadImage(ImageView target, Object object, HOptions options) {

    }

    @Override
    public void loadNet(ImageView target, String url, HOptions options) {

    }

    @Override
    public void loadNet(Context context, ImageView target, String url, HOptions options, ILoadCallback callback) {

    }

    @Override
    public void loadResource(ImageView target, int resId, HOptions options) {

    }

    @Override
    public void loadAssets(ImageView target, String assetName, HOptions options) {

    }

    @Override
    public void loadFile(ImageView target, File file, HOptions options) {

    }

    @Override
    public void loadCircle(ImageView target, String url, HOptions options) {

    }

    @Override
    public void loadCorner(ImageView target, String url, int radius, HOptions options) {

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
