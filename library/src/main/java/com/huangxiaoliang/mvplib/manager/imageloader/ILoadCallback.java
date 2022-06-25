package com.huangxiaoliang.mvplib.manager.imageloader;

import android.graphics.drawable.Drawable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:38</pre>
 * <pre>@desc 图片加载状态接口</pre>
 */
public abstract class ILoadCallback {
    void onLoadFailed(String msg) {
    }

    void onLoadCanceled() {
    }

    public abstract void onLoadReady(Drawable drawable);
}