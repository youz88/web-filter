package com.youz.filter.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1(String p1, Test p2) {
        System.out.println(p1);
        System.out.println(p2);
        return "hello world";
    }

    @PostMapping("/test2")
    public String test2(@RequestBody Test p1) {
        return "hello world";
    }

    @PostMapping("/test3")
    public String test3(@RequestBody List<Test> p2) {
        return "hello world";
    }

    @PostMapping("/test4")
    public String test4(@RequestBody List<String> p3) {
        return "hello world";
    }
}
