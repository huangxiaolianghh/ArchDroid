package com.huangxiaoliang.mvplib.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/6/23 19:52</pre>
 * <pre>@desc 类实例工具</pre>
 */
@SuppressWarnings("unchecked")
public class ClassUtils {

    private ClassUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取父类泛型 Class
     *
     * @param o   Object
     * @param <T> 泛型
     * @return 泛型 Class
     */
    public static <T> Class<T> getSuperClassGenericTypeClass(Object o) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                return (Class<T>) types[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 实例化父类泛型对象（通过获得泛型类的父类，通过反射实例化，拿到泛型实例）
     *
     * @param o   装载泛型的对象
     * @param i   Object对象的泛型位置
     * @param <T> 泛型传递
     * @return 泛型对象
     */
    public static <T> T getSuperClassGenericType(Object o, int i) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] types = parameterizedType.getActualTypeArguments();
                return (T) ((Class<?>) types[i]).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类名初始化
     *
     * @param path   类的路径
     * @param object 类构造函数参数
     * @return 类实例化对象
     */
    public static <T> T instanceByClasspath(String path, Object... object) {
        try {
            // 根据给定的类名初始化类
            Class<T> aClass = (Class<T>) Class.forName(path);
            Constructor<?>[] cons = aClass.getConstructors();
            return (T) cons[0].newInstance(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类名初始化
     *
     * @param path        类的路径
     * @param classMethod 类的方法
     * @param object      类方法参数
     * @return 类实例化对象
     */
    public static <T> T instanceByClasspath(String path, String classMethod, Object... object) {
        try {
            // 根据给定的类名初始化类
            Class<T> aClass = (Class<T>) Class.forName(path);
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
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
