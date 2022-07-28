package com.huangxiaoliang.mvplib.manager.event;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/7 21:45</pre>
 * <pre>@desc 通知事件管理类</pre>
 */
public class EventManager {

    private static IEventBus sEventBus;

    public static IEventBus getBus() {
        if (sEventBus == null) {
            synchronized (EventManager.class) {
                if (sEventBus == null) {
                    if (MVPArchConfig.get().getEventBus() == null) {
                        //框架实现的EventBus
                        sEventBus = EventBusImpl.get();
                    } else {
                        //调用者实现的EventBus
                        sEventBus = MVPArchConfig.get().getEventBus();
                    }

                }
            }
        }
        return sEventBus;
    }

}
