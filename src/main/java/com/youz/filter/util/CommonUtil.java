package com.youz.filter.util;

import com.youz.filter.constant.FilterConst;

import java.util.Collection;

public class CommonUtil {

    /** 空字符串判断 */
    public static boolean isBlank(String str) {
        return str == null || FilterConst.EMPTY.equals(str.trim());
    }

    /** 空集合判断 */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /** 非空集合判断 */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    /** 空数组判断 */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
    /** 非空数组判断 */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }
}
