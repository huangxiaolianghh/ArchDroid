package com.huangxiaoliang.mvplib.manager.imageloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

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
import com.huangxiaoliang.mvplib.util.CommonUtils;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/1 16:36
 * @Description : 图片加载器实现类
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
     * @param context
     * @param <T>
     * @return
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
        //不得已，不可用application作为context
        if (requestManager == null) {
            requestManager = Glide.with(Utils.getApp());
        }
        return requestManager;
    }

    /**
     * 获取RequestOptions
     *
     * @param options
     * @return
     */
    @SuppressLint("CheckResult")
    private RequestOptions getRequestOptions(HOptions options) {
        RequestOptions request = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .priority(Priority.HIGH)
                .transform(wrapTransformation(options, null));
        if (options != null) {
            if (options.loadingResId != HOptions.RES_NONE) {
                request.placeholder(options.loadingResId);
            }
            if (options.loadErrorResId != HOptions.RES_NONE) {
                request.error(options.loadErrorResId);
            }
        }
        return request;
    }


    /**
     * 获取Transformation
     *
     * @return
     */

    private Transformation<Bitmap> wrapTransformation(HOptions options, Transformation<Bitmap> transformations) {
        BitmapTransformation scaleFormation = null;
        if (options != null) {
            if (options.scaleType != null) {
                switch (options.scaleType) {
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
    public void loadImage(ImageView target, Object object, HOptions options) {
        getRequestManager(target)
                .load(object)
                .apply(getRequestOptions(options))
//                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(target);

    }

    @Override
    public void loadNet(ImageView target, String url, HOptions options) {
        loadImage(target, url, options);
    }

    @Override
    public void loadNet(Context context, ImageView target, String url, HOptions options, ILoadCallback callback) {
        getRequestManager(context)
                .load(url)
                .apply(getRequestOptions(options))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e,
                                                Object model,
                                                Target<Drawable> target,
                                                boolean isFirstResource) {
                        if (callback != null) {
                            callback.onLoadFailed(e == null ? "图片加载失败" : e.getMessage());
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource,
                                                   Object model,
                                                   Target<Drawable> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {
                        if (callback != null) {
                            callback.onLoadReady(resource);
                        }
                        return false;
                    }
                })
                .into(target);
    }

    @Override
    public void loadResource(ImageView target, int resId, HOptions options) {
        getRequestManager(target)
                .load(resId)
                .apply(getRequestOptions(options))
                .into(target);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, HOptions options) {
        Context context = CommonUtils.getActivityFromView(target);
        if (context == null) {
            context = Utils.getApp();
        }
        getRequestManager(target)
                .load(CommonUtils.getBitmapFromAssert(context, assetName))
                .apply(getRequestOptions(options))
                .into(target);

    }

    @Override
    public void loadFile(ImageView target, File file, HOptions options) {
        getRequestManager(target)
                .load(file)
                .apply(getRequestOptions(options))
                .into(target);
    }

    @Override
    public void loadCircle(ImageView target, String url, HOptions options) {
        getRequestManager(target)
                .load(url)
                .apply(getRequestOptions(options))
                .transform(wrapTransformation(options, new CircleCrop()))
                .into(target);

    }

    @Override
    public void loadCorner(ImageView target, String url, int radius, HOptions options) {
        getRequestManager(target)
                .load(url)
                .apply(getRequestOptions(options))
                .transform(wrapTransformation(options, new RoundedCorners(CommonUtils.dp2px(radius))))
                .into(target);
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
