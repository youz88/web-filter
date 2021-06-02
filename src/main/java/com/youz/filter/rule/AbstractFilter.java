package com.youz.filter.rule;

import com.youz.filter.util.CommonUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractFilter implements Filter {

    private final List<Pattern> excludePatterns = new ArrayList();
    private final List<Pattern> includePatterns = new ArrayList();

    public AbstractFilter excludePathPatterns(String... patterns) {
        Collection<? extends Pattern> patternList = initPatterns(patterns);
        if (CommonUtil.isNotEmpty(patternList)) {
            excludePatterns.addAll(patternList);
        }
        return this;
    }

    public AbstractFilter includePatterns(String... patterns) {
        includePatterns.addAll(initPatterns(patterns));
        return this;
    }


    private Collection<? extends Pattern> initPatterns(String... patterns) {
        if (CommonUtil.isEmpty(patterns)) return null;
        List<Pattern> patternList = new ArrayList<>();

        for (String pattern : patterns) {
            if (CommonUtil.isBlank(pattern)) continue;
            pattern = pattern.trim()
                .replace("**", "$$")
                .replace("*", "[^/]*?")
                .replace("$$", "**")
                .replace("**", ".*?");
            patternList.add(Pattern.compile(pattern));
        }

        return patternList;
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
        return matches(includePatterns, uri) && !matches(excludePatterns, uri);
    }

    private boolean matches(List<Pattern> patterns, String uri) {
        if (CommonUtil.isEmpty(patterns)) {
            return false;
        }
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(uri);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
