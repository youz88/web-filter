package com.youz.filter.enums;

import com.youz.filter.rule.AbstractFilter;
import com.youz.filter.rule.EmojiFilter;
import com.youz.filter.rule.ReplaceFilter;

/**
 * 过滤类型
 */
public enum FilterType {

    Emoji, Replace;

    public AbstractFilter newInstance(Object... objs) {
        if (this == Emoji) {
            return new EmojiFilter();
        } else if (this == Replace) {
            return new ReplaceFilter(objs);
        }
        return null;
    }
}
