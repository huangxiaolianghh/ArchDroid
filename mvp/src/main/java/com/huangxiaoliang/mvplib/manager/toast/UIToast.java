package com.huangxiaoliang.mvplib.manager.toast;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 23:29</pre>
 * <pre>@desc Toast管理类（默认）</pre>
 */
public class UIToast {

    public static void init() {
        if (sDelegate != null) {
            sDelegate.init();
        }
    }

    public static void showShort(@NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showShort(text);
        }
    }

    public static void showShort(String format, Object... args) {
        if (sDelegate != null) {
            sDelegate.showShort(format, args);
        }
    }

    public static void showShort(int resId) {
        if (sDelegate != null) {
            sDelegate.showShort(resId);
        }
    }

    public static void showShort(int resId, Object... args) {
        if (sDelegate != null) {
            sDelegate.showShort(resId, args);
        }
    }

    public static void showLong(@NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showLong(text);
        }
    }

    public static void showLong(String format, Object... args) {
        if (sDelegate != null) {
            sDelegate.showLong(format, args);
        }
    }

    public static void showLong(int resId) {
        if (sDelegate != null) {
            sDelegate.showLong(resId);
        }
    }

    public static void showLong(int resId, Object... args) {
        if (sDelegate != null) {
            sDelegate.showLong(resId, args);
        }
    }

    public static void showCustomViewShort(@NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewShort(text);
        }
    }

    public static void showCustomViewLong(@NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewLong(text);
        }
    }

    public static void showCustomViewShort(int gravity, @NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewShort(gravity, text);
        }
    }

    public static void showCustomViewLong(int gravity, @NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewLong(gravity, text);
        }
    }

    public static void showCustomViewShort(int gravity, int xOffset, int yOffset, @NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewShort(gravity, xOffset, yOffset, text);
        }
    }

    public static void showCustomViewLong(int gravity, int xOffset, int yOffset, @NonNull CharSequence text) {
        if (sDelegate != null) {
            sDelegate.showCustomViewLong(gravity, xOffset, yOffset, text);
        }
    }

    public static void showCustomShort(int gravity,
                                       @NonNull CharSequence text,
                                       int textSize,
                                       @ColorInt int textColor,
                                       @DrawableRes int drawableId) {
        if (sDelegate != null) {
            sDelegate.showCustomShort(gravity, text, textSize, textColor, drawableId);
        }
    }

    public static void showCustomLong(int gravity,
                                      @NonNull CharSequence text,
                                      int textSize,
                                      @ColorInt int textColor,
                                      @DrawableRes int drawableId) {
        if (sDelegate != null) {
            sDelegate.showCustomLong(gravity, text, textSize, textColor, drawableId);
        }
    }

    private static ToastDelegate sDelegate = new UIToastDelegate().init();

    public static ToastDelegate getDelegate() {
        return sDelegate;
    }

    public static void setDelegate(UIToast.ToastDelegate delegate) {
        sDelegate = delegate;
    }

    public interface ToastDelegate {

        ToastDelegate init();

        void showShort(@StringRes int resId);

        void showShort(CharSequence text);

        void showShort(@StringRes int resId, Object... args);

        void showShort(String format, Object... args);

        void showLong(@StringRes int resId);

        void showLong(CharSequence text);

        void showLong(@StringRes int resId, Object... args);

        void showLong(String format, Object... args);

        void showCustomViewShort(@NonNull CharSequence text);

        void showCustomViewLong(@NonNull CharSequence text);

        void showCustomViewShort(int gravity, @NonNull CharSequence text);

        void showCustomViewLong(int gravity, @NonNull CharSequence text);

        void showCustomViewShort(int gravity, int xOffset, int yOffset, @NonNull CharSequence text);

        void showCustomViewLong(int gravity, int xOffset, int yOffset, @NonNull CharSequence text);

        void showCustomShort(int gravity, CharSequence text, int textSize, @ColorInt int textColor, @DrawableRes int drawableId);

        void showCustomLong(int gravity, CharSequence text, int textSize, @ColorInt int textColor, @DrawableRes int drawableId);

    }
}
