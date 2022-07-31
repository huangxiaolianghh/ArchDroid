package com.huangxiaoliang.mvplib.manager.event;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 21:46</pre>
 * <pre>@desc 通知事件接口类</pre>
 */
public interface IEventBus {

    /**
     * 注册订阅者
     *
     * @param subscriber 订阅者
     */
    void register(Object subscriber);

    /**
     * 注销订阅者
     *
     * @param subscriber 订阅者
     */
    void unregister(Object subscriber);

    /**
     * 发送事件
     *
     * @param event 事件
     */
    void post(AbsEvent event);

    /**
     * 发送黏性事件
     *
     * @param event 黏性事件
     */
    void postSticky(AbsEvent event);

    /**
     * 注销黏性事件
     *
     * @param eventType 事件Class<T>
     * @param <T>       泛型
     * @return T
     */
    <T> T removeStickyEvent(Class<T> eventType);

    /**
     * 注销黏性事件
     *
     * @param event 黏性事件
     * @return boolean
     */
    boolean removeStickyEvent(AbsEvent event);

    /**
     * 清除订阅者和事件的缓存
     */
    void clear();


    /**
     * 事件抽象类，发送的事件需继续此类
     */
    abstract class AbsEvent {
        public abstract int getTag();
    }
}
