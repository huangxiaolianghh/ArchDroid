package com.huangxiaoliang.mvplib.manager.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;

import java.io.File;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/1 16:36
 * @Description : 图片接口
 */
public interface IImageLoader {

    void loadImage(ImageView target, Object object, HOptions options);

    void loadNet(ImageView target, String url, HOptions options);

    void loadNet(Context context, ImageView target, String url, HOptions options, ILoadCallback callback);

    void loadResource(ImageView target, int resId, HOptions options);

    void loadAssets(ImageView target, String assetName, HOptions options);

    void loadFile(ImageView target, File file, HOptions options);

    void loadCircle(ImageView target, String url, HOptions options);

    void loadCorner(ImageView target, String url, int radius, HOptions options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    void resume(Context context);

    void pause(Context context);


    class HOptions {

        public static final int RES_NONE = -1;
        //加载中的资源id
        public int loadingResId = RES_NONE;
        //加载失败的资源id
        public int loadErrorResId = RES_NONE;
        //加载图片的scaleType
        public ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

        //默认图片的Options
        public static HOptions defaultOptions() {
            return new HOptions(MVPArchConfig.IL_LOADING_RES, MVPArchConfig.IL_ERROR_RES);
        }

        public HOptions(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

        public HOptions scaleType(ImageView.ScaleType scaleType) {
            this.scaleType = scaleType;
            return this;
        }
    }


}
