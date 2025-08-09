package dev.aika.smsn.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    @SafeVarargs
    public static <T> Map<T, T> createMap(T... elements) {
        Map<T, T> map = new HashMap<>();
        for (int i = 0; i < elements.length; i += 2)
            map.put(elements[i], elements[i + 1]);
        return map;
    }
}