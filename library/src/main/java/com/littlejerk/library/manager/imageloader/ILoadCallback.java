package com.littlejerk.library.manager.imageloader;

import android.graphics.drawable.Drawable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/1 16:38
 * @Description : 描述
 */
public abstract class ILoadCallback {
    void onLoadFailed(String msg) {
    }

    void onLoadCanceled() {
    }

    public abstract void onLoadReady(Drawable drawable);
}