package com.huangxiaoliang.mvparchdemo.util;

import com.huangxiaoliang.mvplib.manager.event.EventBusImpl;
import com.huangxiaoliang.mvplib.manager.event.IEventBus;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/27 18:59</pre>
 * <pre>@desc 自定义事件通知管理器{@link EventBusImpl}</pre>
 */
public class CustomEventBusImpl implements IEventBus {

    private CustomEventBusImpl() {
    }

    public static CustomEventBusImpl get() {
        return Holder.instance;
    }

    private static class Holder {
        private static final CustomEventBusImpl instance = new CustomEventBusImpl();
    }

    @Override
    public void register(Object subscriber) {

    }

    @Override
    public void unregister(Object subscriber) {

    }

    @Override
    public void post(AbsEvent event) {

    }

    @Override
    public void postSticky(AbsEvent event) {

    }

    @Override
    public <T> T removeStickyEvent(Class<T> eventType) {
        return null;
    }

    @Override
    public boolean removeStickyEvent(AbsEvent event) {
        return false;
    }

    @Override
    public void clear() {

    }
}
