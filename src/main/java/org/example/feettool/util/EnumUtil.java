/*
 * Copyright © 2023 FinToken Ltd.,  All rights reserved.
 */

package org.example.feettool.util;

import jakarta.validation.constraints.NotNull;

import java.util.*;

/**
 * 枚举工具类
 *
 * @author zhengguangle
 * @version create
 * @since 2023-02-06
 */
public class EnumUtil {
    public static List<Map<String, String>> list(EnumNameValue[] arr) {
        if (arr == null) return Collections.emptyList();

        List<Map<String, String>> list = new ArrayList<>();
        for (EnumNameValue obj : arr) {
            HashMap<String, String> map = new HashMap<>();
            map.put("key", obj.name());
            map.put("value", obj.getValue());
            list.add(map);
        }

        return list;
    }

    public static <T extends Enum<T>> boolean contains(@NotNull T[] arr, @NotNull String name) {
        for (T t : arr) {
            if (t.name().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
