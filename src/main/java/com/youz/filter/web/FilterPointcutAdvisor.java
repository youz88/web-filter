package com.youz.filter.web;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;

public class FilterPointcutAdvisor extends AspectJExpressionPointcutAdvisor {

    private final String RELACE_PATH = "${path}";
    private final String DEFAULT_PATH = "execution(* ${path}.*.*(..))";
    private FilterAdvice filterAdvice = new FilterAdvice();

    public FilterPointcutAdvisor(String path) {
        setExpression(DEFAULT_PATH.replace(RELACE_PATH,path));
        setAdvice(filterAdvice);
    }

    public FilterPointcutAdvisor excludePathPatterns(String... paths) {
        filterAdvice.excludePathPatterns(paths);
        return this;
    }

    public FilterPointcutAdvisor addPathPatterns(String... paths) {
        filterAdvice.addPathPatterns(paths);
        return this;
    }
}
