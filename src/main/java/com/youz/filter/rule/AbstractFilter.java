package com.youz.filter.rule;

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
    public void process(Object o) { }
}
