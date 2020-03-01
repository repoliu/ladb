package com.dky.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过反射将对象中的属性转换为数组的方法
 * @author YangSL
 */
public class ObjToArraysUtils {
    /**
     * 将所有属性都转换为String数组
     * @param object 传入的对象
     * @return 返回String数组
     */
    public static String[] getFieldValues(Object object) {
        if (object == null)
            return null;
        Field[] fields = object.getClass().getDeclaredFields();
        List<String> fieldValueList = new ArrayList<>();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object obj = f.get(object);
                if (obj == null) {
                    continue;
                }
                fieldValueList.add(obj.toString());
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValueList.toArray(new String[fieldValueList.size()]);
    }

    /**
     * 将对象转换为Obj数组，优点可以保证每个属性的特性
     * @param object 传入的Obj对象
     * @return 返回Obj数组
     */
    public static Object[] getFieldValuesObject(Object object) {
        if (object == null)
            return null;
        Field[] fields = object.getClass().getDeclaredFields();
        List<Object> fieldValueList = new ArrayList<>();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object obj = f.get(object);
                if (obj == null) {
                    continue;
                }
                fieldValueList.add(obj);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValueList.toArray(new Object[fieldValueList.size()]);
    }
}
