package com.huangxiaoliang.mvplib.manager;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.jaeger.library.StatusBarUtil;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;

/**
 * @author HHotHeart
 * @time 2021/6/9 14:06
 * @description 对statusBar相关库二次封装
 */
public class UIStatusBar {

    public static final int STATUS_BAR_DARK_FONT = 10001;
    public static final int STATUS_BAR_WHITE_FONT = 10002;

    public static final int LOW_STATUS_BAR_ALPHA = 0;
    public static final int DEFAULT_STATUS_BAR_ALPHA = 112;
    public static final int HEIGHT_STATUS_BAR_ALPHA = 225;


    /**
     * 设置statusBar模式为亮系（图标为黑色）
     *
     * @param activity
     */
    public static void setLightMode(Activity activity) {
        StatusBarCompat.setLightStatusBar(activity.getWindow(), true);
    }

    /**
     * 设置statusBar模式为暗系（图标为白色）
     *
     * @param activity
     */
    public static void setDarkMode(Activity activity) {
        StatusBarCompat.setLightStatusBar(activity.getWindow(), false);
    }

    /**
     * 设置statusBar背景颜色（不加透明色值）
     *
     * @param activity
     * @param color
     */
    public static void setBgColor(Activity activity, @ColorInt int color) {
        setBgColor(activity, color, 0);
    }

    /**
     * 设置statusBar背景颜色（控制透明色值）
     *
     * @param activity
     * @param color
     * @param statusBarAlpha
     */
    public static void setBgColor(Activity activity, @ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setColor(activity, color, statusBarAlpha);
    }

    /**
     * 使状态栏半透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public static void setTranslucent(Activity activity) {
        StatusBarUtil.setTranslucent(activity);
    }

    /**
     * 使状态栏半透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity       需要设置的activity
     * @param statusBarAlpha 状态栏透明度
     */
    public static void setTranslucent(Activity activity, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setTranslucent(activity, statusBarAlpha);
    }

    /**
     * 针对根布局是 CoordinatorLayout, 使状态栏半透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity       需要设置的activity
     * @param statusBarAlpha 状态栏透明度
     */
    public static void setTranslucentForCoordinatorLayout(Activity activity, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        StatusBarUtil.setTranslucentForCoordinatorLayout(activity, statusBarAlpha);
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏全透明
     *
     * @param activity       需要设置的activity
     * @param needOffsetView 需要向下偏移的 View
     */
    public static void setTransparentForImageView(Activity activity, View needOffsetView) {
        setTranslucentForImageView(activity, 0, needOffsetView);
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏透明(使用默认透明度)
     *
     * @param activity       需要设置的activity
     * @param needOffsetView 需要向下偏移的 View
     */
    public static void setTranslucentForImageView(Activity activity, View needOffsetView) {
        setTranslucentForImageView(activity, DEFAULT_STATUS_BAR_ALPHA, needOffsetView);
    }


    /**
     * 为头部是 ImageView 的界面设置状态栏透明
     *
     * @param activity       需要设置的activity
     * @param statusBarAlpha 状态栏透明度
     * @param needOffsetView 需要向下偏移的 View
     */
    public static void setTranslucentForImageView(Activity activity, @IntRange(from = 0, to = 255) int statusBarAlpha,
                                                  View needOffsetView) {
        StatusBarUtil.setTranslucentForImageView(activity, statusBarAlpha, needOffsetView);
    }


    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        StatusBarUtil.setTransparent(activity);
    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
