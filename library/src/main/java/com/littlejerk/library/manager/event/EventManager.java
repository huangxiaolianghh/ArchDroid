package com.littlejerk.library.manager.event;

/**
 * @author : HHotHeart
 * @date : 2021/6/7 21:45
 * @desc : 通知事件管理类
 */
public class EventManager {

    private static IEventBus sEventBus;

    public static IEventBus getBus() {
        if (sEventBus == null) {
            synchronized (EventManager.class) {
                if (sEventBus == null) {
                    sEventBus = EventBusImpl.get();
                }
            }
        }
        return sEventBus;
    }

}
