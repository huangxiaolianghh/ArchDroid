package com.huangxiaoliang.mvplib.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;
import com.jaeger.library.StatusBarUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/9 14:06</pre>
 * <pre>@desc 对statusBar相关库二次封装</pre>
 */
public class UIStatusBar {

    @IntDef({STATUS_BAR_DARK_FONT, STATUS_BAR_WHITE_FONT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface mode {
    }

    public static final int STATUS_BAR_DARK_FONT = 10001;
    public static final int STATUS_BAR_WHITE_FONT = 10002;

    public static final int LOW_STATUS_BAR_ALPHA = 0;
    public static final int DEFAULT_STATUS_BAR_ALPHA = 112;
    public static final int HEIGHT_STATUS_BAR_ALPHA = 225;

    /**
     * 设置statusBar模式的色系
     *
     * @param activity Activity
     * @param mode     色系
     */
    public static void setStatusBarMode(Activity activity, @mode int mode) {
        StatusBarCompat.setLightStatusBar(activity.getWindow(), mode == STATUS_BAR_DARK_FONT);
    }

    /**
     * 设置statusBar模式为亮系（图标为黑色）
     *
     * @param activity Activity
     */
    public static void setLightMode(Activity activity) {
        StatusBarCompat.setLightStatusBar(activity.getWindow(), true);
    }

    /**
     * 设置statusBar模式为暗系（图标为白色）
     *
     * @param activity Activity
     */
    public static void setDarkMode(Activity activity) {
        StatusBarCompat.setLightStatusBar(activity.getWindow(), false);
    }

    /**
     * 设置statusBar背景颜色（不加透明色值）
     *
     * @param activity Activity
     * @param color    int @ColorInt
     */
    public static void setBgColor(Activity activity, @ColorInt int color) {
        setBgColor(activity, color, 0);
    }

    /**
     * 设置statusBar背景颜色（控制透明色值）
     *
     * @param activity       Activity
     * @param color          int ColorInt
     * @param statusBarAlpha int
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
     * 内容侵入透明状态栏并且兼容有虚拟按键的手机（状态栏图标白色）
     *
     * @param activity activity
     */
    public static void setTransparentWithoutNav(Activity activity) {
        setTransparentWithoutNav(activity, false);
    }

    /**
     * 内容侵入透明状态栏并且兼容有虚拟按键的手机
     *
     * @param activity           activity
     * @param showLightStatusBar 状态栏是否黑色字体、图标等（如果设置了WindowBackground，布局会覆盖导航栏）
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("ObsoleteSdkInt")
    public static void setTransparentWithoutNav(Activity activity, boolean showLightStatusBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        //Android4.4(API 19) - Android 5.0(API 21)
        final Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= flagTranslucentStatus;
            window.setAttributes(attributes);
            return;
        }
        //Android 6.0(API 23)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (showLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        window.setStatusBarColor(Color.TRANSPARENT);
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
