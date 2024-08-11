package org.example.feettool.util;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {
    private static MyObjectMapper jsonMapper;

    static {
        try {
            jsonMapper = new MyObjectMapper();
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }

    public static String getString(Object obj) {
        if (obj == null) return null;

        String str = null;
        try {
            str = jsonMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error(e.toString(), e);
        }

        return str;
    }

    public static <E> E read(Class<E> clazz, String json) {
        E e = null;
        try {
            e = jsonMapper.readValue(json, clazz);
        } catch (Exception e2) {
            log.error(e2.toString(), e2);
        }

        return e;
    }

    public static <E> E read(String json, TypeReference<E> valueTypeRef) {
        E e = null;
        try {
            e = jsonMapper.readValue(json, valueTypeRef);
        } catch (Exception e2) {
            log.error(e2.toString(), e2);
        }

        return e;
    }
}
