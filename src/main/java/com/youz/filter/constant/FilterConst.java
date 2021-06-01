package com.youz.filter.constant;

import java.util.Arrays;

public class FilterConst {

    public static final String EMPTY = "";

    public static final String SLASH = "/";

    public static final String STAR = "*";

    public static void main(String[] args) {
        String uri = "/aaa/bbb/ccc";
        String[] split = uri.split(SLASH);
        System.out.println(Arrays.toString(split));
    }
}
