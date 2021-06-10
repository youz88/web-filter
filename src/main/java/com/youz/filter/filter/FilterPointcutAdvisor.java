package com.youz.filter.filter;

import com.youz.filter.exception.FilterException;
import com.youz.filter.rule.Filter;
import com.youz.filter.util.CommonUtil;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterPointcutAdvisor extends AspectJExpressionPointcutAdvisor {

    private final String RELACE_PATH = "${path}";
    private final String DEFAULT_PATH = "execution(* ${path}.*.*(..))";
    private FilterAdvice filterAdvice = new FilterAdvice();

    public FilterPointcutAdvisor(String... paths) {
        if (CommonUtil.isEmpty(paths)) {
            throw new FilterException("scan path cannot be empty");
        }
        String expression = Arrays.stream(paths)
                .map(o -> DEFAULT_PATH.replace(RELACE_PATH, o))
                .collect(Collectors.joining(" || ", "(", ")"));
        setExpression(expression);
        setAdvice(filterAdvice);
    }

    public FilterPointcutAdvisor addFilter(Filter... filters) {
        filterAdvice.addFilters(filters);
        return this;
    }
}
