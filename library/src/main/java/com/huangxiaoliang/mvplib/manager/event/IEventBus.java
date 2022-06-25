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
     * @param subscriber
     */
    void register(Object subscriber);

    /**
     * 注销订阅者
     *
     * @param subscriber
     */
    void unregister(Object subscriber);

    /**
     * 发送事件
     *
     * @param event
     */
    void post(AbsEvent event);

    /**
     * 发送黏性事件
     *
     * @param event
     */
    void postSticky(AbsEvent event);


    /**
     * 注销黏性事件
     *
     * @param eventType
     * @param <T>
     * @return
     */
    <T> T removeStickyEvent(Class<T> eventType);

    /**
     * 注销黏性事件
     *
     * @param event
     * @return
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
