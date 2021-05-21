package com.youz.filter.annotation;

import com.youz.filter.enums.FilterType;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Filter {

    FilterType[] value();
}
