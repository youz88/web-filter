package com.youz.filter.util;

import com.youz.filter.constant.FilterConst;

public class CommonUtil {

    public static boolean isBlank(String str) {
        return str == null || FilterConst.EMPTY.equals(str.trim());
    }

}
