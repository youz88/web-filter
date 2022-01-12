package com.youz.filter.rule;

import com.youz.filter.exception.FilterException;
import com.youz.filter.util.CommonUtil;

import java.util.Map;

public class ReplaceFilter extends AbstractFilter {

    private Map<?, ?> replaceTemplate;

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
    protected String stringFilter(String str) {
        for (Map.Entry<?, ?> entry : replaceTemplate.entrySet()) {
            str = str.replace(entry.getKey().toString(), entry.getValue().toString());
        }
        return str;
    }

    @Override
    protected boolean fieldTypeFilter(Object o) {
        return o instanceof Byte || o instanceof Short ||
                o instanceof Integer || o instanceof Long ||
                o instanceof Float || o instanceof Double ||
                o instanceof Character || o instanceof Boolean;
    }

}
