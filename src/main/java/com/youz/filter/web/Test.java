package com.youz.filter.web;

import java.io.Serializable;

public class Test implements Serializable {

    private String s1;

    private String s2;

    public String getS1() {
        return s1;
    }

    public Test setS1(String s1) {
        this.s1 = s1;
        return this;
    }

    public String getS2() {
        return s2;
    }

    public Test setS2(String s2) {
        this.s2 = s2;
        return this;
    }

    @Override
    public String toString() {
        return "Test{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }
}
