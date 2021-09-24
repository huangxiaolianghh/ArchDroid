package com.littlejerk.mvparch.util;

import com.littlejerk.library.manager.toast.UIToast;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;

/**
 * @author : HHotHeart
 * @date : 2021/9/24 15:03
 * @desc : 自定义Toast代理
 */
public class CustomToastDelegate implements UIToast.ToastDelegate {
    @Override
    public UIToast.ToastDelegate init() {
        //做一些初始化工作
        return this;

    }

    @Override
    public void showShort(int resId) {

    }

    @Override
    public void showShort(CharSequence text) {

    }

    @Override
    public void showShort(int resId, Object... args) {

    }

    @Override
    public void showShort(String format, Object... args) {

    }

    @Override
    public void showLong(int resId) {

    }

    @Override
    public void showLong(CharSequence text) {

    }

    @Override
    public void showLong(int resId, Object... args) {

    }

    @Override
    public void showLong(String format, Object... args) {

    }

    @Override
    public void showCustomViewShort(@NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomViewLong(@NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomViewShort(int gravity, @NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomViewLong(int gravity, @NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomViewShort(int gravity, int xOffset, int yOffset, @NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomViewLong(int gravity, int xOffset, int yOffset, @NonNull @NotNull CharSequence text) {

    }

    @Override
    public void showCustomShort(int gravity, CharSequence text, int textSize, int textColor, int drawableId) {

    }

    @Override
    public void showCustomLong(int gravity, CharSequence text, int textSize, int textColor, int drawableId) {

    }
}
