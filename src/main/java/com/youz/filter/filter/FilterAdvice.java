package com.youz.filter.filter;

import com.youz.filter.rule.Filter;
import com.youz.filter.util.CommonUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterAdvice implements MethodInterceptor {

    private final List<Filter> filterList = new ArrayList<>();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (CommonUtil.isNotEmpty(filterList)) {
            HttpServletRequest request = getRequest();
            for (Filter filter : filterList) {
                filter.process(request.getMethod(), request.getRequestURI(), methodInvocation.getArguments());
            }
        }
        return methodInvocation.proceed();
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public void addFilters(Filter[] filters) {
        filterList.addAll(Arrays.asList(filters));
    }
}
