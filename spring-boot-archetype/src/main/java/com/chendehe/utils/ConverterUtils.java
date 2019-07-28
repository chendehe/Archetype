package com.chendehe.utils;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类型转换工具类.
 *
 * @author CDH
 * @since 2019/7/28 12:45
 */
public final class ConverterUtils {

    private ConverterUtils() {}

    /**
     * Map 按 V 排序.
     * 
     * @param map
     *            map
     * @param comparator
     *            顺序、倒序
     * @param <K>
     *            key
     * @param <V>
     *            value
     * @return 排序后的 map
     */
    public static <K, V> Map<K, V> sortMapValue(Map<K, V> map, Comparator<V> comparator) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(comparator))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }

    /**
     * Map 按 K 排序.
     * 
     * @param map
     *            map
     * @param comparator
     *            顺序、倒序
     * @param <K>
     *            key
     * @param <V>
     *            value
     * @return 排序后的 map
     */
    public static <K, V> Map<K, V> sortMapKey(Map<K, V> map, Comparator<K> comparator) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByKey(comparator))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }

}
