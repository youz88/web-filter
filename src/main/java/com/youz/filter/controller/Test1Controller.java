package com.youz.filter.controller;

import com.youz.filter.web.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {

    @GetMapping("/test11")
    public String test1(String p1, Test p2) {
        System.out.println(p1);
        System.out.println(p2);
        return "hello world";
    }

}
