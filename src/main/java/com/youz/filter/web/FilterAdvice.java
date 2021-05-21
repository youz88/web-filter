package com.youz.filter.web;

import com.youz.filter.annotation.FilterParam;
import com.youz.filter.constant.FilterConst;
import com.youz.filter.enums.FilterType;
import com.youz.filter.util.EmojiUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FilterAdvice implements MethodInterceptor {

    private final List<String> excludePatterns = new ArrayList();
    private final List<String> includePatterns = new ArrayList();

    public void excludePathPatterns(String[] paths) {
        excludePatterns.addAll(Arrays.asList(paths));
    }

    public void addPathPatterns(String[] paths) {
        includePatterns.addAll(Arrays.asList(paths));
    }

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
            parameter.getAnnotation(FilterParam.class);
            Class<?> clazz = parameter.getType();
            if (clazz == String.class) {
                arguments[i] = "6666666666";
            }
        }
        return methodInvocation.proceed();
    }

    private HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private void filterEmoji(Object arg) throws Exception {
        // 获取目标对象
        Class<?> clazz = arg.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 只处理string类型
            if (field.getType() == FilterConst.EMPTY.getClass()) {
                // 是否加了【emoji】注解
//                Emoji emoji = field.getAnnotation(Emoji.class);
//                if (Objects.nonNull(emoji)) {
                field.setAccessible(true);
                Object value = field.get(arg);
                if (Objects.nonNull(value)) {
                    String filterEmoji = EmojiUtil.filter(value.toString());
                    // 如果不包含emoji则不作处理
                    if (!value.toString().equals(filterEmoji)){
                        String fieldName = field.getName();
                        String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                        Method m = clazz.getMethod(methodName,String.class);
                        m.invoke(arg, filterEmoji);
                    }
                }
//                }
            }
        }
    }

}
