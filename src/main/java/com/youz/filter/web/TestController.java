package com.youz.filter.web;

import com.youz.filter.annotation.Filter;
import com.youz.filter.annotation.FilterParam;
import com.youz.filter.enums.FilterType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @GetMapping("/test1")
    @Filter(FilterType.Emoji)
    public String test1(@FilterParam(FilterType.Emoji) String p1, Test p2) {
        System.out.println("========>" + p1);
        System.out.println("========>" + p2);
        return "hello world";
    }

    @GetMapping("/test2")
    public String test2(@RequestBody String p1,
                        @RequestBody List<String> p2,
                        @RequestBody Test p3,
                        @RequestBody List<Test> p4) {
        return "hello world";
    }
}
