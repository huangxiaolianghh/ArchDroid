package com.littlejerk.library.manager.log;

import android.text.TextUtils;

import com.littlejerk.library.BuildConfig;
import com.littlejerk.library.manager.MVPArchConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/1 16:37
 * @Description : Log工具
 */
public class UILog {

    private static final String ROOT_TAG = MVPArchConfig.LOG_TAG;


    private static String checkTag() {
        if (getDelegate() == null) return ROOT_TAG;
        return TextUtils.isEmpty(getDelegate().getTag()) ? ROOT_TAG : getDelegate().getTag();
    }

    public static void init(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(3)
                .tag(ROOT_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static void v(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.v(tag, msg, obj);
        }

    }

    public static void v(final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.v(checkTag(), msg, obj);
        }
    }

    public static void d(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.d(tag, msg, obj);
        }
    }

    public static void d(final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.d(checkTag(), msg, obj);
        }
    }

    public static void i(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.i(tag, msg, obj);
        }
    }

    public static void i(final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.i(checkTag(), msg, obj);
            return;
        }
    }

    public static void w(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.w(tag, msg, obj);
        }
    }

    public static void w(final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.w(checkTag(), msg, obj);
        }
    }

    public static void e(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.e(tag, msg, obj);
        }
    }

    public static void e(final String msg, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.e(checkTag(), msg, obj);
        }
    }

    public static void json(final String tag, final String msg) {
        if (sDelegate != null) {
            sDelegate.json(tag, msg);
        }
    }

    public static void json(final String msg) {
        if (sDelegate != null) {
            sDelegate.json(checkTag(), msg);
        }
    }

    public static void xml(final String tag, final String msg) {
        if (sDelegate != null) {
            sDelegate.xml(tag, msg);
        }
    }

    public static void xml(final String msg) {
        if (sDelegate != null) {
            sDelegate.xml(checkTag(), msg);
        }
    }

    public static void printErrStackTrace(final String tag, final Throwable throwable, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.printErrStackTrace(tag, throwable, obj);
        }
    }

    public static void printErrStackTrace(final Throwable throwable, final Object... obj) {
        if (sDelegate != null) {
            sDelegate.printErrStackTrace(checkTag(), throwable, obj);
        }
    }

    private static LogDelegate sDelegate = new UILogDelegate().init();

    public static LogDelegate getDelegate() {
        return sDelegate;
    }

    public static void setDelegate(LogDelegate mDelegate) {
        sDelegate = mDelegate;
    }

    public interface LogDelegate {

        String getTag();

        LogDelegate init();

        void v(final String tag, final String msg, final Object... obj);

        void d(final String tag, final String msg, final Object... obj);

        void i(final String tag, final String msg, final Object... obj);

        void w(final String tag, final String msg, final Object... obj);

        void e(final String tag, final String msg, final Object... obj);

        void xml(final String tag, final String msg);

        void json(final String tag, final String msg);

        void printErrStackTrace(final String tag, final Throwable throwable, final Object... obj);

    }
}
