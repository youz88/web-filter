package com.youz.filter.web;

import com.youz.filter.rule.Filter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterAdvice implements MethodInterceptor {

    private final List<Filter> filterList = new ArrayList<>();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        HttpServletRequest request = getRequest();
        String url = request.getRequestURL().toString();
        System.out.println(url);

        Object[] arguments = methodInvocation.getArguments();
        Method method = methodInvocation.getMethod();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Class<?> clazz = parameter.getType();
            if (clazz == String.class) {

            }
        }
        return methodInvocation.proceed();
    }

    private HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public void addFilters(Filter[] filters) {
        filterList.addAll(Arrays.asList(filters));
    }
}
