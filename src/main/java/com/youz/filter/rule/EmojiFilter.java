package com.youz.filter.rule;

import com.youz.filter.util.CommonUtil;
import com.youz.filter.util.EmojiUtil;

import java.util.Collection;

public class EmojiFilter extends AbstractFilter {

    @Override
    public void doFilter(Object... objs) {
        for (int i = 0; i < objs.length; i++) {
            Object o = objs[i];
            if (o == null || isWrapPrimitive(o)) {
                continue;
            }
            if (o instanceof String) {
                objs[i] = EmojiUtil.filter(o.toString());
            } else if (o instanceof Collection) {
                Collection collection = (Collection) o;
                if (CommonUtil.isNotEmpty(collection)) {
                    doFilter(collection);
                }
            }
        }
    }

}
