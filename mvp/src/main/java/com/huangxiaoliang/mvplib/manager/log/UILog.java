package com.huangxiaoliang.mvplib.manager.log;

import android.text.TextUtils;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;


/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:37</pre>
 * <pre>@desc Log工具</pre>
 */
public class UILog {

    private static final String ROOT_TAG = MVPArchConfig.LOG_TAG;
    private static final boolean isLoggable = MVPArchConfig.get().isLoggable();

    private static String checkTag() {
        if (getDelegate() == null) {
            return ROOT_TAG;
        }
        return TextUtils.isEmpty(getDelegate().getTag()) ? ROOT_TAG : getDelegate().getTag();
    }

    public static void v(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.v(tag, msg, obj);
        }

    }

    public static void v(final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.v(checkTag(), msg, obj);
        }
    }

    public static void d(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.d(tag, msg, obj);
        }
    }

    public static void d(final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.d(checkTag(), msg, obj);
        }
    }

    public static void i(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.i(tag, msg, obj);
        }
    }

    public static void i(final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.i(checkTag(), msg, obj);
        }
    }

    public static void w(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.w(tag, msg, obj);
        }
    }

    public static void w(final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.w(checkTag(), msg, obj);
        }
    }

    public static void e(final String tag, final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.e(tag, msg, obj);
        }
    }

    public static void e(final String msg, final Object... obj) {
        if (sDelegate != null && isLoggable) {
            sDelegate.e(checkTag(), msg, obj);
        }
    }

    public static void json(final String tag, final String msg) {
        if (sDelegate != null && isLoggable) {
            sDelegate.json(tag, msg);
        }
    }

    public static void json(final String msg) {
        if (sDelegate != null && isLoggable) {
            sDelegate.json(checkTag(), msg);
        }
    }

    public static void xml(final String tag, final String msg) {
        if (sDelegate != null && isLoggable) {
            sDelegate.xml(tag, msg);
        }
    }

    public static void xml(final String msg) {
        if (sDelegate != null && isLoggable) {
            sDelegate.xml(checkTag(), msg);
        }
    }

    public static void printErrStackTrace(final String tag, final Throwable throwable) {
        if (sDelegate != null && isLoggable) {
            sDelegate.printErrStackTrace(tag, throwable);
        }
    }

    public static void printErrStackTrace(final Throwable throwable) {
        if (sDelegate != null && isLoggable) {
            sDelegate.printErrStackTrace(checkTag(), throwable);
        }
    }

    private static LogDelegate sDelegate = MVPArchConfig.get().getLogDelegate();

    private static LogDelegate getDelegate() {
        return sDelegate;
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

        void printErrStackTrace(final String tag, final Throwable throwable);

    }
}
