package com.littlejerk.library.manager.event;

import com.littlejerk.library.manager.MVPArchConfig;

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
                    if (MVPArchConfig.getInstance().getEventBus() == null) {
                        //框架实现的EventBus
                        sEventBus = EventBusImpl.get();
                    } else {
                        //调用者实现的EventBus
                        sEventBus = MVPArchConfig.getInstance().getEventBus();
                    }

                }
            }
        }
        return sEventBus;
    }

}
