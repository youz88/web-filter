package com.youz.filter.rule;

import com.youz.filter.util.CommonUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractFilter implements Filter {

    private final Set<String> resolvedMethods = new HashSet<>(16);
    private final List<Pattern> excludePatterns = new ArrayList();
    private final List<Pattern> includePatterns = new ArrayList();

    public AbstractFilter excludePatterns(String... patterns) {
        Collection<? extends Pattern> patternList = initPatterns(patterns);
        if (CommonUtil.isNotEmpty(patternList)) {
            excludePatterns.addAll(patternList);
        }
        return this;
    }

    public AbstractFilter includePatterns(String... patterns) {
        Collection<? extends Pattern> patternList = initPatterns(patterns);
        if (CommonUtil.isNotEmpty(patternList)) {
            includePatterns.addAll(patternList);
        }
        return this;
    }

    public Filter allowedMethods(String... methods) {
        resolvedMethods.addAll(Arrays.asList(methods));
        return this;
    }

    @Override
    public void process(String method, String uri, Object... objs) {
        if (!match(method, uri)) {
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
                            field.set(o, stringFilter((String) field.get(o)));
                        } catch (IllegalAccessException e) {
                            // ignore
                        }
                    }
                }
            }
        }
    }

    /**
     * 初始化正则表达式
     *
     * @param patterns 请求资源路径
     * @return
     */
    private Collection<? extends Pattern> initPatterns(String... patterns) {
        if (CommonUtil.isEmpty(patterns)) return null;

        List<Pattern> patternList = new ArrayList<>();
        for (String pattern : patterns) {
            if (CommonUtil.isBlank(pattern)) continue;

            pattern = pattern.trim()
                    .replace("**", "$oo$")
                    .replace("*", "[^/]*?")
                    .replace("$oo$", "**")
                    .replace("**", ".*?");
            patternList.add(Pattern.compile(pattern));
        }
        return patternList;
    }

    /**
     * 判断是否拦截该方法
     *
     * @param method 请求方法（GET|POST...）
     * @param uri    请求资源路径
     * @return
     */
    private boolean match(String method, String uri) {
        if (CommonUtil.isBlank(uri) || !resolvedMethods.contains(method)) {
            return false;
        }
        return matches(includePatterns, uri) && !matches(excludePatterns, uri);
    }

    /**
     * 判断请求资源对象是否匹配正则
     *
     * @param patterns 拦截正则表达式
     * @param uri      请求资源路径
     * @return
     */
    private boolean matches(List<Pattern> patterns, String uri) {
        if (CommonUtil.isEmpty(patterns)) return false;

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(uri);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}
