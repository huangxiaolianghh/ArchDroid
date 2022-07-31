package com.huangxiaoliang.mvplib.manager.imageloader;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/7/26 21:44</pre>
 * <pre>@desc 图片配置参数</pre>
 */
public class ImageOptions<T> {

    /**
     * 未设置资源ID
     */
    public static final int RES_NONE = -1;

    /**
     * 加载中的资源id
     */
    @DrawableRes
    private int placeholderResId = RES_NONE;

    /**
     * 加载失败的资源id
     */
    @DrawableRes
    private int errorResId = RES_NONE;

    /**
     * 加载图片的scaleType
     */
    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

    /**
     * 圆角，单位px（会被圆形覆盖）
     */
    private int radius;

    /**
     * 圆形（覆盖圆角的设置）
     */
    private boolean isCircle;


    /**
     * 图片加载监听回调
     */
    private ILoadCallback<T> loadCallback;

    /**
     * 构造函数
     */
    public ImageOptions() {
    }

    /**
     * 构造函数
     *
     * @param placeholderResId 占位图资源id
     * @param errorResId       错误图资源id
     */
    public ImageOptions(@DrawableRes int placeholderResId, @DrawableRes int errorResId) {
        this.placeholderResId = placeholderResId;
        this.errorResId = errorResId;
    }

    /**
     * 构造函数
     *
     * @param placeholderResId 占位图资源id
     * @param errorResId       错误图资源id
     * @param scaleType        图片伸缩类型
     * @param radius           圆角
     * @param isCircle         圆形
     * @param loadCallback     图片加载回调
     */
    public ImageOptions(
            @DrawableRes int placeholderResId,
            @DrawableRes int errorResId,
            ImageView.ScaleType scaleType,
            int radius,
            boolean isCircle,
            ILoadCallback<T> loadCallback
    ) {
        this.placeholderResId = placeholderResId;
        this.errorResId = errorResId;
        this.scaleType = scaleType;
        this.radius = radius;
        this.isCircle = isCircle;
        this.loadCallback = loadCallback;
    }

    /**
     * 获取占位图
     *
     * @return 占位图id
     */
    public int getPlaceholderResId() {
        return placeholderResId;
    }

    /**
     * 获取错误图
     *
     * @return 错误图id
     */
    public int getErrorResId() {
        return errorResId;
    }

    /**
     * 获取图片伸缩类型
     *
     * @return 图片伸缩类型ScaleType
     */
    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    /**
     * 设置图片伸缩类型
     *
     * @param scaleType 图片伸缩类型
     * @return ImageOptions<T>
     */
    public ImageOptions<T> setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    /**
     * 获取圆角大小
     *
     * @return 圆角大小
     */
    public int getRadius() {
        return radius;
    }

    /**
     * 设置圆角大小
     *
     * @param radius 圆角大小
     * @return ImageOptions<T>
     */
    public ImageOptions<T> setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 获取圆形标识
     *
     * @return 是否设置圆形
     */
    public boolean isCircle() {
        return isCircle;
    }

    /**
     * 设置圆形标识
     *
     * @param circle 设置圆形
     * @return ImageOptions<T>
     */
    public ImageOptions<T> setCircle(boolean circle) {
        isCircle = circle;
        return this;
    }

    /**
     * 获取图片加载回调监听
     *
     * @return ILoadCallback<T>
     */
    public ILoadCallback<T> getLoadCallback() {
        return loadCallback;
    }

    /**
     * 设置图片加载回调监听
     *
     * @param loadCallback 图片加载回调监听
     * @return ImageOptions<T>
     */
    public ImageOptions<T> setLoadCallback(ILoadCallback<T> loadCallback) {
        this.loadCallback = loadCallback;
        return this;
    }

}