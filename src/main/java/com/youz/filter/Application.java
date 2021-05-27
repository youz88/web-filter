package com.youz.filter;

import com.youz.filter.enums.FilterType;
import com.youz.filter.rule.AbstractFilter;
import com.youz.filter.rule.Filter;
import com.youz.filter.web.FilterPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Bean
    public FilterPointcutAdvisor filterPointcutAdvisor() {
        FilterPointcutAdvisor advisor = new FilterPointcutAdvisor("com.youz.filter.web")
                .addFilter(FilterType.Emoji.instance().addPathPatterns("").excludePathPatterns(""))
                ;
        return advisor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
