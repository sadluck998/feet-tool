/*
 * Copyright © 2023 FinToken Ltd.,  All rights reserved.
 */

package org.example.feettool.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 对象和map转换工具
 *
 * @author zhengguangle
 * @version create
 * @since 2023-03-02
 */
public class ObjectMapUtil {
    /**
     * Just for simple object
     *
     * @param map   object map
     * @param clazz object's class
     * @param <T>   Object's type
     * @return a simple object
     * @throws Exception 异常
     */
    public static <T> T fromMap(@NotNull Map<String, String> map, @NotNull Class<T> clazz) throws Exception {
        T t = clazz.newInstance();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            PropertyDescriptor descriptor = new PropertyDescriptor(k, clazz);
            Class<?> propertyType = descriptor.getPropertyType();
            if (propertyType.isAssignableFrom(Date.class)) {
                Date date = new Date(Long.valueOf(v));
                descriptor.getWriteMethod().invoke(t, date);
            } else if (propertyType.isAssignableFrom(BigDecimal.class)) {
                BigDecimal num = new BigDecimal(v);
                descriptor.getWriteMethod().invoke(t, num);
            } else if (propertyType.isAssignableFrom(String.class)) {
                descriptor.getWriteMethod().invoke(t, v);
            } else if (propertyType.isPrimitive()) {
                descriptor.getWriteMethod().invoke(t, Long.valueOf(v).longValue());
            }
        }

        return t;
    }

    /**
     * Just for simple object
     *
     * @param t   map object
     * @param <T> Object's type
     * @return object map
     * @throws Exception 异常
     */
    public static <T> Map<String, String> toMap(T t) throws Exception {
        Map<String, String> map = new HashMap<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers())) continue;

            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), t.getClass());
            Object val = descriptor.getReadMethod().invoke(t);
            if (val == null) continue;
            if (val instanceof Collection || val instanceof Map) {
                throw new UnsupportedOperationException("Not a simple object");
            }

            Class<?> propertyType = Class.forName(field.getGenericType().getTypeName());
            if (propertyType.isPrimitive() || propertyType.isAssignableFrom(String.class)) {
                map.put(field.getName(), String.valueOf(val));
            } else if (propertyType.isAssignableFrom(Date.class)) {
                Date date = (Date) val;
                map.put(field.getName(), String.valueOf(date.getTime()));
            } else if (propertyType.isAssignableFrom(BigDecimal.class)) {
                BigDecimal num = (BigDecimal) val;
                map.put(field.getName(), num.toPlainString());
            } else {
                Field[] propertyFields = propertyType.getDeclaredFields();
                List<Field> fieldList = Arrays.stream(propertyFields).filter(e -> "TYPE".equals(e.getName())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(fieldList)) {
                    if (((Class<?>) propertyType.getDeclaredField("TYPE").get(null)).isPrimitive()) {
                        map.put(field.getName(), String.valueOf(val));
                    } else {
                        throw new UnsupportedOperationException("Not a simple object");
                    }
                } else {
                    throw new UnsupportedOperationException("Not a simple object");
                }
            }
        }

        return map;
    }
}
