package com.youz.filter.util;

import com.youz.filter.constant.FilterConst;

public class EmojiUtil {

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source 数据源
     * @return
     */
    public static String filter(String source) {
        if (CommonUtil.isBlank(source)) return source;

        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return FilterConst.EMPTY;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 判断是否含有非文字字符
     *
     * @param codePoint 字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static void main(String[] args) {
        String str = "aaaaa\uD83C\uDD70";
        System.out.println(EmojiUtil.filter(str));
    }
}
