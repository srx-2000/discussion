package com.srx.discussion.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-15 21:17:42
 */
public class CommonControllerUtil {
    /**
     * 通过传入的service，methodName，Args，调用相关service中叫methodName的方法，并以map<MethodName,ReturnValue>返回
     * @param service Service对象
     * @param methodName 被调用的Service的方法名
     * @param args 参数列表
     * @return Map map &lt MethodName,ReturnValue &gt
     */
    public static Map<String,Object> CommonController(Object service,String methodName,Object... args){
        Class[] array=new Class[args.length];
        Map<String,Object> map=new HashMap<>();
        for (int i=0;i<args.length;i++){
            Class<?> clazz = args[i].getClass();
            array[i]=clazz;
        }
        try {
            Method method = service.getClass().getMethod(methodName, array);
            Object invoke = method.invoke(service, args);
            map.put(methodName,invoke);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
}
