package com.youz.filter.util;

import com.youz.filter.constant.FilterConst;

import java.util.Collection;

public class CommonUtil {

    public static boolean isBlank(String str) {
        return str == null || FilterConst.EMPTY.equals(str.trim());
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }
}
