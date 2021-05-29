package com.youz.filter.rule;

import com.youz.filter.util.CommonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    protected void doFilter(Object... objs) { }

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

    protected boolean isWrapPrimitive(Object o) {
        return o instanceof Byte || o instanceof Short ||
                o instanceof Integer || o instanceof Long ||
                o instanceof Float || o instanceof Double ||
                o instanceof Character || o instanceof Boolean;
    }
}
