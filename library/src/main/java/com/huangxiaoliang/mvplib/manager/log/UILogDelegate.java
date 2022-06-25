package com.huangxiaoliang.mvplib.manager.log;

import android.util.Log;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import androidx.annotation.Nullable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/1 16:37</pre>
 * <pre>@desc Log代理</pre>
 */
public class UILogDelegate implements UILog.LogDelegate {

    /**
     * 初始化log
     */
    @Override
    public UILogDelegate init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(3)
                .tag(getTag())
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return MVPArchConfig.getInstance().isLoggable();
            }
        });
        return this;
    }

    @Override
    public String getTag() {
        return "MVPArch";
    }

    @Override
    public void v(String tag, String msg, Object... obj) {
        Logger.t(tag).v(msg, obj);

    }

    @Override
    public void d(String tag, String msg, Object... obj) {
        Logger.t(tag).d(msg, obj);

    }

    @Override
    public void i(String tag, String msg, Object... obj) {
        Logger.t(tag).i(msg, obj);

    }

    @Override
    public void w(String tag, String msg, Object... obj) {
        Logger.t(tag).w(msg, obj);

    }

    @Override
    public void e(String tag, String msg, Object... obj) {
        Logger.t(tag).e(msg, obj);

    }

    @Override
    public void xml(String tag, String msg) {
        Logger.t(tag).xml(msg);
    }

    @Override
    public void json(String tag, String msg) {
        Logger.t(tag).json(msg);

    }

    @Override
    public void printErrStackTrace(String tag, Throwable throwable) {
        Logger.t(tag).e(Log.getStackTraceString(throwable));

    }
}
