package com.huangxiaoliang.mvplib.manager;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import com.huangxiaoliang.mvplib.manager.log.UILog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author HHotHeart
 * @time 2021/6/7 18:29
 * @description UI注解绑定管理器
 */
public class UIBindManager {

    private static final String TAG = "UIBindManager";

    private final Map<Object, Object> uiBinds = new HashMap<>();

    private static volatile UIBindManager sInstance;

    private UIBindManager() {
    }

    public static UIBindManager getInstance() {
        if (sInstance == null) {
            synchronized (UIBindManager.class) {
                if (sInstance == null) {
                    sInstance = new UIBindManager();
                }
            }
        }
        return sInstance;
    }


    /**
     * UI绑定
     *
     * @param target
     * @return
     */
    public Unbinder bind(Object target) {
        Unbinder unbinder = Unbinder.EMPTY;
        if (target instanceof Activity) {
            unbinder = ButterKnife.bind((Activity) target);
        } else if (target instanceof Dialog) {
            unbinder = ButterKnife.bind((Dialog) target);
        } else if (target instanceof View) {
            unbinder = ButterKnife.bind((View) target);
        }
        if (unbinder != Unbinder.EMPTY) {
            uiBinds.put(target, unbinder);
        }
        return unbinder;
    }


    /**
     * UI绑定
     *
     * @param target
     * @param source
     * @return
     */
    public Unbinder bind(Object target, Object source) {
        Unbinder unbinder = Unbinder.EMPTY;

        if (source instanceof Activity) {
            unbinder = ButterKnife.bind(target, (Activity) source);
        } else if (source instanceof Dialog) {
            unbinder = ButterKnife.bind(target, (Dialog) source);
        } else if (source instanceof View) {
            unbinder = ButterKnife.bind(target, (View) source);
        }
        if (unbinder != Unbinder.EMPTY) {
            uiBinds.put(target, unbinder);
        }
        return unbinder;
    }


    /**
     * UI解绑
     *
     * @param target
     */
    public void unbind(Object target) {
        Iterator<Map.Entry<Object, Object>> it = uiBinds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            if (entry.getKey().equals(target)) {
                if (entry.getValue() instanceof Unbinder && entry.getValue() != Unbinder.EMPTY) {
                    ((Unbinder) entry.getValue()).unbind();
                    it.remove();
                    UILog.e(TAG, "解绑成功");
                }
            }

        }
    }

}
