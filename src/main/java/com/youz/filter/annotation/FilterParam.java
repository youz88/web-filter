package com.youz.filter.annotation;

import com.youz.filter.enums.FilterType;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilterParam {

    FilterType[] value();
}
