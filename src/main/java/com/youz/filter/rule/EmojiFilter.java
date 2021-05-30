package com.youz.filter.rule;

import com.youz.filter.util.EmojiUtil;

public class EmojiFilter extends AbstractFilter {

    @Override
    protected String stringFilter(String str) {
        return EmojiUtil.filter(str);
    }

    @Override
    protected boolean fieldTypeFilter(Object o) {
        return o instanceof Byte || o instanceof Short ||
                o instanceof Integer || o instanceof Long ||
                o instanceof Float || o instanceof Double ||
                o instanceof Character || o instanceof Boolean;
    }


}
