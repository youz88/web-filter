package com.youz.filter.enums;

import com.youz.filter.rule.AbstractFilter;
import com.youz.filter.rule.EmojiFilter;

/** 过滤类型 */
public enum FilterType {

    Emoji;

    public AbstractFilter instance() {
        if (this == Emoji) {
            return new EmojiFilter();
        }
        return null;
    }
}
