package com.youz.filter.rule;

public interface Filter {

    void process(String method, String uri, Object... objs);

}
