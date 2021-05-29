package com.youz.filter.rule;

import com.youz.filter.exception.FilterException;
import com.youz.filter.util.CommonUtil;
import com.youz.filter.util.EmojiUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReplaceFilter extends AbstractFilter {

    private Map<?,?> replaceTemplate;

    public ReplaceFilter(Object... objs) {
        if (CommonUtil.isEmpty(objs) || objs.length > 1) {
            throw new FilterException("please set a template that needs to be replaced");
        }
        if (!(objs[0] instanceof Map)) {
            throw new FilterException("the template type is java.util.Map");
        }
        Map replaceTemplate = (Map) objs[0];
        this.replaceTemplate = replaceTemplate;
    }

    @Override
    public void doFilter(Object... objs) {
        for (int i = 0; i < objs.length; i++) {
            Object o = objs[i];
            if (o == null || isWrapPrimitive(o)) {
                continue;
            }
            if (o instanceof String) {
                String str = o.toString();
                for (Map.Entry<?, ?> entry : replaceTemplate.entrySet()) {
                    objs[i] = str.replace(entry.getKey().toString(),entry.getValue().toString());
                }
            } else if (o instanceof Collection) {
                Collection collection = (Collection) o;
                if (CommonUtil.isNotEmpty(collection)) {
                    doFilter(collection);
                }
            }
        }
    }

}
