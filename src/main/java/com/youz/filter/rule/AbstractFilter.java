package com.youz.filter.rule;

import com.youz.filter.util.CommonUtil;

import java.lang.reflect.Field;
import java.util.*;

public class AbstractFilter implements Filter {

    private final List<String> excludePatterns = new ArrayList();
    private final List<String> includePatterns = new ArrayList();

    public AbstractFilter excludePathPatterns(String... paths) {
        excludePatterns.addAll(Arrays.asList(paths));
        return this;
    }

    public AbstractFilter addPathPatterns(String... paths) {
        includePatterns.addAll(Arrays.asList(paths));
        return this;
    }

    @Override
    public void process(String uri, Object... objs) {
        if (!match(uri)) {
            return;
        }
        if (CommonUtil.isNotEmpty(objs)) {
            doFilter(objs);
        }
    }

    protected boolean fieldTypeFilter(Object o) {
        return false;
    }

    protected String stringFilter(String str) {
        return str;
    }

    private void doFilter(Object... objs) {
        for (int i = 0; i < objs.length; i++) {
            Object o = objs[i];
            if (o == null || fieldTypeFilter(o)) {
                continue;
            }
            if (o instanceof String) {
                objs[i] = stringFilter(o.toString());
            } else if (o instanceof Collection) {
                Collection collection = (Collection) o;
                if (CommonUtil.isNotEmpty(collection)) {
                    doFilter(collection);
                }
            } else {
                Field[] fields = o.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (String.class.isAssignableFrom(field.getType())) {
                        try {
                            field.set(o,stringFilter((String) field.get(o)));
                        } catch (IllegalAccessException e) {
                            // ignore
                        }
                    }
                }
            }
        }
    }

    private boolean match(String uri) {
        if (CommonUtil.isBlank(uri)) {
            return false;
        }
        if (CommonUtil.isNotEmpty(includePatterns)) {
            for (String includePattern : includePatterns) {

            }
        }
        if (CommonUtil.isNotEmpty(excludePatterns)) {
            for (String includePattern : includePatterns) {

            }
        }
        return true;
    }

}
