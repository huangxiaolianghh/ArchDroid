package com.huangxiaoliang.mvplib.manager.imageloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.huangxiaoliang.mvplib.util.ClassUtils;
import com.huangxiaoliang.mvplib.util.MvpArchUtils;

import java.io.File;
import java.util.Objects;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:36</pre>
 * <pre>@desc 图片加载器实现类</pre>
 */
public final class GlideLoader implements IImageLoader {

    private GlideLoader() {
    }

    protected static GlideLoader get() {
        return Holder.instance;
    }

    private static class Holder {
        private static final GlideLoader instance = new GlideLoader();
    }

    /**
     * 获取加载图片的RequestManager
     *
     * @param context 上下文
     * @param <T>     泛型
     * @return RequestManager
     */
    private <T> RequestManager getRequestManager(T context) {
        RequestManager requestManager = null;
        if (context instanceof Fragment) {
            requestManager = Glide.with((Fragment) context);
        } else if (context instanceof Activity) {
            requestManager = Glide.with((Activity) context);
        } else if (context instanceof Context) {
            requestManager = Glide.with((Context) context);
        } else if (context instanceof View) {
            requestManager = Glide.with((View) context);
        }
        if (requestManager == null) {
            requestManager = Glide.with(Utils.getApp());
        }
        return requestManager;
    }

    /**
     * 获取RequestOptions
     *
     * @param options ImageOptions<?>
     * @return RequestOptions
     */
    @SuppressLint("CheckResult")
    private RequestOptions getRequestOptions(ImageOptions<?> options) {
        Transformation<Bitmap> transformations = null;
        RequestOptions request = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .priority(Priority.HIGH);
        if (options != null) {
            if (options.getPlaceholderResId() != ImageOptions.RES_NONE) {
                request.placeholder(options.getPlaceholderResId());
            }
            if (options.getErrorResId() != ImageOptions.RES_NONE) {
                request.error(options.getErrorResId());
            }
            if (options.isCircle()) {
                transformations = new CircleCrop();
            } else if (options.getRadius() > 0) {
                transformations = new RoundedCorners(options.getRadius());
            }
        }
        request.transform(wrapTransformation(options, transformations));
        return request;
    }

    /**
     * 获取Transformation
     *
     * @return Transformation<Bitmap>
     */
    private Transformation<Bitmap> wrapTransformation(ImageOptions<?> options, Transformation<Bitmap> transformations) {
        BitmapTransformation scaleFormation = null;
        if (options != null) {
            if (options.getScaleType() != null) {
                switch (options.getScaleType()) {
                    case FIT_XY:
                    case CENTER_INSIDE:
                        scaleFormation = new CenterInside();
                        break;
                    case MATRIX:
                    case CENTER:
                    case FIT_START:
                    case FIT_END:
                    case FIT_CENTER:
                        scaleFormation = new FitCenter();
                        break;
                    case CENTER_CROP:
                        scaleFormation = new CenterCrop();
                        break;
                    default:
                }
            } else {
                scaleFormation = new CenterCrop();
            }

        } else {
            scaleFormation = new CenterCrop();
        }
        //只有一个Transformation
        if (transformations == null) {
            return scaleFormation;
        }
        //防止覆盖(参数不可为空，不然加载不出来)
        return new MultiTransformation<>(scaleFormation, transformations);
    }

    @Override
    public void loadImage(ImageView target, Object model) {
        loadImage(target, model, ImageLoaderFactory.asDefault());
    }

    @Override
    public <T> void loadImage(ImageView target, Object model, ImageOptions<T> options) {
        getRequestManager(target)
                .as(Objects.requireNonNull(ClassUtils.<T>getSuperClassGenericTypeClass(options)))
                .load(model)
                .apply(getRequestOptions(options))
                .listener(new RequestListener<T>() {
                    @Override
                    public boolean onLoadFailed(
                            @Nullable GlideException e,
                            Object model,
                            Target<T> target,
                            boolean isFirstResource
                    ) {
                        if (options.getLoadCallback() != null) {
                            options.getLoadCallback().onLoadFailed(e == null ? "图片加载失败" : e.getMessage());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(
                            T resource,
                            Object model,
                            Target<T> target,
                            DataSource dataSource,
                            boolean isFirstResource
                    ) {
                        if (options.getLoadCallback() != null) {
                            options.getLoadCallback().onLoadReady(resource);
                        }
                        return false;
                    }

                }).into(target);
    }

    @Override
    public void loadNet(ImageView target, String url) {
        loadNet(target, url, ImageLoaderFactory.asDefault());
    }

    @Override
    public <T> void loadNet(ImageView target, String url, ImageOptions<T> options) {
        loadImage(target, url, options);
    }

    @Override
    public void loadResource(ImageView target, int resId) {
        loadResource(target, resId, ImageLoaderFactory.asDefault());
    }

    @Override
    public <T> void loadResource(ImageView target, int resId, ImageOptions<T> options) {
        loadImage(target, resId, options);
    }

    @Override
    public void loadAssets(ImageView target, String assetName) {
        loadAssets(target, assetName, ImageLoaderFactory.asDefault());
    }

    @Override
    public <T> void loadAssets(ImageView target, String assetName, ImageOptions<T> options) {
        Context context = MvpArchUtils.getActivityFromView(target);
        if (context == null) {
            context = Utils.getApp();
        }
        loadImage(target, MvpArchUtils.getBitmapFromAssert(context, assetName), options);
    }

    @Override
    public void loadFile(ImageView target, File file) {
        loadFile(target, file, ImageLoaderFactory.asDefault());
    }

    @Override
    public <T> void loadFile(ImageView target, File file, ImageOptions<T> options) {
        loadImage(target, file, options);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        ThreadUtils.getCachedPool().execute(() -> Glide.get(context).clearDiskCache());
    }

    @Override
    public void resume(Context context) {
        getRequestManager(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        getRequestManager(context).pauseAllRequests();
    }
}
