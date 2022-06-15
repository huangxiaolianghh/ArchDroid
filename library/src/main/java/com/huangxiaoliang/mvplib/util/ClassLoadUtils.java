package com.huangxiaoliang.mvplib.util;

import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;
import com.huangxiaoliang.mvplib.manager.log.UILog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 10:36
 * @Description : 类实例工具
 */
public class ClassLoadUtils {

    @SuppressWarnings("unchecked")
    public static <T> T getT(Object o, int i) {
        //通过获得泛型类的父类，拿到泛型的接口类实例，通过反射来实例化 model
        ParameterizedType parameterizedType = (ParameterizedType) o.getClass().getGenericSuperclass();
        UILog.e("===========", "attach: " + o.getClass().getSimpleName());
        if (parameterizedType != null) {
            Type[] types = parameterizedType.getActualTypeArguments();
            try {
                return ((Class<T>) types[i]).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 初始化LCE代理类
     *
     * @param path   LCE代理类的路径
     * @param object 构造函数的参数
     * @return LCE代理对象
     */
    public static ILCEView newLCEDelegate(String path, Object... object) {
        try {
            // 根据给定的类名初始化类
            Class<?> aClass = Class.forName(path);
            ILCEView lceView = null;
            Constructor<?> cons[] = null;
            cons = aClass.getConstructors();
            lceView = (ILCEView) cons[0].newInstance(object);
            return lceView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 根据类名初始化
     *
     * @param path 实例化类对象的路径
     */
    public static Object loadClassByPath(String path, String classMethod, Object... object) {
        try {
            // 根据给定的类名初始化类
            Class aClass = Class.forName(path);
            // 实例化这个类
            Object obj = aClass.newInstance();
            // 获得这个类的所有方法
            Method[] methods = aClass.getMethods();
            // 循环查找想要的方法
            for (Method method : methods) {
                if (classMethod.equals(method.getName())) {
                    // 调用这个方法，invoke第一个参数是类名，后面是方法需要的参数
                    method.invoke(obj, object);
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
