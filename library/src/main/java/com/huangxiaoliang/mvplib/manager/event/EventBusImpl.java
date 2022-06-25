package com.huangxiaoliang.mvplib.manager.event;

import org.greenrobot.eventbus.EventBus;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 21:49</pre>
 * <pre>@desc 通知事件实现类</pre>
 */
public final class EventBusImpl implements IEventBus {

    private EventBusImpl() {
    }

    protected static EventBusImpl get() {
        return Holder.instance;
    }

    private static class Holder {
        private static final EventBusImpl instance = new EventBusImpl();
    }

    @Override
    public void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);

    }

    @Override
    public void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    @Override
    public void post(AbsEvent event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(AbsEvent event) {
        EventBus.getDefault().postSticky(event);
    }


    @Override
    public <T> T removeStickyEvent(Class<T> eventType) {
        return EventBus.getDefault().removeStickyEvent(eventType);
    }

    @Override
    public boolean removeStickyEvent(AbsEvent event) {
        return EventBus.getDefault().removeStickyEvent(event);
    }

    @Override
    public void clear() {
        EventBus.clearCaches();
    }
}
