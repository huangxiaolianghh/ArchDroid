package com.littlejerk.library.manager.toast;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.littlejerk.library.R;

import androidx.annotation.NonNull;

/**
 * @author : HHotHeart
 * @date : 2021/6/8 00:06
 * @desc : Toast的代理类
 */
public class UIToastDelegate implements UIToast.ToastDelegate {

    @Override
    public UIToastDelegate init() {
        ToastUtils.getDefaultMaker()
                .setGravity(Gravity.CENTER, 0, 0)
                .setTextSize(14)
                .setTextColor(Color.WHITE)
                .setBgResource(R.drawable.toast_bg);
        return this;
    }

    @Override
    public void showShort(int resId) {
        ToastUtils.showShort(resId);
    }

    @Override
    public void showShort(CharSequence text) {
        ToastUtils.showShort(text);

    }

    @Override
    public void showShort(int resId, Object... args) {
        ToastUtils.showShort(resId, args);

    }

    @Override
    public void showShort(String format, Object... args) {
        ToastUtils.showShort(format, args);
    }

    @Override
    public void showLong(int resId) {
        ToastUtils.showLong(resId);

    }

    @Override
    public void showLong(CharSequence text) {
        ToastUtils.showLong(text);

    }

    @Override
    public void showLong(int resId, Object... args) {
        ToastUtils.showLong(resId, args);

    }

    @Override
    public void showLong(String format, Object... args) {
        ToastUtils.showLong(format, args);

    }

    @Override
    public void showCustomViewShort(@NonNull CharSequence text) {
        showCustomViewShort(Gravity.CENTER, 0, 0, text);
    }

    @Override
    public void showCustomViewLong(@NonNull CharSequence text) {
        showCustomViewLong(Gravity.CENTER, 0, 0, text);
    }

    @Override
    public void showCustomViewShort(int gravity, @NonNull CharSequence text) {
        showCustomViewShort(gravity, 0, 0, text);
    }

    @Override
    public void showCustomViewLong(int gravity, @NonNull CharSequence text) {
        showCustomViewLong(gravity, 0, 0, text);
    }

    @Override
    public void showCustomViewShort(int gravity, int xOffset, int yOffset, @NonNull CharSequence text) {
        View view = LayoutInflater.from(Utils.getApp()).inflate(R.layout.layout_custom_toast, null);
        TextView tvMsg = view.findViewById(R.id.tvToast);
        tvMsg.setText(text);
        ToastUtils.make()
                .setGravity(gravity, xOffset, yOffset)
                .setDurationIsLong(true)
                .show(view);
    }

    @Override
    public void showCustomViewLong(int gravity, int xOffset, int yOffset, @NonNull CharSequence text) {
        View view = LayoutInflater.from(Utils.getApp()).inflate(R.layout.layout_custom_toast, null);
        TextView tvMsg = view.findViewById(R.id.tvToast);
        tvMsg.setText(text);
        ToastUtils.make()
                .setGravity(gravity, xOffset, yOffset)
                .setDurationIsLong(true)
                .show(view);
    }


    @Override
    public void showCustomShort(int gravity, CharSequence text, int textSize, int textColor, int drawableId) {
        ToastUtils.make()
                .setBgResource(drawableId)
                .setTextColor(textColor)
                .setTextSize(textSize)
                .setGravity(gravity, 0, 0)
                .setDurationIsLong(false)
                .show(text);
    }

    @Override
    public void showCustomLong(int gravity, CharSequence text, int textSize, int textColor, int drawableId) {
        ToastUtils.make()
                .setBgResource(drawableId)
                .setTextColor(textColor)
                .setTextSize(textSize)
                .setGravity(gravity, 0, 0)
                .setDurationIsLong(true)
                .show(text);
    }


}
